import java.util.Scanner;

public class Main 
{
	
	public static void main(String[] args)
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.print("Введите свое имя >");
		String playerName = scanner.nextLine();
		System.out.print("Введите имя врага >");
		String enemyName = scanner.nextLine();
		
		Player player = new Player(playerName);
		Enemy enemy = new Enemy(enemyName);
		
		while (enemy.getLife() > 0 && player.getLife() > 0)
		{
			System.out.print("Введите число >");
			int playerGuess = 0;
			boolean correctInput = false;
			while(!correctInput)
			{
				try
				{
					playerGuess = scanner.nextInt();
					correctInput = playerGuess > 0 && playerGuess < 21;
				}
				catch (Exception e)
				{
					correctInput = false;
					System.out.println("Ошибка! Требуется ввести число от 1 до 20");
				}
			}
			
			player.attack(playerGuess, enemy);
			if (enemy.getLife() > 0) enemy.attack(player);
		}
		
		if (enemy.getLife() < 1)
			System.out.println("Вы убили врага и победили! Примите поздравления!");
		else
			System.out.println("К сожалению, враг убил вас и победил. Увы и ах!");
		System.out.println("А на этом все. До новых встреч.");
	}

}
