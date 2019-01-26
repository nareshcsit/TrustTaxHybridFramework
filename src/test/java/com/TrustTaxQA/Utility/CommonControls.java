package com.TrustTaxQA.Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.TrustTaxQA.BaseClass;
//This class is for Web element Methods like click button,type input,Select value etc
public class CommonControls {

	public static void typeinEditBox(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);

	}

	public static void click(WebElement element) {

		element.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void selectDropdown(WebElement element,String identifiedValue) {

		Select dropdown= new Select(element);
		//dropdown.selectByValue("");
		dropdown.selectByVisibleText(identifiedValue.trim());
		//dropdown.selectByIndex(1);
	}
	public  static  void JSclick(WebElement element,WebDriver driver) throws InterruptedException {
		
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		Thread.sleep(1000);

	}

	public void waitForExpectedCondition()
	{
		
	}
	
}
