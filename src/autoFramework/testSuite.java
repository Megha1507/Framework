package autoFramework;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class testSuite 
{
	static Row row ;
	public static String celldata;
	public static String  TestSheet_Name;
	
	public static  String Run_Flag;
	
	static LinkedHashMap<String,String> hm = new LinkedHashMap<String,String>();
	
	public static void readTestSuite() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException
	{
		String fileP= "D:\\Selenium\\eclipse-workspace\\NewProjectSelenium\\TestSuite.xlsx";
		
		FileInputStream fis = new FileInputStream(fileP);
		
		XSSFWorkbook w = new XSSFWorkbook(fis);
		
		XSSFSheet Sheet = w.getSheet("Sheet1");
		
		int ORrow = Sheet.getLastRowNum();
	
		row = Sheet.getRow(0);
		
		int Col = row.getLastCellNum();
		
		System.out.println("col count" + Col);	
		System.out.println("row count" + ORrow);
		
		for(int i = 1 ; i <= ORrow ; )
		{
		
			Run_Flag = Sheet.getRow(i).getCell(0).getStringCellValue();
			TestSheet_Name = Sheet.getRow(i).getCell(1).getStringCellValue();
			
			if(Run_Flag.equals("Y"))
			{
				MainClass.primary(TestSheet_Name);
			}
			else if(Run_Flag.equals("N"))
			{
				i++;
			}
	
		}
	}
}		
	