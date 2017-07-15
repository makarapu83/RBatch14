package com.selenium.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class FlightFinderPage{
	RemoteWebDriver driver;
	@FindBy(how=How.XPATH, using="//input[@value='oneway']") WebElement _oneWayTripType;
	@FindBy(how=How.XPATH, using="//select[@name='passCount']") public WebElement _passengerCnt;
	
	
	
	public FlightFinderPage(RemoteWebDriver driver){		
		this.driver=driver;
		PageFactory.initElements(driver, this);		
	}
	
	public void tripSelection(){
		_oneWayTripType.click();
	}
	
	
	
}
