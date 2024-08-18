package bean;

public class Teacher extends StandardUser
{
	private String subject;
	private double salary;

	public Teacher(String username, String password)
	{
		super(username, password);
	}

	public String getSubject()
	{
		return subject;
	}

	public Teacher setSubject(String subject)
	{
		this.subject = subject;
		return this;
	}

	public double getSalary()
	{
		return salary;
	}

	public Teacher setSalary(double salary)
	{
		this.salary = salary;
		return this;
	}
}
