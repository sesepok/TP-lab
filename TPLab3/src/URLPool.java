import java.util.HashSet;
import java.util.LinkedList;

public class URLPool 
{
	private int maxDepth;
	private int waitingThreads = 0;
	
	private LinkedList<URLDepthPair> pendingURLs;
	private LinkedList<URLDepthPair> processedURLs;
	private HashSet<String> visitedURLs;
	
	public URLPool (int maxDepth)
	{
		pendingURLs = new LinkedList<URLDepthPair>();
		processedURLs = new LinkedList<URLDepthPair>();
		visitedURLs = new HashSet<String>();
		this.maxDepth = maxDepth;
	}
	
	public synchronized int getWaitingThreads()
	{
		return waitingThreads;
	}
	
	public synchronized void add(URLDepthPair pair)
	{
		String url = pair.getURL().toString();
		if (visitedURLs.contains(url))
			return;
		visitedURLs.add(url);
		
		if(pair.getDepth() < maxDepth)
		{
			pendingURLs.add(pair);
			System.out.println("Adding URL " + pair.getURL() + " to the pool");
			notify();
		}
	}
	
	public synchronized URLDepthPair get() throws InterruptedException
	{
		while (pendingURLs.size() == 0)
		{
			waitingThreads++;			
			wait();
			waitingThreads--;
		}
		
		URLDepthPair pair = pendingURLs.removeFirst();
		processedURLs.add(pair);
		return pair;
	}
	
	
	public synchronized void printProcessedURLs()
	{
		System.out.println("Found " + processedURLs.size() +" URLs");
		for (URLDepthPair pair : processedURLs)
		{
			System.out.println(pair);
		}
	}
}
