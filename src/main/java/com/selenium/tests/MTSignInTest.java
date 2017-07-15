package com.selenium.tests;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.selenium.pages.MTSignInPage;

public class MTSignInTest extends FunctionLibrary{
	
	
	
  @Test
  public void tc001_signIn() throws IOException {
	  RemoteWebDriver driver = getDriver();	  
	  
	  MTSignInPage mTS = new MTSignInPage(driver); 	  
	  //mTS.loginToMercury(System.getenv("uName"), System.getenv("Password")); 
	  
	  mTS.loginToMercury(getData("usrName"), getData("pwd"));
	  
	  
  }
}
