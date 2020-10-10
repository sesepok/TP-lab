import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.LinkedList;

public class Crawler 
{
	private URLPool pool;
	private int threadCount;
	private int timeoutSeconds;
	
	private LinkedList<Thread> threads;
	
	public Crawler(String startURL, int maxDepth, int threadCount, int timeoutSeconds) throws MalformedURLException, URISyntaxException
	{
		pool = new URLPool(maxDepth);
		pool.add(new URLDepthPair(startURL, 0));
		
		this.threadCount = threadCount;
		this.timeoutSeconds = timeoutSeconds;
		
		threads = new LinkedList<Thread>();
	}
	
	public void crawl()
	{
		for (int i = 0; i < threadCount; i++)
		{
			CrawlerTask task = new CrawlerTask(pool, timeoutSeconds);
			Thread thread = new Thread(task);
			thread.start();
			threads.add(thread);
		}
		
		while (pool.getWaitingThreads() != threadCount)
		{
			try 
			{
				Thread.sleep(1000);
			} catch (InterruptedException e) 
			{
				System.err.println("Main thread abnormally interrupted");
				finish();
			}
		}
		finish();
	}
	
	private void finish()
	{
		while (!threads.isEmpty())
		{
			threads.removeFirst().interrupt();
		}
	}
	
	private void printProcessedURLs()
	{
		pool.printProcessedURLs();
	}
	
	
	public static void main(String[] args)
	{
		if (args.length < 2 || args.length > 4)
		{
			System.err.println("Wrong argumets format.");
			System.err.println("Usage: java Crawler <URL> <Depth> [threads] [timeout in seconds]");
			System.exit(1);
		}
		
		int timeoutSeconds = 0;
		int threads = 0;
		int maxDepth = 0;
		try
		{
			timeoutSeconds = (args.length < 4) ? 5 : Integer.parseInt(args[3]);
			threads = (args.length < 3) ? 5 : Integer.parseInt(args[2]);
			maxDepth = Integer.parseInt(args[1]);
		}
		catch (NumberFormatException e)
		{
			System.err.println("Wrong number format");
			System.err.println("Usage: java Crawler <URL> <Depth> [threads] [timeout in seconds]");
			System.exit(1);
		}
		String URL = args[0];
		
		
		
		try 
		{
			Crawler crawler = new Crawler(URL, maxDepth, threads, timeoutSeconds);
			crawler.crawl();
			System.out.println("\n=========================================\n");
			crawler.printProcessedURLs();
		} 
		catch (MalformedURLException | URISyntaxException e) 
		{
			System.out.println("Invalid URL");
			System.exit(1);
		}
	
	}

}
