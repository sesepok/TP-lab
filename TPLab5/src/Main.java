import java.util.Scanner;

public class Main 
{
	
	public static void main(String[] args)
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.print("������� ���� ��� >");
		String playerName = scanner.nextLine();
		System.out.print("������� ��� ����� >");
		String enemyName = scanner.nextLine();
		
		Player player = new Player(playerName);
		Enemy enemy = new Enemy(enemyName);
		
		while (enemy.getLife() > 0 && player.getLife() > 0)
		{
			System.out.print("������� ����� >");
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
					System.out.println("������! ��������� ������ ����� �� 1 �� 20");
				}
			}
			
			player.attack(playerGuess, enemy);
			if (enemy.getLife() > 0) enemy.attack(player);
		}
		
		if (enemy.getLife() < 1)
			System.out.println("�� ����� ����� � ��������! ������� ������������!");
		else
			System.out.println("� ���������, ���� ���� ��� � �������. ��� � ��!");
		System.out.println("� �� ���� ���. �� ����� ������.");
	}

}
