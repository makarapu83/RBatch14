package com.selenium.tests;

import java.io.IOException;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import com.selenium.pages.FlightFinderPage;
import com.selenium.pages.MTSignInPage;

public class FlightFinderTest extends FunctionLibrary{

	@Test
	public void tc002_flight() throws IOException {		  
		RemoteWebDriver driver = getDriver();
		
		 MTSignInPage mTS = new MTSignInPage(driver); 		  
		 mTS.loginToMercury(getData("usrName"), getData("pwd"));
		
		FlightFinderPage fPage =  new FlightFinderPage(driver);
		fPage.tripSelection();
		
		System.out.println("The selected value is "+ fnGetSelectedDropDownValue(fPage._passengerCnt));
					
		//fnSelectByValue(fPage._passengerCnt, "2");
		//fnSelectByVisibleText(fPage._passengerCnt, "3");
		fnSelectByIndex(fPage._passengerCnt, 3);

	}

}
