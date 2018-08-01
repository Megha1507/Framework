package autoFramework;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Object 
{
	static Logger logger =  Logger.getLogger("Object");
	static WebDriver driver;
	
	public static void openBrowser(String objPath) throws Exception
	{
		try
		{
			if(objPath.equals("Firefox"))
			{
				driver = new FirefoxDriver();
				
				logger.info("Open Firefox Browser");
			}
			else if(objPath.equals("Chrome"))
			{
				driver = new ChromeDriver();
				
				driver.manage().window().maximize();
				
				logger.info("Open Chrome Browser and maximise");
			}
		} 
		catch (Exception e) 
		{ 
			CaptureScreenshot.getscreenshot();
			logger.info("Failed to Open Browser");
		}
	}
	public static void navigate(String objpath) throws Exception 
	{
		try 
		{
			driver.get(objpath);
		} 
		catch (Exception e) 
		{
			CaptureScreenshot.getscreenshot();
			logger.info("Unable to navigate to given URL");
		}		
	}
	
	public static void click(String objpath ) throws Exception
	{
		try 
		{
			driver.findElement(By.xpath(objpath)).click();
			
		} 
		catch (Exception e) 
		{
			CaptureScreenshot.getscreenshot();
			
			logger.info("Click method Failed");
		}
	}
	
	public static void type(String objpath, String value) throws Exception
	{
		try 
		{
			driver.findElement(By.xpath(objpath)).sendKeys(value);
			
		} 
		catch (Exception e) 
		{
			CaptureScreenshot.getscreenshot();
			
			logger.info("Type method Failed" + " , " + objpath + " , " + value );
		}
	}
		
}
