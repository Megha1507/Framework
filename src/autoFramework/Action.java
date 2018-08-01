package autoFramework;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class Action 
{
	public static Object key;
	static Logger logger =  Logger.getLogger("Action");
	
	public static void act(String action2,String objectName2 , String objpath, String value2) throws Exception
	{
		
		try {
			if(action2.equals("openBrowser"))
			{			
				Object.openBrowser(objpath);
				
				logger.info("openBrowser ");
			}

			else if(action2.equals("navigate"))
			{
				
				Object.navigate(objpath);
				
				logger.info("navigate");
			
			}
			else if(action2.equals("click"))
			{
				Thread.sleep(3000);
				
				Object.click(objpath);
				
				logger.info("click");
				
			}
			else if(action2.equals("type"))
			{
				Thread.sleep(3000);
				String value1= value2.toString(); 
				Object.type(objpath , value1);
				
				logger.info("type");
			}
		} 
		catch (Exception e) 
		{
			logger.info("act method throws EXCEPTION");
			e.printStackTrace();
		}
	}	
}
