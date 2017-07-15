package com.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class WebTable extends FunctionLibrary{
	
	
  @Test
  public void tc005_webtable() {
	  
	  RemoteWebDriver driver = getDriver();
	  
	  driver.get("https://www.w3schools.com/html/html_tables.asp");
	  
	  System.out.println(
			  driver.findElement(By.xpath(".//*[@id='customers']/tbody/tr/td[3][text()='Austria']/parent::tr/td[1]"))
			  .getText());
	  
	  
  }
}
