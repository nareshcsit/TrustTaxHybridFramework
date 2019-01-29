package com.TrustTaxQA;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.TrustTaxQA.Logger.Reports;
import com.TrustTaxQA.Pages.AccountPage;
import com.TrustTaxQA.Pages.LoginPg;
import com.TrustTaxQA.Utility.CommonControls;
import com.TrustTaxQA.Utility.Extensions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

public class Regression extends BaseClass {
	 @Parameters({ "browser" })
	@Test
	public void AccounttrustTaxLogin1(String param) throws Exception {
		System.out.println("Regression test method param value is:"+param);
		ExtentTest extentTest = null;
	//This is test case
		try {
			extentTest = Reports.startTestCase("Regression1");
			Extensions Extensions = new Extensions();
			driver = Extensions.getDriver();
		//Page factory
			LoginPg loginObj = PageFactory.initElements(driver, LoginPg.class);

		loginObj.validateWebLauncherLogin(extentTest);
		Reports.ReportLogs(extentTest, driver, LogStatus.PASS, "Regression Account Page", " Regression Account page is opened Sucessfully ", true);
			Extensions.EndTest(driver, extentTest);
			
		} catch (Exception e) {

			Extensions.EndTest(driver, extentTest, e.getMessage());
			Assert.assertFalse(true);
		}
	}
//
	@Test
	public void AccounttrustTaxLogin2(Method method) throws Exception {
		ExtentTest extentTest = null;
		WebDriver driver=null;
		try {
			extentTest = Reports.startTestCase("Regression2");
			Extensions Extensions = new Extensions();
			driver = Extensions.getDriver();

			LoginPg loginObj = PageFactory.initElements(driver, LoginPg.class);

			loginObj.validateWebLauncherLogin(extentTest);
			Extensions.EndTest(driver, extentTest);

		} catch (Exception e) {

			Extensions.EndTest(driver, extentTest, e.getMessage());
		Assert.assertFalse(true);
		}
	
			}
	
	@Test
	public void AccounttrustTaxLogin3(Method method) throws Exception {
		ExtentTest extentTest = null;
		WebDriver driver=null;
		try {
			extentTest = Reports.startTestCase("Regression2");
			Extensions Extensions = new Extensions();
			driver = Extensions.getDriver();

			LoginPg loginObj = PageFactory.initElements(driver, LoginPg.class);

			loginObj.validateWebLauncherLogin(extentTest);
			Extensions.EndTest(driver, extentTest);

		} catch (Exception e) {

			Extensions.EndTest(driver, extentTest, e.getMessage());
		Assert.assertFalse(true);
		}
	
			}
	}
