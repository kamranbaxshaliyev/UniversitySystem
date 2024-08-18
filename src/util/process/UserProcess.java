package util.process;

import util.DataInput;
import util.DataOutput;

import java.util.InputMismatchException;

public class UserProcess
{
	public static void processDelete()
	{
		loop:
		while (true)
		{
			try
			{
				System.out.println("DELETE");
				DataOutput.showUserOptions();

				int nOption = DataInput.requireOption();

				switch (nOption)
				{
					case 1:
						AdminProcess.processDelete();
						break;

					case 2:
						TeacherProcess.processDelete();
						break;

					case 3:
						StudentProcess.processDelete();
						break;

					case 4:
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

	public static void processEdit()
	{
		loop:
		while (true)
		{
			try
			{
				System.out.println("EDIT USER");
				System.out.println("""
				1. Teacher
				2. Student
				3. Exit
				""");
				int nOption = DataInput.requireOption();

				switch (nOption)
				{
					case 1:
						TeacherProcess.processEdit();
						break;

					case 2:
						StudentProcess.processEdit();
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

	public static void processAdd()
	{
		loop:
		while (true)
		{
			try
			{
				System.out.println("ADD USER");
				DataOutput.showUserOptions();
				int nOption = DataInput.requireOption();

				switch (nOption)
				{
					// Add admin
					case 1:
						AdminProcess.processAdd();
						break;

					// Add teacher
					case 2:
						TeacherProcess.processAdd();
						break;

					// Add student
					case 3:
						StudentProcess.processAdd();
						break;

					// Exit
					case 4:
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

	public static void processShow()
	{
		loop:
		while (true)
		{
			try
			{
				System.out.println("SHOW USERS");
				DataOutput.showUserOptions();
				int nOption = DataInput.requireOption();

				switch (nOption)
				{
					case 1:
						AdminProcess.processShow();
						break;

					case 2:
						TeacherProcess.processShow();
						break;

					case 3:
						StudentProcess.processShow();
						break;

					case 4:
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
}
