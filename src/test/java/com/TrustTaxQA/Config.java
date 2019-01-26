package com.TrustTaxQA;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

//This class is for to configure the constant values through out the script
//Like URL,Explicity wait time,implicity waitime
public class Config {
	public static Properties properties;
	static FileReader reader;

	public static void LoadConfig() throws IOException {
		reader = new FileReader("Global.Properties");
		properties = new Properties();
		properties.load(reader);
	}

	public static String getJsonFilePath() {
		return properties.getProperty("JsonFilePath");

	}

	public static String getExcelFilePath() {
		return properties.getProperty("ExcelFilePath");

	}

	public static String getOSName() {
		return properties.getProperty("os.name");
	}

	public static String getUserName() {
		return properties.getProperty("UserName");
	}

	public static String getPassWord() {
		return properties.getProperty("PassWord");
	}

	public static String getApplicationURL() {
		return properties.getProperty("ApplicationURL");
	}

	public static int getPageLoadTimeOut() {
		return Integer.parseInt(properties.getProperty("PageLoadTimeOut"));
	}

	public static int getImplicityWait() {
		return Integer.parseInt(properties.getProperty("ImplicityWait"));
	}

	public static int getExplicitWait() {
		return Integer.parseInt(properties.getProperty("ExplicitWait"));
	}

	public static String getBrowser() {
		return properties.getProperty("Browser");
	}

}
