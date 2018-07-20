package autoFramework;

import java.io.FileInputStream;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MainClass
{
	static XSSFCell Cell;
	
	public static String Celldata = null;
	
	static Action key;
	
	static String cellData;
	
	public static void main(String[] args) 
	{
		try {
			String spath = "D:\\Selenium\\eclipse-workspace\\NewProjectSelenium\\KeyExcel.xlsx";
			
			FileInputStream fis = new FileInputStream(spath);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			XSSFSheet Sheet = wb.getSheet("Sheet1");
					
			int rowCount = Sheet.getLastRowNum();
			//rowCount = rowCount + 1;
			
			Row row = Sheet.getRow(0);
			
			int ColCount = row.getLastCellNum();
								
			System.out.println("rowCount: " + rowCount);
			System.out.println("Colcount: " + ColCount);
			
			for(int i = 0 ; i < rowCount ; i ++)
			{
							
			String action =	Sheet.getRow(i).getCell(0).getStringCellValue();
			
			System.out.println(action);
			String object = Sheet.getRow(i).getCell(1).getStringCellValue();
			
			System.out.println(object);
			String objpath= Sheet.getRow(i).getCell(2).getStringCellValue();

			System.out.println(objpath);
			Action.act(action, object , objpath );
			
				/*for(int j = 0 ; j < ColCount ; j ++)
				{
					Cell = Sheet.getRow(i).getCell(j);					
					System.out.println(Cell);
					
					cellData = Cell.toString();				
					
					
					Action.act(action, object , objpath );
				}*/
			
			}
							
				wb.close();		
			} 
		catch (Exception e) 
		{			
			e.printStackTrace();
		}

	}

}
