package com.TrustTaxQA.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.TrustTaxQA.BaseClass;

public class AccountPage extends BaseClass {
	@FindBy(id="usernameOrEmail")public WebElement acctNumber;
}
