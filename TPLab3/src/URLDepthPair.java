import java.net.MalformedURLException;
import java.net.URL;

public class URLDepthPair 
{
	private URL url;
	private int depth;
	
	public static final String HTTP_PREFIX = "http://";
	public static final String HTTPS_PREFIX = "https://";	
	
	public URLDepthPair(String url, int depth) throws MalformedURLException
	{
		if (url.endsWith("/"))
			url = url.substring(0, url.length() - 1);
		URL u = new URL(url);
		
		this.url = u;
		this.depth = depth;
		
	}
	
	
	public String toString()
	{
		return "( " + url + ", depth=" + depth + ")";
	}
	
	
	public URL getURL()
	{
		return url;
	}
	
	public int getDepth()
	{
		return depth;
	}
	
	public String getHost()
	{
		return url.getHost();
	}
	
	public String getPath()
	{
		return (url.getPath().length() == 0) ? "/" : url.getPath();
	}
	
	public String getAbsolute()
	{
		return url.getProtocol() + "://" + url.getHost();
	}
	
	public String getProtocol()
	{
		return url.getProtocol();
	}
	
    public static boolean isAbsolute(String url)
    {
    	return (url.startsWith(HTTP_PREFIX) || url.startsWith(HTTPS_PREFIX));
    	
    }
}
