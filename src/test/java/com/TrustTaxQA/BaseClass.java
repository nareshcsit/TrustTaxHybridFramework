package com.TrustTaxQA;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.TrustTaxQA.Logger.Reports;
import com.TrustTaxQA.Pages.MasterPage;
import com.TrustTaxQA.TestData.TestData;
import com.TrustTaxQA.Utility.CommonControls;
import com.TrustTaxQA.Utility.Extensions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass extends MasterPage {

//	public BaseClass(WebDriver Idriver) {
//		super(Idriver);
//		// TODO Auto-generated constructor stub
//	}

	public Properties properties;
	ITestResult result;
	public ExtentTest test;
	public XSSFWorkbook wb = null;
	public FileInputStream fis = null;
	public Extensions ExtensionsObj;
	public TestData readJsonContent;
	public static JSONObject testData;
	WebDriver driver = null;
	// Before suite will get executed before start the All the test cases
	// execution
	@BeforeSuite
	public void beforeSuite() throws Exception {
		try {
			init();
		} catch (Exception e) {
			Reports.logger("Exception is occured at Before Suite method" + "->" + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	// This method will be loading config files,XSS workbook,worksheet etc
	public void init() throws Exception {
		try {
			Reports.StartReport();
			Config.LoadConfig();
			testData = TestData.readTestData();
			fis = new FileInputStream(Config.getExcelFilePath());
			wb = new XSSFWorkbook(fis);
		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
	}

	// After suite will get executed at end of all testcases
	@AfterSuite
	public void afterSuite() throws Exception {
		try {
			Reports.EndReport();
			Reports.copyFile();
		} catch (Exception e) {
			Reports.logger("Exception is occured at afterSuite method" + "->" + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	// This code is for DataProvider(Datadriven framework)
	public Object[][] getDataProviderData(String sheetName) throws Exception {
		if (sheetName == null) {
			throw new Exception("XL sheet is null,please correct it !!");
		}
		// return new Excel_Reader(wb).readExcleData(sheetName);
		return TestData.readExcleData(wb, sheetName);

	}
}
