import java.util.Random;

public class Main 
{
	private static volatile int number;
	private static volatile boolean pending = false;
	private static Random rand = new Random();
	
	static class T1 extends Thread
	{
		public void run()
		{
			while (true)
			{
				number = Math.abs(rand.nextInt()) % 10;
				pending = true;
				System.out.println("Thread 1: generated " + number);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					break;
				}
			}
		}
	}
	
	static class T2 extends Thread
	{
		public void run()
		{
			while (true)
			{
				if (pending && number % 2 == 0)
				{
					pending = false;
					System.out.println("Thread 2: fetched " + number + ", computed " + number*number);
				}
				
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					break;
				}
			}
		}
	}
	
	static class T3 extends Thread
	{
		public void run()
		{
			while (true)
			{
				if (pending && number % 2 == 1)
				{
					pending = false;
					System.out.println("Thread 3: fetched " + number + 
							", computed " + number*number*number);
				}
				
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					break;
				}
			}
		}
	}
	
	public static void main(String[] args)
	{
		System.out.println("ТП. Лабораторная работа №1.\nВыполнил студент группы БСТ1702 Заздравнов В.И.");
		
		T1 t1 = new T1();
		T2 t2 = new T2();
		T3 t3 = new T3();
		
		t1.start();
		t2.start();
		t3.start();
	}

}
