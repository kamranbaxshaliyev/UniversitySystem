package util;

import java.io.*;

public class FileUtil
{
	public static boolean writeObject(Object object, String sFilename)
	{
		try(FileOutputStream oFileStream = new FileOutputStream(sFilename);
			ObjectOutputStream oStream = new ObjectOutputStream(oFileStream))
		{
			oStream.writeObject(object);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	public static Object readObject(String sFilename)
	{
		try(FileInputStream oFileStream = new FileInputStream(sFilename);
			ObjectInputStream oStream = new ObjectInputStream(oFileStream))
		{
			return oStream.readObject();
		}
		catch(Exception e)
		{
			return null;
		}
	}

	public static boolean deleteObject(String sFileName)
	{
		return new File(sFileName).delete();
	}
}
