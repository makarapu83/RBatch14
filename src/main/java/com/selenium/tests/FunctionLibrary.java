package com.selenium.tests;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class FunctionLibrary {

	ThreadLocal<RemoteWebDriver> threadDriver = new ThreadLocal<RemoteWebDriver>();
	Logger log = LoggerFactory.getLogger(getClass());
	
	@BeforeMethod(alwaysRun=true)
	@Parameters("browser")
	public void beforeMethod(@Optional("firefox") String browser){
		log.info("In before method" + browser);
		RemoteWebDriver driver=null;
		
		switch(browser){
		
		case "firefox":
			driver = new FirefoxDriver();
			threadDriver.set(driver);
			break;
		case "ie":
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "IE");
			capabilities.setCapability(CapabilityType.VERSION, "11");
			capabilities.setCapability("nativeEvents", true);
			capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability("ignoreZoomSetting", true);			
			System.setProperty("webdriver.ie.driver", "Drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver(capabilities);
			threadDriver.set(driver);
			break;
		case "chrome":
			driver = new ChromeDriver();
			threadDriver.set(driver);
		default:
			System.out.println("Please pass correct browser");
		}
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	} 
	
	
	public RemoteWebDriver getDriver(){
		return threadDriver.get();
	}
	
	
	public String getData(String key) throws IOException{
		InputStream fis = getClass().getResourceAsStream("/testData.properties");
		  Properties p = new Properties();
		  p.load(fis);
		  System.out.println("The data get is " + p.getProperty(key));
		  return p.getProperty(key);
	}
	
	
	public String fnGetSelectedDropDownValue(WebElement element){
		Select select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}
	

	public void fnSelectByValue(WebElement element, String val){
		Select select = new Select(element);
		select.selectByValue(val);
	}
	
	
	public void fnSelectByVisibleText(WebElement element, String txt){
		Select select = new Select(element);		
		select.selectByVisibleText(txt);		
	}
	
	public void fnSelectByIndex(WebElement element, int ind){
		Select select = new Select(element);		
		select.selectByIndex(ind);		
	}
	
	public void fnAlertAccept(RemoteWebDriver driver){
		Alert al = driver.switchTo().alert();
		  al.accept();
		  System.out.println( al.getText());		  
	}
	
	public void fnAlertDismiss(RemoteWebDriver driver){
		Alert al = driver.switchTo().alert();
		  al.dismiss();
		  //System.out.println( al.getText());		  
	}
	
	public String fnGetAlerttext(RemoteWebDriver driver){
		Alert al = driver.switchTo().alert();
		 // al.dismiss();
		al.accept();
		  return al.getText();	
		  
	}
	
	public void dragDrop(WebElement source, WebElement target){
		
		  Actions act = new Actions(threadDriver.get());
		  act.dragAndDrop(source,
				  target);
		  
		  act.build().perform();
	}
	
	public void fnWaitforFrameAndSwitch(WebElement element){
		//Initialize webDriver wait
		WebDriverWait wait = new WebDriverWait(threadDriver.get(), 10);
		  //Wait for frame to be available
		  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt
				  (element));  
		
	}
	
	public void clickByJs(WebElement element){
		
		JavascriptExecutor je = (JavascriptExecutor)threadDriver.get();
		  je.executeScript("arguments[0].click()", element );		
	}
	
	@AfterMethod(alwaysRun=true)
	public void afterMethod(ITestResult result){
		log.info(result.toString());

		String testCase = result.getName();

		if(!(ITestResult.FAILURE==result.getStatus())){ //true
			log.info("Execution result: "+testCase+ " Test Result is -- PASS");			
		}else if(ITestResult.FAILURE == result.getStatus()){ //true
			log.info("Execution result: "+testCase+ " Test Result is -- FAIL");	
			
			String screenshotName = result.getName()+"_"+getTimeStamp()+".png";
			try{

				File file = new File("Screenshots");
				if(!file.exists()){
					file.mkdirs();
				}
				File targetFile = new File("Screenshots",screenshotName);
				File screenShotFile = ((TakesScreenshot) threadDriver.get()).getScreenshotAs(OutputType.FILE);

				FileUtils.copyFile(screenShotFile, targetFile);
			}catch(Exception e){
				log.info("An exception occured while taking screenshot "+e.getCause());
			}			
		}

		if(threadDriver.get() !=null){
			threadDriver.get().close();
			threadDriver.get().quit();
			
		}
	}

	public String getTimeStamp(){
		String timeNow;
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Calendar cal = Calendar.getInstance();
		timeNow = dateFormat.format(cal.getTime());
		return timeNow;
	}
	
	
	public void fnSwitchChildWindow(String mainWindow) throws InterruptedException{
		
		
		Set<String> allWindows = threadDriver.get().getWindowHandles();
		for(String window : allWindows){ // 2 windows -- 1 is mainWindow and other is child window
			if(!window.equals(mainWindow)){ // true
				threadDriver.get().switchTo().window(window);
				Thread.sleep(3000);
				log.info("The child window is "+ threadDriver.get().getTitle());
			}
		}
	}
	
	
	
	public void fnwaitForElementClickable(WebElement element){
		
		WebDriverWait wait= new WebDriverWait(threadDriver.get(), 30);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
