package com.TrustTaxQA.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.TrustTaxQA.BaseClass;
import com.TrustTaxQA.Logger.Reports;
import com.TrustTaxQA.Utility.CommonControls;
import com.TrustTaxQA.Utility.Extensions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//public class WebLauncherLogin extends BaseClass{
public class LoginPg extends BaseClass{

		WebDriver driver;
		public LoginPg(WebDriver Idriver) throws Exception
		{
		this.driver = Idriver;
		}
	
		@FindBy(name="tbxOTTClientID") private List<WebElement> userNames;
	//ClientID
	@FindBy(name="tbxOTTClientID")public WebElement clientID ;
	//UserID
	@FindBy(name="tbxOTTUserID")public WebElement userID ;
	//Environment
	@FindBy(id="lstEnvironment")public WebElement environment ;
	//Login Button
	@FindBy(id="btnLogin")public WebElement LoginButton ;
	//Product Access Number 
	@FindBy(id="txtAccount")public WebElement PAN ;
	//Tax Year
	@FindBy(id="txtTaxYear")public WebElement TaxYear ;
	//OneSource Trust Tax Login
	@FindBy(id="Button1")public WebElement oneSourceButton ;
	
	//Main Frame
	@FindBy(css="[src='manager.aspx']")public WebElement mainFrame ;
	@FindBy(css="[name='paneDetails'][src='MainMRU.aspx']")public WebElement subFrame ;
	//accountinfo.aspx
	@FindBy(css="[name='paneDetails'][src='accountinfo.aspx']")public WebElement Accountframe ;
	//AccountNumber
		@FindBy(name="ctl00$cphBinderContent$TrustNumber")public WebElement acctNumber ;
		//
		//Open Button
		@FindBy(id="Open")public WebElement openButton ;
		//Name of Trust
		@FindBy(name="ctl00$cphBinderContent$txtname1")public WebElement NameOfTrust ;
		@FindBy(name="ctl00$cphBinderContent$txtein")public WebElement EmployerID ;
		//Save ButtonButton
		@FindBy(id="0_save")public WebElement saveButton ;
		//GreetingLetter
		@FindBy(id="ctl00_cphContent_WebPaneManager1_ViewPane0_WebNavPane1_Account_admincontrols.aspx")public WebElement greetingLetter ;
		//Account information
		@FindBy(id="ctl00_cphContent_WebPaneManager1_ViewPane0_WebNavPane1_Account_accountinfo.aspx")public WebElement acctInformation ;
		
		
		public void validateWebLauncherLogin(ExtentTest extentTest) throws Exception {
		String dataUserName = Extensions.testDataContent("TC-01", "UserName", testData);
		String dataPassWord = Extensions.testDataContent("TC-01", "PassWord", testData);
		String environmentVal = Extensions.testDataContent("TC-01", "Environment", testData);
		String PANNumber = Extensions.testDataContent("TC-01", "ProductAccessNumber", testData);
		String selectTaxYear = Extensions.testDataContent("TC-01", "TaxYear", testData);
		Extensions.waitForExpectedCondition(clientID);
		//Master page
		CommonControls.typeinEditBox(Master_clientID, dataUserName);
		//Login page
		//CommonControls.typeinEditBox(clientID, dataUserName);
		CommonControls.typeinEditBox(userID, dataPassWord);
		CommonControls.selectDropdown(environment, environmentVal);
		Extensions.waitForExpectedCondition(LoginButton);
		CommonControls.click(LoginButton);
	//	CommonControls.JSclick(LoginButton, driver);
		//Waiting for PAN to be appear
		Extensions.waitForExpectedCondition(PAN);
		CommonControls.typeinEditBox(PAN, PANNumber);
		CommonControls.selectDropdown(TaxYear, selectTaxYear);
		CommonControls.click(oneSourceButton);
		Extensions.waitSwithToFrame(mainFrame);
		Extensions.waitSwithToFrame(subFrame);
		CommonControls.typeinEditBox(acctNumber, "TEST1123");
		//CommonControls.click(openButton);
		//CommonControls.JSclick(openButton, driver);
		CommonControls.click(openButton);
		//Extensions.waitForExpectedCondition(NameOfTrust);
		driver.switchTo().defaultContent();
		//Extensions.waitForVisibleElement(mainFrame);
		//Extensions.waitPageTitle("ONESOURCE Trust Tax");
		Extensions.waitSwithToFrame(mainFrame);
		Extensions.waitForVisibleElement(Accountframe);
		//Extensions.waitForExpectedCondition(Accountframe);
		Extensions.waitSwithToFrame(Accountframe);
		CommonControls.typeinEditBox(NameOfTrust, "NewTrust5");
		CommonControls.typeinEditBox(EmployerID, "66-8658345");
		driver.switchTo().defaultContent();
		Extensions.waitSwithToFrame(mainFrame);
		CommonControls.click(saveButton);
		driver.switchTo().defaultContent();
		Extensions.waitSwithToFrame(mainFrame);
		Extensions.waitForExpectedCondition(greetingLetter);
		CommonControls.JSclick(greetingLetter, driver);
		//Reports.ReportLogs(extentTest, driver, LogStatus.PASS, "Account Page", "Account page is opened Sucessfully ", true);


	}
		public void LoginToOTTUsingLauncher_DataProvider(ExtentTest extentTest,String dataUserName,String dataPassWord) throws Exception {
		
		
			Extensions.waitForExpectedCondition(clientID);
			//Master page
			CommonControls.typeinEditBox(Master_clientID, dataUserName);
			//Login page
			//CommonControls.typeinEditBox(clientID, dataUserName);
			CommonControls.typeinEditBox(userID, dataPassWord);
			Reports.ReportLogs(extentTest, driver, LogStatus.PASS, "Account Page", "Account page is opened Sucessfully ", true);
		}
		//How to use list
		
		public void usingListToClickOnUname()
		{
			for (int i=0;i<userNames.size();i++)
			{
				if(userNames.get(i).getText().equals("ABC"))
				{
					userNames.get(i).click();
					break;
				}
			}
		}
	
}
