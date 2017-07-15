package com.actions.programs;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import com.selenium.tests.FunctionLibrary;

public class MultiSelect extends FunctionLibrary {
	
  @Test
  public void f() {
	  RemoteWebDriver driver = getDriver();
	  driver.get("file:///F:/Techies/Backup_data_6years/Learning/MyNotes/multiSelect.html");
	  
	  Actions act = new Actions(driver);
	  act.keyDown(Keys.CONTROL); // Press Control Key
	  fnSelectByIndex(driver.findElement(By.xpath(".//*[@id='chkveg']")), 1); // select a value
	  fnSelectByIndex(driver.findElement(By.xpath(".//*[@id='chkveg']")), 2); // select a secondvalue
	  act.keyUp(Keys.CONTROL);//Release the Control key
	  
  }
}
