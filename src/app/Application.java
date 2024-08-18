package app;

import bean.Admin;
import bean.User;
import util.DataOutput;
import util.DataUtil;

import java.util.Objects;

public class Application
{
	public static Mode MODE;
	private static User oCurrentUser;

	public enum Mode
	{
		ADMIN, TEACHER, STUDENT
	}

	public static boolean authorize(String username, String password)
	{
		boolean bResult = false;

		loop:
		for(User user : DataUtil.getUsers())
		{
			if(Objects.equals(user.getUsername(), username) && Objects.equals(user.getPassword(), password))
			{
				Application.oCurrentUser = user;

				switch(user.getClass().getName())
				{
					case "bean.Admin":
						MODE = Mode.ADMIN;
						break;

					case "bean.Teacher":
						MODE = Mode.TEACHER;
						break;

					case "bean.Student":
						MODE = Mode.STUDENT;
						break;

					default:
						break loop;
				}

				bResult = true;
			}
		}

		return bResult;
	}

	public static void unAuthorize()
	{
		MODE = null;
		oCurrentUser = null;
	}

	public static boolean onStart()
	{
		boolean bResult;

		try
		{
			if (!DataUtil.loadData())
				throw new Exception();

			if (DataUtil.getUsers() == null)
			{
				initializeData();
			}

			bResult = true;
		}
		catch (Exception e)
		{
			DataOutput.showSystemError();
			bResult = false;
		}

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			if(!DataUtil.updateData())
				DataOutput.showSystemError();
		}));

		return bResult;
	}

	public static void initializeData()
	{
		DataUtil.initializeLsUsers();
		DataUtil.addUser(new Admin("admin", "admin"));
	}

	public static void onExit()
	{
		if(!DataUtil.updateData())
			DataOutput.showSystemError();
	}

	public static User getCurrentUser()
	{
		return oCurrentUser;
	}

	public static void start()
	{
		Interface.start();
	}
}
