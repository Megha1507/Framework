package autoFramework;

import org.openqa.selenium.WebDriver;

public class Action 
{
	public static WebDriver driver;
	public static Object key;
	static Object ob = new Object();
	public static void act(String action,String object , String objpath, String value) throws InterruptedException
	{
		
		if(action.equals("openBrowser"))
		{			
			Object.openBrowser(objpath);
		}
	
		else if(action.equals("navigate"))
		{
			
			Object.navigate(objpath);
		
		}
		else if(action.equals("click"))
		{
			Thread.sleep(3000);
			
			Object.click(objpath);
		}
		else if(action.equals("type"))
		{
			Thread.sleep(3000);
			
			Object.type(objpath , value);
		}
	}	
}
