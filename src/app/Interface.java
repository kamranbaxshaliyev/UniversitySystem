package app;

import util.*;
import util.process.AdminProcess;
import util.process.StudentProcess;
import util.process.TeacherProcess;

import java.util.*;

public class Interface
{
	public static void start()
	{
		processAuthorization();

		processPanel();
	}

	private static void processAuthorization()
	{
		System.out.println("Log in");
		boolean bAuthorized = false;

		while (!bAuthorized)
		{
			try
			{
				Scanner oScanner = new Scanner(System.in);

				System.out.print("Username: ");
				String sUsername = oScanner.next();
				System.out.print("Password: ");
				String sPassword = oScanner.next();
				System.out.println();

				if (!(bAuthorized = Application.authorize(sUsername, sPassword)))
				{
					DataOutput.showWrongLoginMess();
					System.out.println("Try again");
				}
			}
			catch (Exception e)
			{
				DataOutput.showSystemError();
			}
		}
	}

	private static void processPanel()
	{
		switch (Application.MODE)
		{
			case ADMIN:
				AdminProcess.processPanel();
				break;

			case TEACHER:
				TeacherProcess.processPanel();
				break;

			case STUDENT:
				StudentProcess.processPanel();
				break;
		}
	}
}