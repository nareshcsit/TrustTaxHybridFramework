package com.TrustTaxQA;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.TrustTaxQA.Logger.Reports;
//import com.TrustTaxQA.TestData.Excel_Reader;
import com.TrustTaxQA.TestData.Excel_Reader;
import com.TrustTaxQA.TestData.ReadJson;
import com.TrustTaxQA.Utility.CommonFunctions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass {

	public WebDriver driver;
	boolean isPresent = false;
	boolean isEnabled = false;
	public Properties properties;
	ITestResult result;
	public Reports ReportObj;
	public ExtentTest test;
	public Config conf;
	public XSSFWorkbook wb = null;
	public FileInputStream fis = null;
	// public ReadExcelData objReadExcel;
	public ReadJson readJson;
	// ReadJson
	CommonFunctions cf;
	public JSONObject jsonObject;

	@BeforeSuite
	public void beforeSuite() throws Exception {
		try {
			
			init();
		} catch (Exception e) {
			ReportObj.logger("Exception is occured at Before Suite method" + "->" + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public void init() throws Exception
	{
		try {
			//Creating object for Report class
			ReportObj = new Reports();
			ReportObj.StartReport();
			//Creating object for Config class
			conf=new Config();
			//Creating object for Json class
			readJson = new ReadJson(conf,ReportObj);
			jsonObject = readJson.returnJsonContent();
			//Creating object for XL classfis = new FileInputStream(excelFilePath);
			fis = new FileInputStream(conf.getExcelFilePath());
			wb = new XSSFWorkbook(fis);
		} catch (Exception e) {
			ReportObj.logger("Exception is occured at Before Suite method" + "->" + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
	@BeforeMethod
	@Parameters("browser")
	public void beforeMethod(String browserName) throws Exception {
		try {
			System.out.println(conf.getJsonFilePath());
			getBrowser(browserName);
			cf = new CommonFunctions(driver,conf);
			
			driver.get("https://wordpress.com/log-in");
			driver.manage().window().maximize();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws Exception {
		getresult(result);
	}

	@AfterSuite
	public void afterSuite() throws Exception {
		try {
			ReportObj.EndReport();
			ReportObj.copyFile();
		} catch (Exception e) {
			ReportObj.logger("Exception is occured at EndReport method" + "->" + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public void getBrowser(String browser) throws Exception {
		try {
			//if (properties.getProperty("os.name").contains("Window")) {
			if (conf.getOSName().contains("Window")) {
				if (browser.equalsIgnoreCase("firefox")) {
					// https://github.com/mozilla/geckodriver/releases
					// System.out.println(System.getProperty("user.dir"));
					// System.setProperty("webdriver.gecko.driver",
					// System.getProperty("user.dir")+"/drivers/geckodriver.exe");
					driver = new FirefoxDriver();
				} else if (browser.equalsIgnoreCase("chrome")) {
					// https://chromedriver.storage.googleapis.com/index.html
					properties.setProperty("webdriver.chrome.driver",
							System.getProperty("user.dir") + "/drivers/chromedriver.exe");
					driver = new ChromeDriver();
				}
			} else if (properties.getProperty("os.name").contains("Mac")) {
				System.out.println(System.getProperty("os.name"));
				if (browser.equalsIgnoreCase("firefox")) {
					System.out.println(System.getProperty("user.dir"));
					System.setProperty("webdriver.gecko.driver",
							System.getProperty("user.dir") + "/drivers/geckodriver");
					driver = new FirefoxDriver();
				} else if (browser.equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.chrome.driver",
							System.getProperty("user.dir") + "/drivers/chromedriver");
					driver = new ChromeDriver();
				}
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void getresult(ITestResult result) throws Exception {
		try {

			if (ITestResult.FAILURE == result.getStatus()) {

				ReportObj.ReportLogs(driver, LogStatus.ERROR, "Exception has occuredd",
						result.getThrowable().getMessage(), false);
			}

			ReportObj.EndTestCase();
			driver.quit();
			cf=null;

		} catch (Exception e) {
			ReportObj.EndTestCase();
			driver.quit();
			cf=null;
			// throw new Exception(e.getMessage());
		}
	}

	//public WebElement getLocator(WebDriver driver, String locator) throws Exception {
	public WebElement getLocator(String locator) throws Exception {
		// System.out.println(locator);
		String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];
		// System.out.println("locatorType:-"+locatorType);
		// System.out.println("locatorValue:-"+locatorValue);
		if (locatorType.toLowerCase().equals("id"))
			return driver.findElement(By.id(locatorValue));
		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElement(By.name(locatorValue));
		else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
			return driver.findElement(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
			return driver.findElement(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
			return driver.findElement(By.linkText(locatorValue));
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElement(By.partialLinkText(locatorValue));
		else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
			return driver.findElement(By.cssSelector(locatorValue));
		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElement(By.xpath(locatorValue));
		else
			throw new Exception("Unknown locator type '" + locatorType + "'");
	}

	public List<WebElement> getLocators(String locator) throws Exception {
		String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];
		System.out.println("locatorType:-" + locatorType);
		System.out.println("locatorValue:-" + locatorValue);

		if (locatorType.toLowerCase().equals("id"))
			return driver.findElements(By.id(locatorValue));
		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElements(By.name(locatorValue));
		else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
			return driver.findElements(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
			return driver.findElements(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
			return driver.findElements(By.linkText(locatorValue));
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElements(By.partialLinkText(locatorValue));
		else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
			return driver.findElements(By.cssSelector(locatorValue));
		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElements(By.xpath(locatorValue));
		else
			throw new Exception("Unknown locator type '" + locatorType + "'");
	}

//	public WebElement getWebElement(WebDriver driver, String locator) throws Exception {
//		return getLocator(driver, locator);
	public WebElement getWebElement( String locator) throws Exception {
		return getLocator(locator);
	}
	
	public List<WebElement> getWebElements(String locator) throws Exception {
		return getLocators(locator);
	}

	// String[][] getData
	public Object[][] getData(String sheetName) throws Exception {
		if(sheetName==null)
		{
			throw new Exception("XL sheet is null,please correct it !!");
		}
		Excel_Reader excelReader = new Excel_Reader(wb);
		return excelReader.readExcleData(sheetName);

	}
}
