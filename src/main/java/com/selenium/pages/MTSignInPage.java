package com.selenium.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class MTSignInPage {
	RemoteWebDriver driver;
	
	@FindBy(name="userName")  WebElement _uName;	
	@FindBy(name="password") WebElement _pwd;
	@FindBy(name="login") WebElement _signin;
	
	@FindBy(how=How.NAME, using="tripType") public WebElement _tripTypeRadio;
	
	public MTSignInPage(RemoteWebDriver driver){		
		this.driver=driver;
		PageFactory.initElements(driver, this);		
	}
	
	
  public void loginToMercury(String u, String p) {
	  driver.get("http://newtours.demoaut.com/");
	  
	  _uName.clear();
	  _uName.sendKeys(u);
	  _pwd.sendKeys(p);
	  _signin.click();
	  Assert.assertTrue(_tripTypeRadio.isDisplayed());
  }
}
