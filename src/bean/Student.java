package bean;

import java.util.HashMap;

public class Student extends StandardUser
{
	private String groupName;
	private double scholarship;
	private HashMap<String, Integer> hmSubjects = new HashMap<>();

	public Student(String username, String password)
	{
		super(username, password);
	}

	public HashMap<String, Integer> getHmSubjects()
	{
		return hmSubjects;
	}

	public String getGroupName()
	{
		return groupName;
	}

	public Student setGroupName(String groupName)
	{
		this.groupName = groupName;
		return this;
	}

	public double getScholarship()
	{
		return scholarship;
	}

	public Student setScholarship(double scholarship)
	{
		this.scholarship = scholarship;
		return this;
	}
}
