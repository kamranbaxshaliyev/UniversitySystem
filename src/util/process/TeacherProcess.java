package util.process;

import app.Application;
import app.Interface;
import bean.Student;
import bean.Teacher;
import bean.User;
import util.DataInput;
import util.DataOutput;
import util.DataUtil;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;

public class TeacherProcess
{
	public static void processDelete()
	{
		while (true)
		{
			try
			{
				if(!DataOutput.showTeachers(false))
					break;

				int nOption = DataInput.requireOption();

				if (nOption == 0) break;

				int nTeacherIndex = nOption - 1;
				List<Teacher> lsTeachers = DataUtil.getTeachers();

				if (lsTeachers.size() >= nOption && nOption >= 1)
				{
					Teacher teacher = lsTeachers.get(nTeacherIndex);
					System.out.println("Are you sure you want to delete user " + teacher.getName() + " " + teacher.getSurname() + "?");
					String sApprove = DataInput.requireString("letter (Y | N)");

					if (sApprove.equalsIgnoreCase("Y"))
					{
						deleteOldSubjectFromStudentList(teacher.getSubject());
						DataUtil.removeUser(teacher);
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

	public static void processEdit()
	{
		while (true)
		{
			try
			{
				if(!DataOutput.showTeachers(true))
					break;

				int nOption = DataInput.requireOption();

				if (nOption == 0) break;

				int nTeacherIndex = nOption - 1;

				List<Teacher> lsTeachers = DataUtil.getTeachers();

				if (lsTeachers.size() >= nOption && nOption >= 1)
				{
					loop:
					while (true)
					{
						Teacher teacher = lsTeachers.get(nTeacherIndex);
						DataOutput.showTeacherData(teacher);
						System.out.println("EDIT");
						System.out.println("""
							1. Username 2. Password 3. Name    4. Surname 5. Age
							6. Email    7. Phone    8. Subject 9. Salary  10. Exit
							""");
						nOption = DataInput.requireOption();

						switch (nOption)
						{
							case 1:
								String username = DataInput.requireString("username");
								teacher.setUsername(username);
								break;

							case 2:
								String password = DataInput.requireString("password");
								teacher.setUsername(password);
								break;

							case 3:
								String name = DataInput.requireString("name");
								teacher.setName(name);
								break;

							case 4:
								String surname = DataInput.requireString("surname");
								teacher.setSurname(surname);
								break;

							case 5:
								int age = DataInput.requireInt("age");
								teacher.setAge(age);
								break;

							case 6:
								String email = DataInput.requireString("email");
								teacher.setEmail(email);
								break;

							case 7:
								String phone = DataInput.requireString("phone");
								teacher.setPhone(phone);
								break;

							case 8:
								String subject = DataInput.requireString("subject");

								if(DataUtil.getSubjects().contains(subject))
								{
									System.out.println("This subject is already set to one of the teachers");
								}
								else
								{
									deleteOldSubjectFromStudentList(teacher.getSubject());
									teacher.setSubject(subject);
								}
								break;

							case 9:
								double salary = DataInput.requireDouble("salary");
								teacher.setSalary(salary);
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
				System.out.println("ADD TEACHER");
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
				double salary = DataInput.requireDouble("salary");
				String subject = DataInput.requireString("subject");

				Teacher teacher = new Teacher(username, password);
				teacher.setSalary(salary).setSubject(subject).setName(name).setSurname(surname).setAge(age).setEmail(email).setPhone(phone);

				DataUtil.addUser(teacher);
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
				if (!DataOutput.showTeachers(true))
					break;

				int nOption = DataInput.requireOption();

				if (nOption == 0)
					break;

				int nTeacherIndex = nOption - 1;
				List<Teacher> lsTeachers = DataUtil.getTeachers();

				if (lsTeachers.size() >= nOption && nOption >= 1)
				{
					Teacher teacher = DataUtil.getTeachers().get(nTeacherIndex);
					DataOutput.showTeacherData(teacher);
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
						MODE: TEACHER
						1. Show students
						2. Evaluate students
						3. Show salary
						4. Log off
						5. Close application
						""");

				int nOption = DataInput.requireOption();

				switch (nOption)
				{
					case 1:
						DataOutput.showStudentsForTeacher(true);
						break;

					case 2:
						processEvaluateStudents();
						break;

					case 3:
						System.out.println("My salary: " + ((Teacher) Application.getCurrentUser()).getSalary() + "\n");
						break;

					case 4:
						Application.unAuthorize();
						Interface.start();

					case 5:
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

	private static void processEvaluateStudents()
	{
		while (true)
		{
			try
			{
				if(!DataOutput.showStudentsForTeacher(false))
					break;

				int nOption = DataInput.requireOption();

				if (nOption == 0)
					break;

				int nStudentIndex = nOption - 1;
				List<Student> lsStudents = DataUtil.getStudents();

				if (lsStudents.size() >= nOption && nOption >= 1)
				{
					Student student = lsStudents.get(nStudentIndex);
					String subject = ((Teacher) Application.getCurrentUser()).getSubject();
					student.getHmSubjects().put(subject, DataInput.requireInt("grade"));
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

	private static void deleteOldSubjectFromStudentList(String subject)
	{
		for (Student student : DataUtil.getStudents())
			student.getHmSubjects().remove(subject);
	}
}
