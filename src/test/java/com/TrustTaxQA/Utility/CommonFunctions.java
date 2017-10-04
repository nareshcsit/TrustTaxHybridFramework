package com.TrustTaxQA.Utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.TrustTaxQA.BaseClass;
import com.TrustTaxQA.Config;
import com.TrustTaxQA.Logger.Reports;
import com.TrustTaxQA.Pages.Login;
import com.google.common.base.Function;
import com.relevantcodes.extentreports.LogStatus;


public class CommonFunctions extends BaseClass{
	private Config conf;
	WebDriverWait wait;
	WebDriver driver;
public CommonFunctions(WebDriver driver,Config conf)
{
	this.conf=conf;
	this.driver=driver;
	 wait = new WebDriverWait(driver,conf.getExplicitWait());
}

//int explicitWaitTimeOut=conf.getExplicitWait();

public String returnJsonContent(String TC_ID, String ColumnHeader, JSONObject jsonObject) throws Exception {
		String jsonObjectValue = null;
		
		try {
			System.out.println("Common Functions"+conf.getJsonFilePath());
			JSONArray arrayList = (JSONArray) jsonObject.get(TC_ID);
			JSONObject jsonContent = (JSONObject) arrayList.get(0);
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
	public boolean  validateLogin(String userName,String passWord) throws Exception
	{
		boolean BooleanLoginsucess = false;
		try
		{
			System.out.println(conf.getJsonFilePath());
			WebElement username=getWebElement(Login.ID_txtusername);
			username.sendKeys(userName);
		BooleanLoginsucess=true;
		}
		catch(Exception e)
		{
			throw new Exception("Exception has occured while validating login functinality:"+e.getMessage());
		}
		return BooleanLoginsucess;
	}
	//Alert methods
	public Alert getAlert() throws Exception {
		try{
			//oLog.debug("");
			return driver.switchTo().alert();
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
		public void AcceptAlert() throws Exception {
			//oLog.info("");
			try{
			getAlert().accept();
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}
		
		public void DismissAlert() throws Exception {
			//oLog.info("");
			try{
			getAlert().dismiss();
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}

		public String getAlertText() throws Exception {
			try{
			String text = getAlert().getText();
			//oLog.info(text);
			return text;
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}

		public boolean isAlertPresent() {
			try {
				driver.switchTo().alert();
				//oLog.info("true");
				return true;
			} catch (NoAlertPresentException e) {
				// Ignore
				//oLog.info("false");
				return false;
			}
		}

		public void AcceptAlertIfPresent() throws Exception {
			try{
			if (!isAlertPresent())
				return;
			AcceptAlert();
			//oLog.info("");
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}

		public void DismissAlertIfPresent() throws Exception {
try{
			if (!isAlertPresent())
				return;
			DismissAlert();
			//oLog.info("");
}
catch(Exception e)
{
	throw new Exception(e.getMessage());
}
}
		
		public void AcceptPrompt(String text) throws Exception {
			try{
			if (!isAlertPresent())
				return;
			
			Alert alert = getAlert();
			alert.sendKeys(text);
			alert.accept();
			//oLog.info(text);
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}
	
	//Verification Helper
	public  boolean verifyElementPresent( WebElement element) {
			boolean isDispalyed = false;
			try {
				 isDispalyed= element.isDisplayed();
				 //log.info(element.getText()+" is dispalyed");
				 isDispalyed=true;
			}
			catch(Exception ex) {
				//log.error("Element not found " + ex);
			}
			
			return isDispalyed;
		}
		
		public  boolean verifyElementNotPresent( WebElement element) {
			boolean isDispalyed = false;
			try {
				 element.isDisplayed();
				// log.info(element.getText()+" is dispalyed");
			}
			catch(Exception ex) {
				//log.error("Element not found " + ex);
				isDispalyed = true;
			}
			
			return isDispalyed;
		}
		
		public  boolean verifyTextEquals( WebElement element,String expectedText) {
			boolean flag = false;
			try {
				String actualText=element.getText();
				if(actualText.equals(expectedText)) {
					//log.info("actualText is :"+actualText+" expected text is: "+expectedText);
					return flag=true;
				}
				else {
					//log.error("actualText is :"+actualText+" expected text is: "+expectedText);
					return flag;
				}
			}
			catch(Exception ex) {
				//log.error("actualText is :"+element.getText()+" expected text is: "+expectedText);
				//log.info("text not matching" + ex);
				return flag;
			}
		}

	//Browser helper

	public void goBack() throws Exception {
		try{
			driver.navigate().back();
			//Log.info("");
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}

		public void goForward() throws Exception {
			try{
			driver.navigate().forward();
			//Log.info("");
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}

		public void refresh() throws Exception {
			try{
			driver.navigate().refresh();
			//Log.info("");
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}

		public Set<String> getWindowHandlens() throws Exception {
			try{
			//Log.info("");
			return driver.getWindowHandles();
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}

		public void SwitchToWindow(int index) throws Exception {
try{
			LinkedList<String> windowsId = new LinkedList<String>(getWindowHandlens());

			if (index < 0 || index > windowsId.size()){
				throw new IllegalArgumentException("Invalid Index : " + index);
			}
			driver.switchTo().window(windowsId.get(index));
			//Log.info(index);
}
catch(Exception e)
{
	throw new Exception(e.getMessage());
}
}

		public void switchToParentWindow() throws Exception {
			try{
			LinkedList<String> windowsId = new LinkedList<String>(getWindowHandlens());
			driver.switchTo().window(windowsId.get(0));
			//Log.info("");
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}

		public void switchToParentWithChildClose() throws Exception {
			try{
			LinkedList<String> windowsId = new LinkedList<String>(getWindowHandlens());

			for (int i = 1; i < windowsId.size(); i++) {
				//Log.info(windowsId.get(i));
				driver.switchTo().window(windowsId.get(i));
				driver.close();
			}

			switchToParentWindow();
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}
		

		
		
	//drop down

	public void SelectUsingVisibleValue(WebElement element,String visibleValue) throws Exception {
		try{
			Select select = new Select(element);
			select.selectByVisibleText(visibleValue);
			//Log.info("Locator : " + element + " Value : " + visibleValue);
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}

		public String getSelectedValue(WebElement element) throws Exception {
			try{
			String value = new Select(element).getFirstSelectedOption().getText();
			//Log.info("WebELement : " + element + " Value : "+ value);
			return value;
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}
		
		public void SelectUsingIndex(WebElement element,int index) throws Exception {
			try{
			Select select = new Select(element);
			select.selectByIndex(index);
			//Log.info("Locator : " + element + " Value : " + index);
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}
		
		
		public List<String> getAllDropDownValues(WebElement locator) throws Exception {
			try{
			Select select = new Select(locator);
			List<WebElement> elementList = select.getOptions();
			List<String> valueList = new LinkedList<String>();
			
			for (WebElement element : elementList) {
				//Log.info(element.getText());
				valueList.add(element.getText());
			}
			return valueList;
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}
	
	//Generic Helper

	public String readValueFromElement(WebElement element) throws Exception {
try{
			if (null == element){
				//log.info("weblement is null");
				return null;
			}

			boolean displayed = false;
			try {
				displayed = isDisplayed(element);
			} catch (Exception e) {
				//log.error(e);
				return null;
			}

			if (!displayed){
				return null;
			}
			String text = element.getText();
			//log.info("weblement valus is.."+text);
			return text;
}
catch(Exception e)
{
	throw new Exception(e.getMessage());
}
}
		

		public String readValueFromInput(WebElement element) throws Exception {
			try{
			if (null == element){
				return null;
			}
			if (!isDisplayed(element)){
				return null;
			}
			String value = element.getAttribute("value");
			//log.info("weblement valus is.."+value);
			return value;
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}
		public boolean isDisplayed(WebElement element) {
			try {
				element.isDisplayed();
				//log.info("element is displayed.."+element);
				return true;
			} catch (Exception e) {
			//	log.info(e);
				return false;
			}
		}
		
		protected boolean isNotDisplayed(WebElement element) {
			try {
				element.isDisplayed();
				//log.info("element is displayed.."+element);
				return false;
			} catch (Exception e) {
				//log.error(e);
				return true;
			}
		}
		
		protected String getDisplayText(WebElement element) throws Exception {
			try{
			if (null == element){
				return null;
			}
			if (!isDisplayed(element)){
				return null;
			}
			return element.getText();
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}
		

		public  String getElementText( WebElement element) throws Exception {
			try{
			if (null == element) {
				//log.info("weblement is null");
				return null;
			}
			String elementText = null;
			try {
				elementText = element.getText();
			} catch (Exception ex) {
				//log.info("Element not found " + ex);
			}
			return elementText;
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}
	
	//Java script helper

	public Object executeScript(String script) throws Exception {
		try{
			JavascriptExecutor exe = (JavascriptExecutor) driver;
			//Log.info(script);
			return exe.executeScript(script);
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}

		public Object executeScript(String script, Object... args) throws Exception {
			try{
			JavascriptExecutor exe = (JavascriptExecutor) driver;
			//Log.info(script);
			return exe.executeScript(script, args);
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}

		public void scrollToElemet(WebElement element) throws Exception {
			try{
			executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
			//Log.info(element);
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}

		public void scrollToElemetAndClick(WebElement element) throws Exception {
			try{
			scrollToElemet(element);
			element.click();
			//Log.info(element);
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}

		public void scrollIntoView(WebElement element) throws Exception {
			try{
			executeScript("arguments[0].scrollIntoView()", element);
			//Log.info(element);
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}

		public void scrollIntoViewAndClick(WebElement element) throws Exception {
			try{
			scrollIntoView(element);
			element.click();
			//Log.info(element);
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}

		public void scrollDownVertically() throws Exception {
			try{
			executeScript("window.scrollTo(0, document.body.scrollHeight)");
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}

		public void scrollUpVertically() throws Exception {
			try{
			executeScript("window.scrollTo(0, -document.body.scrollHeight)");
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}

		public void scrollDownByPixel() throws Exception {
			try{
			executeScript("window.scrollBy(0,1500)");
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}

		public void scrollUpByPixel() throws Exception {
			try{
			executeScript("window.scrollBy(0,-1500)");
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}

		public void ZoomInBypercentage() throws Exception {
			try{
			executeScript("document.body.style.zoom='40%'");
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}

		public void ZoomBy100percentage() throws Exception {
			try{
			executeScript("document.body.style.zoom='100%'");
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}
	
	//Wait helper

	public void setImplicitWait(long timeout, TimeUnit unit) throws Exception {
			//Log.info(timeout);
		try{
			driver.manage().timeouts().implicitlyWait(timeout, unit == null ? TimeUnit.SECONDS : unit);
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
		
		private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) throws Exception {
			//Log.debug("");
			try{
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.pollingEvery(pollingEveryInMiliSec, TimeUnit.MILLISECONDS);
			wait.ignoring(NoSuchElementException.class);
			wait.ignoring(ElementNotVisibleException.class);
			wait.ignoring(StaleElementReferenceException.class);
			wait.ignoring(NoSuchFrameException.class);
			return wait;
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}
		public void waitForElementVisible(WebElement locator, int timeOutInSeconds, int pollingEveryInMiliSec) throws Exception {
			//Log.info(locator);
			try{
			WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
			wait.until(ExpectedConditions.visibilityOf(locator));
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}
		
		public void waitForElement(WebDriver driver, WebElement element, long timeout) throws Exception {
			try{
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.visibilityOf(element));
			//Log.info("element found..."+element.getText());
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}
		
		public WebElement waitForElement(long time,WebElement element) throws Exception{
			try{
			WebDriverWait wait = new WebDriverWait(driver, time);
			return wait.until(ExpectedConditions.elementToBeClickable(element));
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}
		

	//Date time helper

	public  String getCurrentDateTime() throws Exception {
try{
			DateFormat dateFormat = new SimpleDateFormat("_yyyy-MM-dd_HH-mm-ss");
			Calendar cal = Calendar.getInstance();
			String time = "" + dateFormat.format(cal.getTime());
			return time;
}
catch(Exception e)
{
	throw new Exception(e.getMessage());
}
}

	public String getCurrentDate() throws Exception {
			try{
			return getCurrentDateTime().substring(0, 11);
			}
			catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
		}

	public boolean waitForExpectedCondition(String locator) throws Exception {
		boolean flag = false;
		
		String[] split = locator.split(":");
		String identifyBy = split[0];
		 locator = split[1];
		System.out.println("locatorType:-" + identifyBy);
		System.out.println("locatorValue:-" + locator);
		
		if (identifyBy.equalsIgnoreCase("id")) {
			try {
				//int explicitWaitTimeOut=conf.getExplicitWait();
				//System.out.println(explicitWaitTimeOut);
				//WebDriverWait wait = new WebDriverWait(driver, explicitWaitTimeOut);
				wait.until(ExpectedConditions.elementToBeClickable((By.id(locator))));
				flag = true;
			} catch (Exception e) {
				throw new Exception("Exception is occured and detailsa are:"+e.getMessage());
				//e.printStackTrace();
			}
		} else if (identifyBy.equalsIgnoreCase("name")) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, 15);
				wait.until(ExpectedConditions.elementToBeClickable((By.name(locator))));
				flag = true;
			} catch (Exception e) {
				throw new Exception("Exception is occured and detailsa are:"+e.getMessage());
				//e.printStackTrace();
			}
			
		} else if (identifyBy.equalsIgnoreCase("linkText")) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, 120);
				wait.until(ExpectedConditions.elementToBeClickable((By.linkText(locator))));
				flag = true;
			} catch (Exception e) {
				throw new Exception("Exception is occured and detailsa are:"+e.getMessage());
				//e.printStackTrace();
			}
		} else if (identifyBy.equalsIgnoreCase("cssSelector")) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, 180);
				wait.until(ExpectedConditions.elementToBeClickable((By.cssSelector(locator))));
				
				flag = true;
			} catch (Exception e) {
				throw new Exception("Exception is occured and detailsa are:"+e.getMessage());
				//e.printStackTrace();
			}
		} else if (identifyBy.equalsIgnoreCase("className")) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, 40);
				wait.until(ExpectedConditions.elementToBeClickable((By.className(locator))));
				flag = true;
			} catch (Exception e) {
				throw new Exception("Exception is occured and detailsa are:"+e.getMessage());
				//e.printStackTrace();
			}
		} else if (identifyBy.equalsIgnoreCase("xpath")) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, 40);
				wait.until(ExpectedConditions.elementToBeClickable((By.xpath(locator))));
				flag = true;
			} catch (Exception e) {
				throw new Exception("Exception is occured and detailsa are:"+e.getMessage());
				//e.printStackTrace();
			}
		}
		return flag;
	}
	public void switchToFrame(String nameOrId) throws Exception {
		try{
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
		//Log.info(nameOrId);
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
	public void switchToMultipleFrames(String outerFrame, String innerFrame) throws Exception {
				Boolean swithToFrame=false;
				try{
				 	//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(outerFrame));
					WebDriverWait wait = new WebDriverWait(driver,30);
				 	if(innerFrame!="")
				 	{
				 	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(innerFrame));
				 	}
				 	swithToFrame=true;
				}
				catch(Exception e)
				{
					
					throw new Exception("Exception has occured while switching to frame:"+e.getMessage());
					
				}
				
			
		
		
	}
}
