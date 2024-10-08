package bean;

import java.io.Serializable;

public abstract class User implements Serializable
{
	private String username;
	private String password;

	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	public String getUsername()
	{
		return username;
	}

	public User setUsername(String username)
	{
		this.username = username;
		return this;
	}

	public String getPassword()
	{
		return password;
	}

	public User setPassword(String password)
	{
		this.password = password;
		return this;
	}
}
