package autoFramework;

import org.apache.log4j.Logger;

public class Action 
{
	public static Object key;
	static Logger logger =  Logger.getLogger("Action");
	
	public static void act(String action,String object , String objpath, String value) throws Exception
	{
		
		try {
			if(action.equals("openBrowser"))
			{			
				Object.openBrowser(objpath);
				
				logger.info("openBrowser ");
			}

			else if(action.equals("navigate"))
			{
				
				Object.navigate(objpath);
				
				logger.info("navigate");
			
			}
			else if(action.equals("click"))
			{
				Thread.sleep(3000);
				
				Object.click(objpath);
				
				logger.info("click");
				
			}
			else if(action.equals("type"))
			{
				Thread.sleep(3000);
				
				Object.type(objpath , value);
				
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
