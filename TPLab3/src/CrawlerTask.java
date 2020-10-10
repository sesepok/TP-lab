import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class CrawlerTask implements Runnable
{
	public static final String LINK_REGEX = "href\\s*=\\s*\"([^$^\"]*)\"";
    public static final Pattern LINK_PATTERN = Pattern.compile(LINK_REGEX, Pattern.CASE_INSENSITIVE);
    
    private int timeoutSeconds;
    
    private URLPool pool;
    
    public CrawlerTask(URLPool pool, int timeoutSeconds)
    {
    	this.pool = pool;
    	this.timeoutSeconds = timeoutSeconds;
    }
    
    public Socket sendRequest(URLDepthPair pair) throws UnknownHostException, IOException
    {
    	Socket socket = null;
    	if (pair.getProtocol().equals("https"))
    	{
    		SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
    		socket = (SSLSocket)factory.createSocket(pair.getHost(), 443);
    		((SSLSocket)socket).startHandshake();
    	}
    	else
    	{
    		socket = new Socket(pair.getHost(), 80);
    	}
        
        socket.setSoTimeout(timeoutSeconds * 1000);
    	
    	PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
    	
    	writer.println("GET " + pair.getPath() + " HTTP/1.1");
    	writer.println("Host: " + pair.getHost());
    	writer.println("Connection: close");
    	writer.println();
    	
    	
    	return socket;
    }
    
    public void processURL(URLDepthPair pair)
    {
    	Socket socket;
    	InputStream is;
    	try 
    	{
			socket = sendRequest(pair);
			is = socket.getInputStream();
		} 
    	catch (IOException e) 
    	{
			System.err.println("IOException at page " + pair.getURL() + ":");
			System.err.println(e.getMessage());
			return;
		}
    	
    	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    	
    	try
    	{
	    	String line = reader.readLine();
	    	while (line != null)
	    	{
	    		//System.out.println(line);
	    		Matcher matcher = LINK_PATTERN.matcher(line);
	    		while (matcher.find())
	    		{
	    			String url = matcher.group(1);
	    			try {
	    				if (!URLDepthPair.isAbsolute(url))
	    					url = pair.getAbsolute() + "/" + url;
						URLDepthPair newPair = new URLDepthPair(url, pair.getDepth() + 1);
						pool.add(newPair);
					} catch (MalformedURLException e) {
						System.err.println("MalformedURLException with URL " + url + ":");
						System.err.println(e.getMessage());
					} 
	    		}
	    		line = reader.readLine();
	    	}
	    	reader.close();
    	}
    	catch (IOException e)
    	{
    		System.err.println("IOException while reading from " + pair.getURL() + ":");
    		System.err.println(e.getMessage());
    	}
    	
    	try
    	{
    		socket.close();
    	}
    	catch (IOException e)
    	{
    		System.err.println("IOException while closing socket with url " + pair.getURL() + ":");
    		System.err.println(e.getMessage());
    	}
    }
    
    
    public void run()
    {
    	while (!Thread.interrupted())
    	{
    		try 
    		{
				processURL(pool.get());
			} 
    		catch (InterruptedException e) 
    		{
				break;
			}
    	}
    }

}
