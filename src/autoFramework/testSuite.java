package autoFramework;

import java.io.FileInputStream;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;


@Test(priority =1 )

public class TestSuite 
{
	static Row row ;
	public static String celldata;
	public static String  TestSheet_Name;
	
	public static  String Run_Flag;
	
	static LinkedHashMap<String,String> hm = new LinkedHashMap<String,String>();
	
	 	
	public static void readTestSuite() throws Exception
	{
		//Using Log4j to generate Log files
		Logger logger =  Logger.getLogger("TestSuite");
	    PropertyConfigurator.configure("Log4j.properties");
		
	    logger.info("----------------Beginning of TestSuite.xlsx Excel sheet----------------");
	    
		String testSuitePath= "D:\\Selenium\\eclipse-workspace\\NewProjectSelenium\\TestSuite.xlsx";
		
		logger.info("TestSuite.xlsx sheet at location: " + testSuitePath );	
		
		FileInputStream testSuitefis = new FileInputStream(testSuitePath);
		
		XSSFWorkbook testSuiteWb = new XSSFWorkbook(testSuitefis);
		
		XSSFSheet testSuiteSheet = testSuiteWb.getSheet("Sheet1");
		
		int testSuiteRow = testSuiteSheet.getLastRowNum();
		
		//row = testSuiteSheet.getRow(0);
		
		//int testSuiteCol = row.getLastCellNum();
		
		//System.out.println("TestSuite col count = " + testSuiteCol);	
		System.out.println("TestSuite row count = " + testSuiteRow);
		
		for(int i = 1 ; i <= testSuiteRow ; i++)
		{
		
			Run_Flag = testSuiteSheet.getRow(i).getCell(0).getStringCellValue();
			TestSheet_Name = testSuiteSheet.getRow(i).getCell(1).getStringCellValue();
			
			logger.info("Run_Flag is : " + Run_Flag +" and TestSheet_Name is :  " + TestSheet_Name +  " found");
			
			if(Run_Flag.equals("Y"))
			{
				TestScript.testScript(TestSheet_Name);
								
			}
			else if(Run_Flag.equals("N"))
			{
				i++;
			}
	
		}
		//testSuiteWb.close();
		logger.info("----------------Execution ENDED----------------");
	}	
}	
	