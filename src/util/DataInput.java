package util;

import java.util.Scanner;

public class DataInput
{
	public static int requireOption()
	{
		Scanner oScanner = new Scanner(System.in);
		System.out.print("Select an option: ");
		int nReturn = oScanner.nextInt();
		System.out.println();

		return nReturn;
	}

	public static String requireString(String string)
	{
		Scanner oScanner = new Scanner(System.in);
		System.out.print("Enter the " + string + ": ");
		String sReturn = oScanner.nextLine();
		System.out.println();

		return sReturn;
	}

	public static int requireInt(String string)
	{
		Scanner oScanner = new Scanner(System.in);
		System.out.print("Enter the " + string + ": ");
		int nReturn = oScanner.nextInt();
		System.out.println();

		return nReturn;
	}

	public static double requireDouble(String string)
	{
		Scanner oScanner = new Scanner(System.in);
		System.out.print("Enter the " + string + ": ");
		double nReturn = oScanner.nextDouble();
		System.out.println();

		return nReturn;
	}
}
