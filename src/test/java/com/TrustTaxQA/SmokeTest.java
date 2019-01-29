package com.TrustTaxQA;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.TrustTaxQA.Logger.Reports;
import com.TrustTaxQA.Pages.AccountPage;
import com.TrustTaxQA.Pages.LoginPg;
import com.TrustTaxQA.Utility.CommonControls;
import com.TrustTaxQA.Utility.Extensions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

public class SmokeTest extends BaseClass {

	 @Test(dataProvider = "getData")
	 public void setData(String username, String password) throws Exception {
	 ExtentTest extentTest = null;

	 try {
	System.out.println("This is smoke test");
	 extentTest = Reports.startTestCase("SmokeTest1");
	
	 Extensions Extensions = new Extensions();
	 driver = Extensions.getDriver();
	 LoginPg loginObj = PageFactory.initElements(driver, LoginPg.class);
	 loginObj.LoginToOTTUsingLauncher_DataProvider(extentTest,username, password);
	 System.out.println("you have provided username as::" + username);
	 System.out.println("you have provided password as::" + password);
	 Extensions.EndTest(driver, extentTest);
	
	 } catch (Exception e) {
	 Extensions.EndTest(driver, extentTest, e.getMessage());
	 Assert.assertFalse(true);
	 }
	
	 }
	 
	/*
	 @DataProvider
	 public Object[][] getData() throws Exception
	 {
	 return getDataProviderData("SmokeSheet");
	
	 }*/

	 @DataProvider
	 public Object[][] getData() {
	 // Rows - Number of times your test has to be repeated.
	// // Columns - Number of parameters in test data.
	 Object[][] data = new Object[3][2];
	
	// // 1st row
	 data[0][0] = "nareshcsit@gmail.com";
	 data[0][1] = "Shiridi@34";
	//
	// // 2nd row
	 data[1][0] = "nareshcsit@gmail.com";
	 data[1][1] = "Shiridi@34";
	//
	// // 3rd row
	 data[2][0] = "nareshcsit@gmail.com";
	 data[2][1] = "Shiridi@34";
	//
	 return data;
	 }

			@Test
			public void oneSourceLogin1(Method method) throws Exception {
				System.out.println("Smoke test method value is:"+method.getName());
				ExtentTest extentTest = null;
				WebDriver driver = null;
				try {
					extentTest = Reports.startTestCase("SmokeTest1");
					Extensions Extensions = new Extensions();
					driver = Extensions.getDriver();
		
					LoginPg loginObj = PageFactory.initElements(driver, LoginPg.class);
		
					loginObj.validateWebLauncherLogin(extentTest);
					Reports.ReportLogs(extentTest, driver, LogStatus.PASS, "SmokeTest Account Page", " SmokeTest Account page is opened Sucessfully ", true);
					Extensions.EndTest(driver, extentTest);
					
				} catch (Exception e) {
		
					Extensions.EndTest(driver, extentTest, e.getMessage());
					Assert.assertFalse(true);
		
				}
			}
			@Test
			public void oneSourceLogin2(Method method) throws Exception {
				System.out.println("Smoke test method value is:"+method.getName());
				ExtentTest extentTest = null;
				WebDriver driver = null;
				try {
					extentTest = Reports.startTestCase("SmokeTest2");
					Extensions Extensions = new Extensions();
					driver = Extensions.getDriver();
		
					LoginPg loginObj = PageFactory.initElements(driver, LoginPg.class);
		
					loginObj.validateWebLauncherLogin(extentTest);
					Reports.ReportLogs(extentTest, driver, LogStatus.PASS, "SmokeTest Account Page", " SmokeTest Account page is opened Sucessfully ", true);
					Extensions.EndTest(driver, extentTest);
					
				} catch (Exception e) {
		
					Extensions.EndTest(driver, extentTest, e.getMessage());
					Assert.assertFalse(true);
		
				}
			}
			@Test
			public void oneSourceLogin3(Method method) throws Exception {
				System.out.println("Smoke test method value is:"+method.getName());
				ExtentTest extentTest = null;
				WebDriver driver = null;
				try {
					extentTest = Reports.startTestCase("SmokeTest2");
					Extensions Extensions = new Extensions();
					driver = Extensions.getDriver();
		
					LoginPg loginObj = PageFactory.initElements(driver, LoginPg.class);
		
					loginObj.validateWebLauncherLogin(extentTest);
					Reports.ReportLogs(extentTest, driver, LogStatus.PASS, "SmokeTest Account Page", " SmokeTest Account page is opened Sucessfully ", true);
					Extensions.EndTest(driver, extentTest);
					
				} catch (Exception e) {
		
					Extensions.EndTest(driver, extentTest, e.getMessage());
					Assert.assertFalse(true);
		
				}
			}
			@Test
			public void oneSourceLogin4(Method method) throws Exception {
				System.out.println("Smoke test method value is:"+method.getName());
				ExtentTest extentTest = null;
				WebDriver driver = null;
				try {
					extentTest = Reports.startTestCase("SmokeTest2");
					Extensions Extensions = new Extensions();
					driver = Extensions.getDriver();
		
					LoginPg loginObj = PageFactory.initElements(driver, LoginPg.class);
		
					loginObj.validateWebLauncherLogin(extentTest);
					Reports.ReportLogs(extentTest, driver, LogStatus.PASS, "SmokeTest Account Page", " SmokeTest Account page is opened Sucessfully ", true);
					Extensions.EndTest(driver, extentTest);
					
				} catch (Exception e) {
		
					Extensions.EndTest(driver, extentTest, e.getMessage());
					Assert.assertFalse(true);
				}
				}
				@Test
				public void oneSourceLogin5(Method method) throws Exception {
					System.out.println("Smoke test method value is:"+method.getName());
					ExtentTest extentTest = null;
					WebDriver driver = null;
					try {
						extentTest = Reports.startTestCase("SmokeTest5");
						Extensions Extensions = new Extensions();
						driver = Extensions.getDriver();
			
						LoginPg loginObj = PageFactory.initElements(driver, LoginPg.class);
			
						loginObj.validateWebLauncherLogin(extentTest);
						Reports.ReportLogs(extentTest, driver, LogStatus.PASS, "SmokeTest Account Page", " SmokeTest Account page is opened Sucessfully ", true);
						Extensions.EndTest(driver, extentTest);
						
					} catch (Exception e) {
			
						Extensions.EndTest(driver, extentTest, e.getMessage());
						Assert.assertFalse(true);
			
					}
			}
			
			
}

