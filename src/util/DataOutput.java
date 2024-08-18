package util;

import app.Application;
import bean.Admin;
import bean.Student;
import bean.Teacher;

import java.util.List;

public class DataOutput
{
	public static void showSubjects()
	{
		System.out.println("SHOW SUBJECTS");
		List<Teacher> lsTeachers = DataUtil.getTeachers();

		if(!lsTeachers.isEmpty())
		{
			for (Teacher teacher : lsTeachers)
				System.out.println(teacher.getSubject() + ": " + teacher.getName() + " " + teacher.getSurname());

			System.out.println();
		}
		else
			System.out.println("There are no any subjects\n");
	}

	public static boolean showSubjectsSelected()
	{
		boolean bSubjects = false;
		System.out.println("SHOW SUBJECTS");
		List<Teacher> lsTeachers = DataUtil.getTeachers();

		if(!lsTeachers.isEmpty())
		{
			bSubjects = true;
			Student student = (Student) Application.getCurrentUser();

			int i = 1;
			for (Teacher teacher : lsTeachers)
			{
				String sSelected = "";

				if (student.getHmSubjects().containsKey(teacher.getSubject()))
						sSelected = " - Selected";

				System.out.println((i++) + ". " + teacher.getSubject() + ": " + teacher.getName() + " " + teacher.getSurname() + sSelected);
			}

			System.out.println("\n0. Exit\n");
		}
		else
			System.out.println("There are no any subject");

		return bSubjects;
	}

	public static void showStudentData(Student student)
	{
		System.out.println("INFO OF STUDENT " + student.getName() + " " + student.getSurname() + ":");
		System.out.println("Username: " + student.getUsername());
		System.out.println("Password: " + student.getPassword());
		System.out.println("Role: Student");
		System.out.println("Name: " + student.getName());
		System.out.println("Surname: " + student.getSurname());
		System.out.println("Age: " + student.getAge());
		System.out.println("Email: " + student.getEmail());
		System.out.println("Phone: " + student.getPhone());
		System.out.println("Group name: " + student.getGroupName());
		System.out.println("Scholarship: " + student.getScholarship());
		System.out.println();
	}

	public static void showTeacherData(Teacher teacher)
	{
		if(teacher == null)
		{
			showSystemError();
			return;
		}

		System.out.println("INFO OF TEACHER " + teacher.getName() + " " + teacher.getSurname() + ":");
		System.out.println("Username: " + teacher.getUsername());
		System.out.println("Password: " + teacher.getPassword());
		System.out.println("Role: Teacher");
		System.out.println("Name: " + teacher.getName());
		System.out.println("Surname: " + teacher.getSurname());
		System.out.println("Age: " + teacher.getAge());
		System.out.println("Email: " + teacher.getEmail());
		System.out.println("Phone: " + teacher.getPhone());
		System.out.println("Subject: " + teacher.getSubject());
		System.out.println("Salary: " + teacher.getSalary());
		System.out.println();
	}

	public static void showAdminData(Admin admin)
	{
		if(admin == null)
		{
			showSystemError();
			return;
		}

		System.out.println("INFO OF ADMIN " + admin.getUsername() + ":");
		System.out.println("Username: " + admin.getUsername());
		System.out.println("Password: " + admin.getPassword());
		System.out.println("Role: Admin");
		System.out.println();
	}

	public static void showAdmins(boolean bShow)
	{
		List<Admin> lsAdmins = DataUtil.getAdmins();

		if (bShow)
			System.out.println("SHOW ADMINS");
		else
			System.out.println("DELETE ADMIN");

		for (int i = 0; i < lsAdmins.size(); i++)
			System.out.println((i + 1) + ". " + lsAdmins.get(i).getUsername());


		System.out.println("\n0. Exit");
	}

	public static boolean showTeachers(boolean bShow)
	{
		boolean bTeachers = false;
		List<Teacher> lsTeachers = DataUtil.getTeachers();

		if (!lsTeachers.isEmpty())
		{
			bTeachers = true;

			if (bShow) System.out.println("SHOW TEACHERS");
			else System.out.println("DELETE TEACHER");

			for (int i = 0; i < lsTeachers.size(); i++)
				System.out.println((i + 1) + ". " + lsTeachers.get(i).getName() + " " + lsTeachers.get(i).getSurname());

			System.out.println("\n0. Exit");
		} else System.out.println("There are no any teachers\n");

		return bTeachers;
	}

	public static boolean showStudents(boolean bShow)
	{
		boolean bStudents = false;
		List<Student> lsStudents = DataUtil.getStudents();

		if (!lsStudents.isEmpty())
		{
			bStudents = true;
			if (bShow) System.out.println("SHOW STUDENTS");
			else System.out.println("DELETE STUDENT");

			for (int i = 0; i < lsStudents.size(); i++)
				System.out.println((i + 1) + ". " + lsStudents.get(i).getName() + " " + lsStudents.get(i).getSurname());

			System.out.println("\n0. Exit");
		} else System.out.println("There are no any students\n");

		return bStudents;
	}

	public static void showUserOptions()
	{
		System.out.println("""
				1. Admin
				2. Teacher
				3. Student
				4. Exit
				""");
	}

	public static void showWrongOptionMess()
	{
		// ANSI escape code for red text
		String redText = "\u001B[31m";

		// ANSI escape code to reset the color
		String resetText = "\u001B[0m";

		System.out.println(redText + "Wrong option selected. Please try again\n" + resetText);
	}

	public static void showSystemError()
	{
		// ANSI escape code for red text
		String redText = "\u001B[31m";

		// ANSI escape code to reset the color
		String resetText = "\u001B[0m";

		System.out.println(redText + "System error" + resetText);
	}

	public static void showWrongLoginMess()
	{
		// ANSI escape code for red text
		String redText = "\u001B[31m";

		// ANSI escape code to reset the color
		String resetText = "\u001B[0m";

		System.out.println(redText + "Incorrect login or password" + resetText);
	}

	public static boolean showStudentsForTeacher(boolean bShow)
	{
		boolean bStudents = false;
		List<Student> lsStudents = DataUtil.getStudents();

		if (!lsStudents.isEmpty())
		{
			bStudents = true;
			String subject = ((Teacher) Application.getCurrentUser()).getSubject();
			System.out.println(subject.toUpperCase());
			System.out.println("ID | NAME       | SURNAME         | GRADE | GROUP  | EMAIL                          | PHONE        ");
			System.out.println("---------------------------------------------------------------------------------------------------");

			for (int i = 0; i < lsStudents.size(); i++)
			{
				Student student = lsStudents.get(i);
				String name = student.getName();
				String surname = student.getSurname();
				String group = student.getGroupName();
				String email = student.getEmail();
				String phone = student.getPhone();
				int grade = student.getHmSubjects().get(subject) == null ? 0 : student.getHmSubjects().get(subject);

				System.out.println((i + 1) + " ".repeat(3 - intSymbolCount(i + 1)) + "| "
						+ name + " ".repeat(11 - name.length()) + "| "
						+ surname + " ".repeat(16 - surname.length()) + "| "
						+ grade + " ".repeat(6 - intSymbolCount(grade)) + "| "
						+ group + " ".repeat(7 - group.length()) + "| "
						+ email + " ".repeat(31 - email.length()) + "| "
						+ phone);
			}

			System.out.println("---------------------------------------------------------------------------------------------------");

			if(!bShow)
				System.out.println("\n0. Exit");
		}
		else
			System.out.println("There are no any students\n");

		return bStudents;
	}

	private static int intSymbolCount(int num)
	{
		int count = 0;

		if (num == 0)
			count++;

		while (num != 0)
		{
			num /= 10;
			count++;
		}

		return count;
	}

	private static int doubleSymbolCount(double num)
	{
		int nInt = (int) num;
		int count = 2;

		if (nInt == 0) count++;

		while (nInt != 0)
		{
			nInt /= 10;
			count++;
		}

		return count;
	}
}