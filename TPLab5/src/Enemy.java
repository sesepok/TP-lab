import java.util.Random;

public class Enemy extends Person 
{
	int guessedNumber = 0;
	private Random rand = new Random();
	int lowerLimit = 0;
	int higherLimit = 21;

	public Enemy(String name)
	{
		super(name);
		guessNewNumber();
	}
	
	private void guessNewNumber()
	{
		guessedNumber = Math.abs(rand.nextInt()) % 20 + 1;
	}
	
	public int getGuessedNumber()
	{
		return guessedNumber;
	}
	
	private int randInt(int a, int b)
	{
		return Math.abs(rand.nextInt(b - a)) + a;
	}
	
	private int guessPlayersNumber()
	{
		return randInt(lowerLimit + 1, higherLimit);
	}
	
	private void resetLimits()
	{
		lowerLimit = 0;
		higherLimit = 21;
	}
	
	public void attack(Player player)
	{
		int number = guessPlayersNumber();
		
		if (number == player.getGuessedNumber())
		{
			player.getHit();
			System.out.println("���� ����� � ���! � " + player.getName() + " �������� " + player.getLife() + " ������!");
			resetLimits();
		}
		else
		{
			System.out.println("���� �� ������ �����.");
			if (number > player.getGuessedNumber())
				higherLimit = number;
			else
				lowerLimit = number;
		}
	}
	
	public void getHit()
	{
		changeLife(-10);
		guessNewNumber();
	}
	
	
}
