package autoFramework;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.log4j.Logger;

import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class MainClass
{
	
	public static String objectName ;	
	public static String action ;	
	public static String Value ;
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
			
			XSSFSheet Sheet = testScriptWb.getSheetAt(0);
		
			sheetName = Sheet.getSheetName();
			System.out.println("TestScript sheetName = " + sheetName);
							
			int testScriptRows = Sheet.getLastRowNum();
			
			//Row row = Sheet.getRow(0);
			
			//int testScriptCols = row.getLastCellNum();
								
				System.out.println("TestScript row count: " + testScriptRows);
			
				logger.info("Counting no. of Rows in TestScript.xlsx : " + testScriptRows);
				
			//System.out.println("TestScript Col count: " + testScriptCols);
			
				
				
			// This loop checks if the Test Case name matches
				if(testSheetName.equals(sheetName))
				{
					logger.info("TestScript Found . Comparing TestScript Sheet name");
			// Read Object Repo Sheet name Linked in TestScipt
				
					String ORSheetName = Sheet.getRow(1).getCell(0).getStringCellValue();
					
					ORSheetNameValue = Sheet.getRow(1).getCell(1).getStringCellValue();
				
					System.out.println(ORSheetName + ", "+ ORSheetNameValue);
				
					objectRepoScript(ORSheetNameValue);
					
					
					logger.info("Cheching for the Linked ObjectRepo Sheet");
		
				
			// Checking if Object Repo Sheet linked in Test Case Sheet is available in Object Repo workbook.	
				if(obj_sheetname.equals(ORSheetNameValue))
				{
					for(int i = 3 ; i <= testScriptRows ; i ++)
					{
					
					logger.info("Accessing TestCase Variables in TestScript");
					
					action = Sheet.getRow(i).getCell(0).getStringCellValue();				
					System.out.println("action = " + action);
					
					objectName = Sheet.getRow(i).getCell(1).getStringCellValue();
					System.out.println("objectName= " + objectName);
					
					Value= Sheet.getRow(i).getCell(2).getStringCellValue();
		
					System.out.println("Value = " + Value);
					
					getObjectRepoVar(objectName);
					}
					
				}
		// If Linked Object Repo Sheet doesn't match with any sheet of the Object Repo Workbook then this loop will execute and end the Execution.
				else
					{
					logger.info("ObjectRepo Sheet name Not Found");
					exit();
					
					}
			}
			else
			{
				System.out.println("TestScript "+ testSheetName + " doesnt exist. Please check Sheet Name");
				
				logger.info("TestScript Not Found");
				
				exit();
			}
				testScriptWb.close();
		}
			
	//This Method is to read  the Object Repo Sheet 
	
	public static void objectRepoScript(String ORSheetNameValue) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException
		{
		
			logger.info("Accessing ObjectRepo Script");
		
			String objRepoPath = "D:\\Selenium\\eclipse-workspace\\NewProjectSelenium\\Objectrepo.xlsx";
			
			FileInputStream Obj_fis = new FileInputStream(objRepoPath);
				
			Obj_repo_wb = new XSSFWorkbook(Obj_fis);
				
			SheetObj =  Obj_repo_wb.getSheetAt(0);
			
			// This loop is to search the Object Repo Sheet linked with Test Script. Comparing the Object Repo Sheet Names
				
			for(int i = 0 ; i < Obj_repo_wb.getNumberOfSheets() ; )
				
			{
			
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
				 if(method[i].getName().equals(action))
				 {
					
					 Action.act(action, objectName , objpath, Value);
					 break;
				 }
			 }
		}
			
	private static void getObjectRepoVar(String testScriptObjectName) throws Exception 
	{
		int objectRepoRows = SheetObj.getLastRowNum();
		
		//Row row1 = SheetObj.getRow(0);
					
		//int objectRepoCol = row1.getLastCellNum();
		
		System.out.println("Object repo sheet rowCount: " + objectRepoRows);
		
		logger.info("Counting no. of ObjectRepo Script Rows : " + objectRepoRows);
		
		//System.out.println("Object repo sheet Colcount: " + objectRepoCol);	
		
		for(int j = 1; j <= objectRepoRows ;)
		{
			logger.info("Accessing ObjectRepo_Sheet Variable");
								
			objName = SheetObj.getRow(j).getCell(0).getStringCellValue();
			
			objpath = SheetObj.getRow(j).getCell(1).getStringCellValue();
				
			//System.out.println(objName +"  "+ objpath  );
	
				if(testScriptObjectName.equals(objName))
				{
					logger.info("ObjectRepo name is compared successfully.");
					
					getObjMethods();
					break;
				}
				else 
				{
					j++;
				}
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
