package autoFramework;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestSuite 
{
	static Row row ;
	public static String celldata;
	public static String  TestSheet_Name;
	
	public static  String Run_Flag;
	
	static LinkedHashMap<String,String> hm = new LinkedHashMap<String,String>();
	
	public static void readTestSuite() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException
	{
		String testSuitePath= "D:\\Selenium\\eclipse-workspace\\NewProjectSelenium\\TestSuite.xlsx";
		
		FileInputStream testSuitefis = new FileInputStream(testSuitePath);
		
		XSSFWorkbook testSuiteWb = new XSSFWorkbook(testSuitefis);
		
		XSSFSheet testSuiteSheet = testSuiteWb.getSheet("Sheet1");
		
		int testSuiteRow = testSuiteSheet.getLastRowNum();
	
		//row = testSuiteSheet.getRow(0);
		
		//int testSuiteCol = row.getLastCellNum();
		
		//System.out.println("TestSuite col count = " + testSuiteCol);	
		System.out.println("TestSuite row count = " + testSuiteRow);
		
		for(int i = 1 ; i <= testSuiteRow ; )
		{
		
			Run_Flag = testSuiteSheet.getRow(i).getCell(0).getStringCellValue();
			TestSheet_Name = testSuiteSheet.getRow(i).getCell(1).getStringCellValue();
			
			if(Run_Flag.equals("Y"))
			{
				MainClass.testScript(TestSheet_Name);
				i++;
			}
			else if(Run_Flag.equals("N"))
			{
				i++;
			}
	
		}
		testSuiteWb.close();
	}
}		
	