package com.TrustTaxQA;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.lift.TestContext;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.TrustTaxQA.Pages.Login;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SmokeTest extends BaseClass {

//	 @Test
//	 public void AccounttrustTaxLogin(Method method) throws Exception {
//	 try{
//	 boolean BooleanLoginsucess = false;
//	 System.out.println(conf.getJsonFilePath());
//	 ReportObj.startTestCase((method.getName()));
//	 ReportObj.ReportLogs(driver,LogStatus.PASS,"Step Name","User is logged in attendee admin Application",true);
//	 String UserName=cf.returnJsonContent("TC-01", "UserName", jsonObject);
//	 String PassWord=cf.returnJsonContent("TC-01", "PassWord", jsonObject);
//	 BooleanLoginsucess=cf.waitForExpectedCondition(Login.ID_txtusername);
//	 getWebElement(Login.ID_txtusername).sendKeys("ABC");
//	 if(BooleanLoginsucess)
//	 {
//	 ReportObj.ReportLogs(driver,LogStatus.PASS,"Step Name","User is logged in attendee admin Application",true);
//	 }
//	 ReportObj.logger("text file 1 is entered");
//	
//	 ReportObj.logger("text file 2 is entered");
//	 } catch(Exception e)
//	 {
//	 throw new Exception("Exception is occured in Smoketest "+e.getMessage());
//	 }
//	 }
//	 @Test
//	 public void OpenAccount(Method method) throws Exception {
//	 boolean BooleanLoginsucess = false;
//	 try{
//	
//	
//	 ReportObj.startTestCase(method.getName());
//	
//	 String UserName=cf.returnJsonContent("TC-02", "UserName", jsonObject);
//	 String PassWord=cf.returnJsonContent("TC-02", "PassWord", jsonObject);
//	 BooleanLoginsucess=cf.validateLogin(UserName,PassWord);
//	 
//	 if(BooleanLoginsucess)
//	 {
//	 ReportObj.ReportLogs(driver,LogStatus.PASS,"Step Name","User is logged inattendee admin Application",true);
//	 }
//	
//	 WebElement username=getWebElement(Login.Name_txtpassword);
//	 username.sendKeys("Naresh");
//	ReportObj.logger("text file 3 is entered");
//	
//	 }
//	 catch(Exception e)
//	 {
//	 throw new Exception(e.getMessage());
//	 }
//	 }
//	

	@Test(dataProvider = "getData")
	public void setData(String username, String password) throws Exception {
		try{
		ReportObj.startTestCase("testLogin");
		ReportObj.ReportLogs(driver, LogStatus.INFO, "Login", "Validationg multi login functinality", true);
		System.out.println("you have provided username as::" + username);
		System.out.println("you have provided password as::" + password);
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}

	@DataProvider
	public Object[][] getData() throws Exception {
		try{
			System.out.println(conf.getExcelFilePath());
			
		return getData("SmokeSheet");
		}catch(Exception e)
		{
			ReportObj.logger("Exception occured at Validationg multi login functinality");
		
			throw new Exception(e.getMessage());
		}

	}

}

