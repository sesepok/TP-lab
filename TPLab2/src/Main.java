import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

	
	//private static String [][] data = null;
	
	private static String[][] initData()
	{
		String[][] data = new String[10][4];
		
		data[0][0] = "United States";
		data[0][1] = "45785090";
		data[0][2] = "19.8";
		data[0][3] = "14.3";
		
		data[1][0] = "Russia";
		data[1][1] = "11048064";
		data[1][2] = "4.8";
		data[1][3] = "7.7";
				
		data[2][0] = "Germany";
		data[2][1] = "9845244";
		data[2][2] = "4.3";
		data[2][3] = "11.9";
		
		data[3][0] = "Saudi Arabia";
		data[3][1] = "9060433";
		data[3][2] = "3.9";
		data[3][3] = "31.4";
		
		data[4][0] = "United Arab Emirates";
		data[4][1] = "7826981";
		data[4][2] = "3.4";
		data[4][3] = "83.7";
		
		data[5][0] = "United Kingdom";
		data[5][1] = "7824131";
		data[5][2] = "3.4";
		data[5][3] = "12.4";
		
		data[6][0] = "France";
		data[6][1] = "7439086";
		data[6][2] = "3.2";
		data[6][3] = "11.6";
		
		data[7][0] = "Canada";
		data[7][1] = "7284069";
		data[7][2] = "3.1";
		data[7][3] = "20.7";
		
		data[8][0] = "Australia";
		data[8][1] = "6468640";
		data[8][2] = "2.8";
		data[8][3] = "27.7";
		
		data[9][0] = "Spain";
		data[9][1] = "6466605";
		data[9][2] = "2.8";
		data[9][3] = "13.8";
		
		return data;
	}
	
	private static void printData(String[][] data)
	{
		String format = "%25s %10s %14s %12s%n";
		System.out.printf(format, "Country", "Immigrants", "% world total", "% population");
		for (int i = 0; i < data.length; i++)
		{
			System.out.printf(format, data[i][0], data[i][1], data[i][2], data[i][3]);
		}
	}
	
	private static int totalImmigrants(String[][] data)
	{
		int sum = 0;
		for (int i = 0; i < data.length; i++)
			sum += Integer.parseInt(data[i][1]);
		return sum;
	}
	
	private static double totalPercentage(String[][] data)
	{
		double sum = 0;
		for (int i = 0; i < data.length; i++)
			sum += Double.parseDouble(data[i][2]);
		return sum;
	}
	
	private static String countryWithLeastImmigration(String[][] data)
	{
		double lowest = 100;
		String lowestCountry = "";
		for (int i = 0; i < data.length; i++)
			if (Double.parseDouble(data[i][3]) < lowest)
			{
				lowest = Double.parseDouble(data[i][3]);
				lowestCountry = data[i][0];
			}
		return lowestCountry;
	}
	
	private static String countryWithGreatestImmigration(String[][] data)
	{
		double highest = 0;
		String highestCountry = "";
		for (int i = 0; i < data.length; i++)
			if (Double.parseDouble(data[i][3]) > highest)
			{
				highest = Double.parseDouble(data[i][3]);
				highestCountry = data[i][0];
			}
		return highestCountry;
	}
	
	public static int totalPopulation(String[][] data)
	{
		int sum = 0;
		for (int i = 0; i < data.length; i++)
			sum += Double.parseDouble(data[i][1]) / (0.01 * Double.parseDouble(data[i][3]));
		return sum;
	}
	
	private static String[][] readDataFromFile(String filename)
	{
		File file = null;
		Scanner reader = null;
		
		//open
		try
		{
			file = new File(filename);
			reader = new Scanner(file);
		}
		catch(FileNotFoundException e)
		{
			return null;
		}
		
		//read
		ArrayList<String[]> dataArray = new ArrayList<String[]>();
		String[] currentRecord = new String[4];
		int lineCounter = 0;
		while(reader.hasNextLine())
		{
			currentRecord[lineCounter] = reader.nextLine();
			lineCounter++;
			if (lineCounter > 3)
			{
				lineCounter = 0;
				dataArray.add(currentRecord.clone());
			}
		}
		reader.close();
		
		//check
		for (int i = 0; i < dataArray.size(); i++)
		{
			try 
			{
				Integer.parseInt(dataArray.get(i)[1]);
				Double.parseDouble(dataArray.get(i)[2]);
				Double.parseDouble(dataArray.get(i)[3]);
			}
			catch (NumberFormatException e)
			{
				return null;
			}
		}
		
		return dataArray.toArray(new String[0][0]);
	}
	
	public static void sortByImmigrantPopulationPercent(String[][] data)
	{
		Arrays.sort(data, Comparator.<String[]>comparingDouble(a -> Double.parseDouble(a[3])).reversed());
	}
	
	public static void printValues(String[][] data)
	{
		System.out.println("Total immigrants: " + totalImmigrants(data));
		System.out.println("Total percentage of world's immigrants: " + totalPercentage(data) + "%");
		System.out.println("Country with least immigration: " + countryWithLeastImmigration(data));
		System.out.println("Country with greatest immigration: " + countryWithGreatestImmigration(data));
		System.out.println("Total estimated population of all countries: " + totalPopulation(data));
	}
	
	public static void main(String[] args)
	{
		System.out.println("ТП. Лабораторная работа №2.\nВыполнил студент группы БСТ1702 Заздравнов В.И.");
		System.out.println();
		
		String[][] data = initData();
		printData(data);
		System.out.println( );
		printValues(data);
		System.out.println( );
		sortByImmigrantPopulationPercent(data);
		System.out.println("Отсортированно:");
		printData(data);
		
		System.out.print("Введите имя файла >");
			
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		String filename = reader.nextLine();
		data = readDataFromFile(filename);
		if (data == null)
			System.out.println("Ошибка при чтении файла");
		else
		{
			System.out.println("Прочитано из файла:");
			printData(data);
			System.out.println( );
			printValues(data);
			System.out.println( );
			sortByImmigrantPopulationPercent(data);
			System.out.println("Отсортированно:");
			printData(data);
		}
		
		
	}
}
