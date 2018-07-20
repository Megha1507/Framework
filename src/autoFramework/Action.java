package autoFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Action 
{
	public static WebDriver driver;
	public static Object key;
	
	public static void act(String action,String object , String objpath) throws InterruptedException
	{
		
		if(action.equals("openBrowser") && object.equals("Chrome"))
		{			
			driver = new FirefoxDriver();
		}
		else if(action.equals("navigate"))
		{
			Thread.sleep(3000);
			driver.get(object);
		
		}
		else if(action.equals("click"))
		{
			Thread.sleep(3000);
			driver.findElement(By.xpath(object)).click();
		}
		else if(action.equals("type"))
		{
			driver.findElement(By.xpath(object)).sendKeys(objpath);
		}
	}	
}
