package com_SCM_utilities_file;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Utility class for reading the data from Properties file
 * @author JAGADISH
 */
public class FileHelper {
	/**
	 * Reads value from the file based on key.
	 * @param key  The key whose value to be fetched.
	 * @return  it will return the value corresponding to the key.
	 */
public String getDataFromPropertyFile(String key)
{      Properties p=null;
	try {
		FileInputStream fis=new FileInputStream("");
		  p=new Properties();
		  p.load(fis);
		 
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	   String data=p.getProperty(key);
	   return data;
}
	
}
