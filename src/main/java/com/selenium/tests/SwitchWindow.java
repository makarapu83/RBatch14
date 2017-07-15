package com.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;



public class SwitchWindow extends FunctionLibrary{	
		
	Logger log = LoggerFactory.getLogger(getClass());
			
	@Test(groups = {"switchex","Regression","Mahendar"})
  public void test006_switch() throws InterruptedException {
		
		RemoteWebDriver driver = getDriver(); //initialize driver
		
		//Open application
		driver.get("http://toolsqa.com/automation-practice-switch-windows/");
		
		//Get current main window
		String mainWin =driver.getWindowHandle();		
		
		log.info("The title of main window is " + driver.getTitle());
		
		fnwaitForElementClickable(driver.findElement(By.cssSelector("#button1")));
		//CLick on New Window button
		clickByJs(driver.findElement(By.cssSelector("#button1")));
		
		//Switch to main window
		fnSwitchChildWindow(mainWin);
		
		
		
		
  }
}
