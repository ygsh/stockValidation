package com.stocksValidation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.stocksValidation.utils.TestInfo;

import junit.framework.Assert;

public class PageSetupTest extends TestSetup{


	//Validate page loads with correct title
	@Parameters({"BasePageTitle"})
	@Test
	private void validateStocksPageLoads(String expectedtitle){
		TestInfo.startTest(this.getClass().getSimpleName()+"_"+"validateStocksPageLoads");
		String actualTitle=driver.getTitle();
		Assert.assertEquals(actualTitle, expectedtitle);
		TestInfo.endTest(this.getClass().getSimpleName()+"_"+"validateStocksPageLoads");
	}
	
	//Validate presence of only one Table on the landing page
	
	@Test
	private void validateStocksPageUIelements(){
		int sizeOfTable=driver.findElements(By.id("dataTable")).size();
		Assert.assertEquals(1, sizeOfTable);
	}
}
