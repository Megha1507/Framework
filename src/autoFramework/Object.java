package autoFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Object 
{
	static WebDriver driver;
	
	public static void openBrowser(String objPath)
	{
		if(objPath.equals("Firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(objPath.equals("Chrome"))
		{
			driver = new ChromeDriver();
			
			driver.manage().window().maximize();
		}
	}
	public static void navigate(String objpath) 
	{
		driver.get(objpath);		
	}
	public static void click(String objpath )
	{
		driver.findElement(By.xpath(objpath)).click();
	}
	public static void type(String objpath, String value)
	{
		driver.findElement(By.xpath(objpath)).sendKeys(value);
	}
		
}
