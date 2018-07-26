package autoFramework;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MainClass
{
	
	public static String objectName ;	
	public static String action ;	
	public static String Value ;
	public static String objectN;	
	public static String objpath;
	public static Object key;
	public static Method[] method;
	public static String sheetName;
	public static String ORsheetName;
	public static String ORsheetname;
	public static XSSFWorkbook wbObj;
	public static String ORSheetNameValue;
	public static XSSFSheet SheetObj ;
	public static String ORSheN;
	
	public static void primary(String sheetN) throws InterruptedException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		
			String spath = "D:\\Selenium\\eclipse-workspace\\NewProjectSelenium\\KeyExcel.xlsx";
			
			FileInputStream fis = new FileInputStream(spath);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet Sheet = wb.getSheetAt(0);
		
			sheetName = Sheet.getSheetName();
			System.out.println(sheetName);
							
			int rowCount = Sheet.getLastRowNum();
			//rowCount = rowCount + 1;
			
			Row row = Sheet.getRow(0);
			
			int ColCount = row.getLastCellNum();
								
			System.out.println("rowCount: " + rowCount);
			System.out.println("Colcount: " + ColCount);
			
			// To compare Name of Test case Sheet in Testsuite and Testcase Sheet Name 
			if(sheetN.equals(sheetName))
			{
				
			// Fetching ObjectRepo name from Test Case sheet
				
				String ORSheetName = Sheet.getRow(1).getCell(0).getStringCellValue();
				
				ORSheetNameValue = Sheet.getRow(1).getCell(1).getStringCellValue();
				
				System.out.println(ORSheetName + ", "+ ORSheetNameValue);
				
				objectRe(ORSheetNameValue);
				
				if(ORSheN.equals(ORSheetNameValue))
				{
					for(int i = 3 ; i <= rowCount ; i ++)
					{
								
					action = Sheet.getRow(i).getCell(0).getStringCellValue();
				
					System.out.println(action);
					objectName = Sheet.getRow(i).getCell(1).getStringCellValue();
					System.out.println(objectName);
					
					Value= Sheet.getRow(i).getCell(2).getStringCellValue();
		
					System.out.println(Value);
					
					getObjectRepoVar(objectName);
					}
					
				}
				else
					{
					
					exit();
					
					}
			}
			else
			{
				System.out.println("Sheet doesnt exist. Please check SheetName");
				exit();
			}
		}
			
	
	public static void objectRe(String ORSheetNameValue) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException
		{
			String spathObj = "D:\\Selenium\\eclipse-workspace\\NewProjectSelenium\\Objectrepo.xlsx";
			
			FileInputStream fisObj = new FileInputStream(spathObj);
				
			wbObj = new XSSFWorkbook(fisObj);
				
			SheetObj =  wbObj.getSheetAt(0);
			
			for(int i = 0 ; i < wbObj.getNumberOfSheets() ; )
			{
			ORsheetname = wbObj.getSheetName(i);
			
				if(ORsheetname.equals(ORSheetNameValue))
				{
					// Go back to test case sheet and increment
					
					System.out.println(ORsheetname + "and " + ORSheetNameValue + "OR sheet found");
					ORSheN = ORSheetNameValue;
					break;
					// Start reading variables of Test Case sheet and OR sheet
					//getObjectRepoVar(objectNM);
				}
				else
				{
					
					System.out.println(ORSheetNameValue + " and " + ORsheetname + " doesnt match." + " Sheet not found");
					i++;
					ORSheN = "ORsheet Not Found";
					
				}
				
				
			}
		}	
	private static void compare() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException 
		{
			key = new Object();
			
			method = key.getClass().getMethods();
			
			
			for(int i = 0;i<method.length;i++)
			 {
				System.out.println(method[i]);
				 if(method[i].getName().equals(action))
				 {
					 //objectRepo();
					 Action.act(action, objectName , objpath, Value);
					 break;
				 }
			 }
		}
			
	private static void getObjectRepoVar(String objectNM) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException 
	{
		int rowC = SheetObj.getLastRowNum();
		
		Row row1 = SheetObj.getRow(0);
					
		int ColC = row1.getLastCellNum();
		
		System.out.println("Object repo sheet rowCount: " + rowC);
		System.out.println("Object repo sheet Colcount: " + ColC);	
		
		for(int j = 1; j <= rowC ;)
		{
								
			objectN = SheetObj.getRow(j).getCell(0).getStringCellValue();
			
			objpath = SheetObj.getRow(j).getCell(1).getStringCellValue();
				
			System.out.println(objectN +"  "+ objpath  );
	
				if(objectName.equals(objectN))
				{
					compare();
					break;
				}
				else 
				{
					j++;
				}
		}
		
	}
	
	public static void exit()
	{
		System.exit(0);
	}

	public static void main(String[] args) throws IOException, InterruptedException 
			{
				
				try
				{					
					testSuite.readTestSuite();					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				
			}

	}
