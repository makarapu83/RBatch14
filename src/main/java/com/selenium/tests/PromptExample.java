package com.selenium.tests;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class PromptExample extends FunctionLibrary{
	
	
  @Test
  public void f() {
	  
	  RemoteWebDriver driver = getDriver();
	  
	  driver.get("file:///F:/Techies/Backup_data_6years/Learning/MyNotes/prompt.html");
	  driver.findElement(By.xpath("//button")).click();
	  
	  Alert al = driver.switchTo().alert();
	  al.sendKeys("Mahendar");
	  al.accept();
  }
}
