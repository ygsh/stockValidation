package com.stocksValidation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.stocksValidation.utils.OsUtils;

public class TestSetup {
	
	public static WebDriver driver=null;

	public WebDriver getDriver() {
		return driver;
	}
	
	@BeforeMethod
	private void setUp(){
		driver=getDriver();
	}
	

	@BeforeMethod
	@Parameters({"base_url","feature_url","browser"})
	public static void setDriver(String base_url, String feature_url, String browser){

		if(browser.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver","libs\\geckodriver.exe");

			driver = new FirefoxDriver();
		}else if (browser.equalsIgnoreCase("chrome")){
		
			//Set chromedriver for Mac and Linux below.
			
			if(OsUtils.isLinux())
				System.setProperty("webdriver.chrome.driver", "resources/lib/chromedriver_for_Linux");
			else if(OsUtils.isMac())
				System.setProperty("webdriver.chrome.driver", "resources/lib/chromedriver_for_Mac");
			else if(OsUtils.isWindows())
				System.setProperty("webdriver.chrome.driver", "resources/lib/chromedriver.exe");
			
			driver = new ChromeDriver();	
		}
		driver.get(base_url+feature_url);
		//During setup wait till the page loads and then continue execution.
		//Configured only for stocks watch page currently.
		WebDriverWait wait = new WebDriverWait(driver,30);
		if(feature_url.equals("/live_market/dynaContent/live_watch/equities_stock_watch.htm#")){
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("advDecData")));
		}
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();	
	}

}
