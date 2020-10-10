
public abstract class Person 
{
	private int life = 100;
	private String name;
	
	public Person(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void changeLife(int difference)
	{
		life += difference;
	}
	
	public int getLife()
	{
		return life;
	}
	
}
