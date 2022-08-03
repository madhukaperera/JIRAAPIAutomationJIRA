package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class ReadPropertyValues {

	String property = "";
	InputStream inputStream;
	
	public String getPropValues(String propertyToRead) throws IOException {
		 
		try {
			Properties prop = new Properties();
			String propFileName = "resources/Config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) 
			{
				prop.load(inputStream);
			} 
			else 
			{
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			//Date time = new Date(System.currentTimeMillis());
 
			// get the property 
			property = prop.getProperty(propertyToRead);		
			
		} 
		catch (Exception e) 
		{
			
		} finally {
			inputStream.close();
		}
		return property;
	}
}
