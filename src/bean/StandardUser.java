package bean;

public abstract class StandardUser extends User
{
	private String name;
	private String surname;
	private int age;
	private String email;
	private String phone;

	public StandardUser(String username, String password)
	{
		super(username, password);
	}

	public String getName()
	{
		return name;
	}

	public StandardUser setName(String name)
	{
		this.name = name;
		return this;
	}

	public String getSurname()
	{
		return surname;
	}

	public StandardUser setSurname(String surname)
	{
		this.surname = surname;
		return this;
	}

	public int getAge()
	{
		return age;
	}

	public StandardUser setAge(int age)
	{
		this.age = age;
		return this;
	}

	public String getEmail()
	{
		return email;
	}

	public StandardUser setEmail(String email)
	{
		this.email = email;
		return this;
	}

	public String getPhone()
	{
		return phone;
	}

	public StandardUser setPhone(String phone)
	{
		this.phone = phone;
		return this;
	}
}
