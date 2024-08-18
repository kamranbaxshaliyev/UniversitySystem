package util.process;

import app.Application;
import app.Interface;
import bean.Admin;
import bean.User;
import util.DataInput;
import util.DataOutput;
import util.DataUtil;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;

public class AdminProcess
{
	public static void processPanel()
	{
		loop:
		while (true)
		{
			try
			{
				System.out.println("""
						MODE: ADMIN
						1. Show users
						2. Add user
						3. Edit user
						4. Delete user
						5. Show subjects
						6. Clear data
						7. Edit my credentials
						8. Log off
						9. Close application
						""");

				int nOption = DataInput.requireOption();

				switch (nOption)
				{
					case 1:
						UserProcess.processShow();
						break;

					case 2:
						UserProcess.processAdd();
						break;

					case 3:
						UserProcess.processEdit();
						break;

					case 4:
						UserProcess.processDelete();
						break;

					case 5:
						DataOutput.showSubjects();
						break;

					case 6:
						processClearData();
						break;

					case 7:
						processEdit();
						break;

					case 8:
						Application.unAuthorize();
						Interface.start();

					case 9:
						break loop;

					default:
						throw new InputMismatchException();
				}
			}
			catch (InputMismatchException e)
			{
				DataOutput.showWrongOptionMess();
			}
			catch (Exception e)
			{
				DataOutput.showSystemError();
			}
		}
	}

	private static void processClearData()
	{
		System.out.println("Are you sure you want to clear all existing data?");
		String sApprove = DataInput.requireString("letter (Y | N)");

		if (sApprove.equalsIgnoreCase("Y"))
		{
			if (!DataUtil.clearData())
				DataOutput.showSystemError();
			else
			{
				System.out.println("Data cleared! Please re-run the application");
				System.exit(0);
			}
		}
	}

	public static void processEdit()
	{
		loop:
		while (true)
		{
			try
			{
				Admin oCurAdmin = (Admin) Application.getCurrentUser();
				DataOutput.showAdminData(oCurAdmin);

				System.out.println("""
				1. Edit username
				2. Edit password
				3. Exit
				""");

				int nOption = DataInput.requireOption();

				switch (nOption)
				{
					case 1:
						String username = DataInput.requireString("username");
						oCurAdmin.setUsername(username);
						break;

					case 2:
						String password = DataInput.requireString("password");
						oCurAdmin.setPassword(password);
						break;

					case 3:
						break loop;

					default:
						throw new InputMismatchException();
				}
			}
			catch (InputMismatchException e)
			{
				DataOutput.showWrongOptionMess();
			}
			catch (Exception e)
			{
				DataOutput.showSystemError();
			}
		}
	}

	public static void processDelete()
	{
		while (true)
		{
			try
			{
				DataOutput.showAdmins(false);

				int nOption = DataInput.requireOption();

				if (nOption == 0) break;

				int nAdminIndex = nOption - 1;
				List<Admin> lsAdmins = DataUtil.getAdmins();

				if (lsAdmins.size() >= nOption && nOption >= 1)
				{
					Admin admin = lsAdmins.get(nAdminIndex);
					System.out.println("Are you sure you want to delete user " + admin.getUsername() + "?");
					String sApprove = DataInput.requireString("letter (Y | N)");

					if (sApprove.equalsIgnoreCase("Y"))
						DataUtil.removeUser(admin);
				}
				else
					throw new InputMismatchException();
			}
			catch (InputMismatchException e)
			{
				DataOutput.showWrongOptionMess();
			}
			catch (Exception e)
			{
				DataOutput.showSystemError();
			}
		}
	}

	public static void processAdd()
	{
		main:
		while (true)
		{
			try
			{
				System.out.println("ADD ADMIN");
				String username = DataInput.requireString("username");

				for (User user : DataUtil.getUsers())
				{
					if (Objects.equals(username, user.getUsername()))
					{
						System.out.println("User with entered username already exists. Please try again\n");
						continue main;
					}
				}

				String password = DataInput.requireString("password");

				Admin admin = new Admin(username, password);
				DataUtil.addUser(admin);
				break;
			}
			catch (InputMismatchException e)
			{
				DataOutput.showWrongOptionMess();
			}
			catch (Exception e)
			{
				DataOutput.showSystemError();
			}
		}
	}

	public static void processShow()
	{
		while (true)
		{
			try
			{
				DataOutput.showAdmins(true);
				int nOption = DataInput.requireOption();

				if (nOption == 0)
					break;

				int nAdminIndex = nOption - 1;
				List<Admin> lsAdmins = DataUtil.getAdmins();

				if (lsAdmins.size() >= nOption && nOption >= 1)
				{
					Admin admin = DataUtil.getAdmins().get(nAdminIndex);
					DataOutput.showAdminData(admin);
				}
				else
					throw new InputMismatchException();
			}
			catch (InputMismatchException e)
			{
				DataOutput.showWrongOptionMess();
			}
			catch (Exception e)
			{
				DataOutput.showSystemError();
			}
		}
	}
}
