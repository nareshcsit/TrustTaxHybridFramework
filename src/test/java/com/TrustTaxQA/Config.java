package com.TrustTaxQA;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config{
	public Properties properties;
	public Config() throws IOException
	{
	FileReader reader = new FileReader("Global.Properties");
	properties = new Properties();
	// Loading Property File
	properties.load(reader);
	
	}
	public String getJsonFilePath()
	{
	return properties.getProperty("JsonFilePath");
	
	}
	public String getExcelFilePath()
	{
	return properties.getProperty("ExcelFilePath");
	
	}
	public String getOSName()
	{
		return properties.getProperty("os.name");
	}
	public String getUserName()
	{
		return properties.getProperty("UserName");
	}
	public String getPassWord()
	{
		return properties.getProperty("PassWord");
	}
	public String getApplicationURL()
	{
		return properties.getProperty("ApplicationURL");
	}
	public int getPageLoadTimeOut()
	{
		return Integer.parseInt(properties.getProperty("PageLoadTimeOut"));
	}
	public int getImplicityWait()
	{
		return Integer.parseInt(properties.getProperty("ImplicityWait"));
	}
	public int getExplicitWait()
	{
		return Integer.parseInt(properties.getProperty("ExplicitWait"));
	}
	
}

