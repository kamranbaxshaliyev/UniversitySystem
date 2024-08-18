import app.Application;

public class Main
{
	public static void main(String[] args)
	{
		if(Application.onStart())
		{
			Application.start();
			Application.onExit();
		}
	}
}