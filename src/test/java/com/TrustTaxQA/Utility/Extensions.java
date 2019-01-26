package com.TrustTaxQA.Utility;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import com.TrustTaxQA.BaseClass;
import com.TrustTaxQA.Config;
import com.TrustTaxQA.Logger.Reports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//Extensions is for only webdriver methods like
//WebDriver wait,Driver script click etc
public class Extensions {
	public WebDriver driver;
	static WebDriverWait wait;

	// Get driver method will return the driver instant
	public WebDriver getDriver() throws Exception {
		String browser=Config.getBrowser().trim();
		System.out.println("Browser is:"+browser);
		try {
			if (Config.getOSName().contains("Window")) {
			
				if (browser.equalsIgnoreCase("firefox")) {
					System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "/drivers/geckodriver.exe");
					driver = new FirefoxDriver();
					
				} else if (browser.equalsIgnoreCase("chrome")) {

					System.setProperty("webdriver.chrome.driver",
							System.getProperty("user.dir") + "/drivers/chromedriver.exe");

					ChromeOptions chromeOptions = new ChromeOptions();
					chromeOptions.addArguments(Arrays.asList("--test-type"));
					DesiredCapabilities capabilities = DesiredCapabilities.chrome();
					capabilities.setCapability("chrome.switches", Arrays.asList("--incognito"));
					capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
					driver = new ChromeDriver(capabilities);
					
				} else if (browser.equalsIgnoreCase("IE")) 
					{
					System.setProperty("webdriver.ie.driver",
							System.getProperty("user.dir") + "/drivers/IEDriverServer.exe");
					driver = new InternetExplorerDriver();				
					
				}
			} else if (Config.getOSName().contains("Mac")) {
				if (browser.equalsIgnoreCase("firefox")) {
					System.out.println(System.getProperty("user.dir"));
					System.setProperty("webdriver.gecko.driver",
							System.getProperty("user.dir") + "/drivers/geckodriver");

				}

			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
		driver.get(Config.getApplicationURL());
		// Defing pageLoad time
			// driver.manage().
			// providing implicit wait
		driver.manage().timeouts().implicitlyWait(Config.getImplicityWait(), TimeUnit.SECONDS);
		// maximizing the browser
		driver.manage().window().maximize();
		// Creating object for explicity wait
		wait = new WebDriverWait(driver, Config.getExplicitWait());
		return driver;

	}

	// This method is for to end the testCase with 2 arguments
	public static void EndTest(WebDriver driver, ExtentTest extentTest) throws Exception {
		try {
			Reports.EndTestCase(extentTest);
			closeIEDriver();
			driver.close();
			driver = null;
		} catch (Exception e) {
			Reports.EndTestCase(extentTest);
			closeIEDriver();
			driver.close();
			driver = null;
		}
	}
	
	public static void closeIEDriver() throws IOException
	{
		
		    Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
		
	}

	// This method is for to end the testCase with 3 arguments
	public static void EndTest(WebDriver driver, ExtentTest extentTest, String ErrorMessage) throws Exception {
		try {
			Reports.ReportLogs(extentTest, driver, LogStatus.ERROR, "Exception has occured", ErrorMessage, false);
			Reports.EndTestCase(extentTest);
			driver.close();
			driver = null;
		} catch (Exception e) {
			Reports.EndTestCase(extentTest);
			driver.close();
			driver = null;
		}
	}

	// This method is for to retrive the required Data from JSON Object
	public static String testDataContent(String TC_ID, String ColumnHeader, JSONObject jsonObject) throws Exception {
		String jsonObjectValue = null;
		try {
			// JSONObject converting into JSONArray
			JSONArray arrayList = (JSONArray) jsonObject.get(TC_ID);
			// retrieve first value from JSON Array
			JSONObject jsonContent = (JSONObject) arrayList.get(0);
			// retrieve the content from JSON array based on column header
			jsonObjectValue = (String) jsonContent.get(ColumnHeader);
			if (jsonObjectValue == null) {
				throw new Exception("Unable to find either TestCaseNo or ColumnHeader for the testcase  '" + TC_ID
						+ "' in the Jsonfile !!");
			}
		} catch (Exception e) {
			throw new Exception("Unable to find either TestCaseNo or ColumnHeader for the testcase  '" + TC_ID
					+ "' in the Jsonfile !!");
		}
		return jsonObjectValue;

	}

	// This method is for to wait until elment is clickble
	//This method is for to wait until elment is clickble
		public static void waitForExpectedCondition(WebElement Element) {

			wait.until(ExpectedConditions.elementToBeClickable(Element));

		}
		public static void waitForVisibleElement(WebElement Element) {

			wait.until(ExpectedConditions.visibilityOf(Element));

		}
		public static void waitSwithToFrame(WebElement Element)
		{
		  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(Element));
		}
		
		public static void waitPageTitle(String title)
		{
		  wait.until(ExpectedConditions.titleContains(title));
		}

}
