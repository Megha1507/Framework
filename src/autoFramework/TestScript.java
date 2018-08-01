package autoFramework;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.log4j.Logger;

import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class TestScript
{
	
	public static XSSFCell objectName ;	
	public static XSSFCell action ;	
	public static XSSFCell Value ;
	public static String objName;	
	public static String objpath;
	public static Object key;
	public static Method[] method;
	public static String sheetName;
	public static String ORsheetName;
	public static String ORsheetname;
	public static XSSFWorkbook Obj_repo_wb;
	public static String ORSheetNameValue;
	public static XSSFSheet SheetObj ;
	public static String obj_sheetname;
	static String objectName2;
	public static String action2 ;
	static String Value2;
	static Logger logger =  Logger.getLogger("MainClass");
  
    
	// This function is reading the TestScript Sheet
	public static void testScript(String testSheetName) throws Exception
	{
			logger.info("Accessing Log file in Main Class");
			
			PropertyConfigurator.configure("Log4j.properties");
		    
			logger.info("Beginning of TestScript.xlsx Excel sheet");
		
			String testScriptPath = "D:\\Selenium\\eclipse-workspace\\NewProjectSelenium\\TestScript.xlsx";
			
			FileInputStream testScriptfis = new FileInputStream(testScriptPath);
			
			XSSFWorkbook testScriptWb = new XSSFWorkbook(testScriptfis);
			
			for(int i = 0 ; i < testScriptWb.getNumberOfSheets() ; i++ )
				
			{
			XSSFSheet Sheet = testScriptWb.getSheetAt(i);
			
			int testScriptRows = Sheet.getLastRowNum();
			
			//Row row = Sheet.getRow(0);
			
			//int testScriptCols = row.getLastCellNum();
								
			//logger.info("Counting no. of Rows in TestScript.xlsx : " + testScriptRows);
				
			//System.out.println("TestScript Col count: " + testScriptCols);
				
			
			
			logger.info("Fetching all the Sheets under TestScript.xlsx");
				
			sheetName = testScriptWb.getSheetName(i);
				System.out.println(sheetName);
				
			// This loop checks if the Test Case name matches
				if(testSheetName.equals(sheetName))
				{
					logger.info("TestScript Found . Comparing TestScript Sheet name");
			// Read Object Repo Sheet name Linked in TestScipt
				
					String ORSheetName = Sheet.getRow(1).getCell(0).getStringCellValue();
					
					ORSheetNameValue = Sheet.getRow(1).getCell(1).getStringCellValue();
				
					System.out.println(ORSheetName + ", "+ ORSheetNameValue);
					
					logger.info("Checking for the Linked ObjectRepo Sheet");
					
					objectRepoScript(ORSheetNameValue);
					
				
			// Checking if Object Repo Sheet linked in Test Case Sheet is available in Object Repo workbook.	
				if(obj_sheetname.equals(ORSheetNameValue))
				{
					for(int j = 3 ; j <= testScriptRows ; j ++)
					{
					
					logger.info("Accessing TestCase Variables in TestScript");
					
					action = Sheet.getRow(j).getCell(0);			
						
					System.out.println("action = " + action);
					
					objectName = Sheet.getRow(j).getCell(1);
					
					action2 = action.toString(); 
					objectName2 = objectName.toString(); 
					
					System.out.println("objectName= " + objectName);
					
					
					Value= Sheet.getRow(j).getCell(2);
					
					
					System.out.println("Value = " + Value);
					
					if(Value != null )
					{
						Value2 = Value.toString(); 
					}
					getObjectRepoVar(objectName2);				
					/*	if(action.toString().equalsIgnoreCase("stop"))
					{
						System.out.println("null execute");
						//continue;
						
					}*/
					}
				}
		// If Linked Object Repo Sheet doesn't match with any sheet of the Object Repo Workbook then this loop will execute and end the Execution.
				else
					{
					logger.info("ObjectRepo Sheet name Not Found");
					//exit();
					
					}
			}
			/*else if(action2.equals("stop"))
			{
				return;
			}*/
			/*else 
				{
				System.out.println("TestScript "+ testSheetName + " doesnt exist. Please check Sheet Name");
				return;
				//logger.info("TestScript Not Found");
				}
				*/
				//exit();
			}
			
		//		testScriptWb.close();
		}
	

	//This Method is to read  the Object Repo Sheet 
	
	public static void objectRepoScript(String ORSheetNameValue) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException
		{
		
			logger.info("Accessing ObjectRepo Script");
		
			String objRepoPath = "D:\\Selenium\\eclipse-workspace\\NewProjectSelenium\\Objectrepo.xlsx";
			
			FileInputStream Obj_fis = new FileInputStream(objRepoPath);
				
			Obj_repo_wb = new XSSFWorkbook(Obj_fis);
				
			
			
			// This loop is to search the Object Repo Sheet linked with Test Script. Comparing the Object Repo Sheet Names
				
			for(int i = 0 ; i < Obj_repo_wb.getNumberOfSheets() ; )
				
			{
			SheetObj =  Obj_repo_wb.getSheetAt(i);
			
			logger.info("Fetching all the Sheets under Object Repo.xlsx");
				
			ORsheetname = Obj_repo_wb.getSheetName(i);
				
			logger.info("Searching for the Object Repo Sheet specified in Test Script");
			
				if(ORsheetname.equals(ORSheetNameValue))
				{
					// Go back to test case sheet and increment
					
					System.out.println(ORsheetname + " and " + ORSheetNameValue + " Object Repo sheet found");
					
					logger.info("Object Repo Sheet Found");
					
					obj_sheetname = ORSheetNameValue;
					
					break;
				}
				else
				{
					
					System.out.println(ORSheetNameValue + " and " + ORsheetname + " doesnt match." + " Sheet not found");
					i++;
					obj_sheetname = "Object Repo sheet Not Found";
					
					logger.info("Object Repo Sheet Not Found");
					
				}		
			}
		}	
	
	// This Method is to retrieve all "Methods" of class "Object" and compare with action values present in TestScript
	private static void getObjMethods() throws Exception 
		{
			key = new Object();
			
			method = key.getClass().getMethods();
			
			logger.info("Accessing all the methods of Class Object");
			
			for(int i = 0;i<method.length;i++)
			 {
				//System.out.println(method[i]);
				 if(method[i].getName().equals(action2))
				 {
					
					 Action.act(action2, objectName2 , objpath, Value2);
					 break;
				 }
			 }
		}
			
	private static void getObjectRepoVar(String objectName3) throws Exception 
	{
		int objectRepoRows = SheetObj.getLastRowNum();
		
		//Row row1 = SheetObj.getRow(0);
					
		//int objectRepoCol = row1.getLastCellNum();
		
		System.out.println("Object repo sheet rowCount: " + objectRepoRows);
		
		logger.info("Counting no. of ObjectRepo Script Rows : " + objectRepoRows);
		
		//System.out.println("Object repo sheet Colcount: " + objectRepoCol);	
		
		for(int j = 1; j <= objectRepoRows ; j++)
		{
			logger.info("Accessing ObjectRepo_Sheet Variable");
								
			objName = SheetObj.getRow(j).getCell(0).getStringCellValue();
			
			objpath = SheetObj.getRow(j).getCell(1).getStringCellValue();
				
			//System.out.println(objName +"  "+ objpath  );
	
				if(objectName3.equals(objName))
				{
					logger.info("ObjectRepo name is compared successfully.");
					
					getObjMethods();
					//break;
				}
				/*else
				{continue;}
				*/
		}
		
	}
	/*public static void main(String[] args) throws Exception
	{
		Logger logger =  Logger.getLogger("TestSuite");
	    PropertyConfigurator.configure("Log4j.properties");
	    
		TestSuite.readTestSuite();
	}*/
	
	public static void exit()
	{
		logger.info("Program Terminated");
		System.exit(0);
		
	}
	}
