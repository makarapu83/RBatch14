package com.selenium.tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class AlertHandle extends FunctionLibrary{
	
	
  @Test(groups={"alert","smoke"})
  public void test005_alert() {
	  RemoteWebDriver driver = getDriver();
	  driver.get("file:///F:/Techies/Backup_data_6years/Learning/MyNotes/confirmation.html");
	  driver.findElement(By.xpath("//button")).click();
	  
	  //fnAlertAccept(driver);
	  fnAlertDismiss(driver);
	  
	  
  }
}
