import java.util.Random;

public class Player extends Person
{
	int guessedNumber = 0;
	private Random rand = new Random();

	public Player(String name)
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
	
	public void attack(int number, Enemy enemy)
	{
		if (number == enemy.getGuessedNumber())
		{
			enemy.getHit();
			System.out.println("Вы угадали! У врага по имени " + enemy.getName() 
				+ " осталось " + enemy.getLife() + " жизней!");
		}
		else if (number < enemy.getGuessedNumber())
			System.out.println("Вы не угадали! Число, что вы пытаетесь угадать, больше.");
		else
			System.out.println("Вы не угадали! Число, что вы пытаетесь угадать, меньше.");
			
	}
	
	public void getHit()
	{
		changeLife(-10);
		guessNewNumber();
	}
}
