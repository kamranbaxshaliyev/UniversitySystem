package util;

import app.Application;
import bean.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataUtil
{
	private static List<User> lsUsers;
	private static final String sDataPath = "data/";
	private static final String sLsUsersPath = sDataPath + "lsUsers.obj";
	private static final String E_OBJECT_NOT_EXIST = "E_OBJECT_NOT_EXIST";

	public static List<User> getUsers()
	{
		return lsUsers;
	}

	public static void addUser(User user)
	{
		lsUsers.add(user);
	}

	public static List<String> getSubjects()
	{
		List<String> lsSubjects = new ArrayList<>();
		List<Teacher> lsTeachers = getTeachers();

		if(!lsTeachers.isEmpty())
		{
			for (Teacher teacher : lsTeachers)
				lsSubjects.add(teacher.getSubject());
		}

		return lsSubjects;
	}

	public static void initializeLsUsers()
	{
		if(lsUsers == null)
			lsUsers = new ArrayList<>();
	}

	public static boolean updateData()
	{
		boolean bResult;

		try
		{
			if(!FileUtil.writeObject(lsUsers, sLsUsersPath))
				throw new Exception();

			bResult = true;
		}
		catch (Exception e)
		{
			bResult = false;
		}

		return bResult;
	}

	public static boolean loadData()
	{
		boolean bResult = true;

		try
		{
			if(new File(sLsUsersPath).exists())
				lsUsers = (List<User>) FileUtil.readObject(sLsUsersPath);
			else
				throw new Exception(E_OBJECT_NOT_EXIST);
		}
		catch(Exception e)
		{
			if(Objects.equals(e.getMessage(), E_OBJECT_NOT_EXIST))
			{
				Application.initializeData();
			}
			else
				bResult = false;
		}

		return bResult;
	}

	public static boolean clearData()
	{
		boolean bResult;

		if(new File(sLsUsersPath).exists())
			bResult = FileUtil.deleteObject(sLsUsersPath);
		else
			bResult = false;

		if(bResult)
			lsUsers = null;

		return bResult;
	}

	public static List<Admin> getAdmins()
	{
		List<Admin> lsAdmins = new ArrayList<>();

		for(User user : lsUsers)
		{
			if(user instanceof Admin)
				lsAdmins.add((Admin) user);
		}

		return lsAdmins;
	}

	public static List<Teacher> getTeachers()
	{
		List<Teacher> lsTeachers = new ArrayList<>();

		for(User user : lsUsers)
		{
			if(user instanceof Teacher)
				lsTeachers.add((Teacher) user);
		}

		return lsTeachers;
	}

	public static List<Student> getStudents()
	{
		List<Student> lsStudents = new ArrayList<>();

		for(User user : lsUsers)
		{
			if(user instanceof Student)
				lsStudents.add((Student) user);
		}

		return lsStudents;
	}

	public static void removeUser(User enteredUser)
	{
		lsUsers.removeIf(user -> Objects.equals(user.getUsername(), enteredUser.getUsername()));
	}
}