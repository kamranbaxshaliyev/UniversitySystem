package util.process;

import app.Application;
import app.Interface;
import bean.Student;
import bean.Teacher;
import bean.User;
import util.DataInput;
import util.DataOutput;
import util.DataUtil;

import java.util.*;

public class StudentProcess
{
	public static void processDelete()
	{
		while (true)
		{
			try
			{
				if(!DataOutput.showStudents(false))
					break;

				int nOption = DataInput.requireOption();

				if (nOption == 0) break;

				int nStudentIndex = nOption - 1;
				List<Student> lsStudents = DataUtil.getStudents();

				if (lsStudents.size() >= nOption && nOption >= 1)
				{
					Student student = lsStudents.get(nStudentIndex);
					System.out.println("Are you sure you want to delete user " + student.getName() + " " + student.getSurname() + "?");
					String sApprove = DataInput.requireString("letter (Y | N)");

					if (sApprove.equalsIgnoreCase("Y"))
						DataUtil.removeUser(student);
				} else
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

	public static void processEdit()
	{
		while (true)
		{
			try
			{
				if(!DataOutput.showStudents(true))
					break;

				int nOption = DataInput.requireOption();

				if (nOption == 0) break;

				int nStudentIndex = nOption - 1;

				List<Student> lsStudents = DataUtil.getStudents();

				if (lsStudents.size() >= nOption && nOption >= 1)
				{
					loop:
					while (true)
					{
						Student student = lsStudents.get(nStudentIndex);
						DataOutput.showStudentData(student);
						System.out.println("EDIT");
						System.out.println("""
							1. Username 2. Password 3. Name       4. Surname      5. Age
							6. Email    7. Phone    8. Group Name 9. Scholarship  10. Exit
							""");
						nOption = DataInput.requireOption();

						switch (nOption)
						{
							case 1:
								String username = DataInput.requireString("username");
								student.setUsername(username);
								break;

							case 2:
								String password = DataInput.requireString("password");
								student.setUsername(password);
								break;

							case 3:
								String name = DataInput.requireString("name");
								student.setName(name);
								break;

							case 4:
								String surname = DataInput.requireString("surname");
								student.setSurname(surname);
								break;

							case 5:
								int age = DataInput.requireInt("age");
								student.setAge(age);
								break;

							case 6:
								String email = DataInput.requireString("email");
								student.setEmail(email);
								break;

							case 7:
								String phone = DataInput.requireString("phone");
								student.setPhone(phone);
								break;

							case 8:
								String groupName = DataInput.requireString("group name");
								student.setGroupName(groupName);
								break;

							case 9:
								double scholarship = DataInput.requireDouble("scholarship");
								student.setScholarship(scholarship);
								break;

							case 10:
								break loop;

							default:
								throw new InputMismatchException();
						}
					}
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
				System.out.println("ADD STUDENT");
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
				String name = DataInput.requireString("name");
				String surname = DataInput.requireString("surname");
				int age = DataInput.requireInt("age");
				String email = DataInput.requireString("email");
				String phone = DataInput.requireString("phone");
				String groupName = DataInput.requireString("group name");
				double scholarship = DataInput.requireDouble("scholarship");

				Student student = new Student(username, password);
				student.setGroupName(groupName).setScholarship(scholarship).setName(name).setSurname(surname).setAge(age).setEmail(email).setPhone(phone);

				DataUtil.addUser(student);
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
				if (!DataOutput.showStudents(true))
					break;

				int nOption = DataInput.requireOption();

				if (nOption == 0)
					break;

				int nStudentIndex = nOption - 1;
				List<Student> lsStudents = DataUtil.getStudents();

				if (lsStudents.size() >= nOption && nOption >= 1)
				{
					Student student = DataUtil.getStudents().get(nStudentIndex);
					DataOutput.showStudentData(student);
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

	public static void processPanel()
	{
		loop:
		while (true)
		{
			try
			{
				System.out.println("""
						MODE: STUDENT
						1. Show my info
						2. Show subjects
						3. Select subjects
						4. Show scholarship
						5. Log off
						6. Close application
						""");

				int nOption = DataInput.requireOption();

				switch (nOption)
				{
					case 1:
						Student student = (Student) Application.getCurrentUser();
						DataOutput.showStudentData(student);
						break;

					case 2:
						student = (Student) Application.getCurrentUser();
						HashMap<String, Integer> hmSubjects = student.getHmSubjects();

						if(hmSubjects!= null && !student.getHmSubjects().isEmpty())
						{
							for (Map.Entry<String, Integer> entry : student.getHmSubjects().entrySet())
								System.out.println(entry.getKey() + " : " + entry.getValue());

							System.out.println();
						}
						else
							System.out.println("There are no any subjects\n");

						break;

					case 3:
						processSelectSubjects();
						break;

					case 4:
						student = (Student) Application.getCurrentUser();
						System.out.println("My scholarship: " + student.getScholarship() + "\n");
						break;

					case 5:
						Application.unAuthorize();
						Interface.start();

					case 6:
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

	public static void processSelectSubjects()
	{
		while(true)
		{
			try
			{
				if (!DataOutput.showSubjectsSelected())
					break;

				int nOption = DataInput.requireInt("number of subject to select/deselect");

				if(nOption == 0)
					break;

				int nTeacherIndex = nOption - 1;

				List<Teacher> lsTeachers = DataUtil.getTeachers();

				if(lsTeachers.size() >= nOption && nOption >= 1)
				{
					Teacher teacher = lsTeachers.get(nTeacherIndex);
					String subject = teacher.getSubject();
					Student curStudent = (Student) Application.getCurrentUser();

					if(curStudent.getHmSubjects().containsKey(subject))
						curStudent.getHmSubjects().remove(subject);
					else
						curStudent.getHmSubjects().put(subject, null);
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
