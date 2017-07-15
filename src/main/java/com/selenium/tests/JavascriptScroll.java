package com.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class JavascriptScroll extends FunctionLibrary{
	
	
  @Test
  public void tc005_webtable() {
	  
	  RemoteWebDriver driver = getDriver();
	  
	  driver.get("https://www.w3schools.com/html/html_tables.asp");
	  
	  JavascriptExecutor je = (JavascriptExecutor)threadDriver.get();
	  je.executeScript("scroll(0,750);");
	
	  
  }
}
