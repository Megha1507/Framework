package autoFramework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
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
	public static void primary() throws InterruptedException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		
			String spath = "D:\\Selenium\\eclipse-workspace\\NewProjectSelenium\\KeyExcel.xlsx";
			
			FileInputStream fis = new FileInputStream(spath);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet Sheet = wb.getSheetAt(0);
		
			sheetName = Sheet.getSheetName();
			System.out.println(sheetName);
			
		// To get sheetName of Test script sheet and compare with Test Suite sheet 
			testSuite.readTestSuite(sheetName);
			
			int rowCount = Sheet.getLastRowNum();
			//rowCount = rowCount + 1;
			
			Row row = Sheet.getRow(0);
			
			int ColCount = row.getLastCellNum();
								
			System.out.println("rowCount: " + rowCount);
			System.out.println("Colcount: " + ColCount);
									
			
			for(int i = 1 ; i <= rowCount ; i ++)
				{
							
				action = Sheet.getRow(i).getCell(0).getStringCellValue();
			
				System.out.println(action);
				objectName = Sheet.getRow(i).getCell(1).getStringCellValue();
				System.out.println(objectName);
				
				
				Value= Sheet.getRow(i).getCell(2).getStringCellValue();
	
				System.out.println(Value);
					
					String spathObj = "D:\\Selenium\\eclipse-workspace\\NewProjectSelenium\\Objectrepo.xlsx";
					
					FileInputStream fisObj = new FileInputStream(spathObj);
					
					XSSFWorkbook wbObj = new XSSFWorkbook(fisObj);
					
					XSSFSheet SheetObj = wbObj.getSheet("Sheet1");
					
					
					int rowC = SheetObj.getLastRowNum();
					//rowCount = rowCount + 1;
					
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
	
					
	public static void main(String[] args) throws IOException, InterruptedException 
			{
				
				try
				{
					
					primary();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				
			}

	}
