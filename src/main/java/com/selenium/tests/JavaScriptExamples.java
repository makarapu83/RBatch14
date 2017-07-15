package com.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class JavaScriptExamples extends FunctionLibrary {
  @Test
  public void f() {
	  RemoteWebDriver driver = getDriver();
	  driver.get("http://www.amazon.in/");
	  Actions act = new Actions(driver);
	  act.moveToElement(driver.findElement(By.xpath(".//*[@id='nav-link-yourAccount']/span[1]")));
	  act.build().perform();
	  
	  //driver.findElement(By.xpath(".//*[@id='nav-flyout-ya-signin']/a/span[text()='Sign in']")).click();
	  
	  //Clicking by using Java script
	  clickByJs(driver.findElement(
			  By.xpath(".//*[@id='nav-flyout-ya-signin']/a/span[text()='Sign in']")));
  }
}
