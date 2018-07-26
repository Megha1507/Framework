package autoFramework;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;



public class CaptureScreenshot extends Object
{ 
	// WebDriver driver;
	static void getscreenshot() throws Exception 
    {
                      
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			
			String filename =  new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
            File dest = new File("D:\\Selenium\\eclipse-workspace\\NewProjectSelenium\\FailureScreenshots\\" + filename + ".png");

			//The below method will save the screen shot in d drive with name "screenshot.png"
            FileUtils.copyFile(scrFile, dest);
            
            
                }
}
