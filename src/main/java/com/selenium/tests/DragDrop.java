package com.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class DragDrop extends FunctionLibrary{
	
  @Test
  public void tc003_dragdrop() {
	  
	  RemoteWebDriver driver = getDriver();
	  
	  driver.get("http://jqueryui.com/droppable/");
	  //driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));
  
	  //Common method for wait and once frame is available , it will switch to it.
	  fnWaitforFrameAndSwitch(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));
	  
	  //Common method for drag and drop
	  dragDrop(driver.findElement(By.xpath("//div[@id='draggable']")),
			  driver.findElement(By.xpath("//div[@id='droppable']")));	  
	  
	  driver.switchTo().defaultContent();
	  
	  
  }
}
