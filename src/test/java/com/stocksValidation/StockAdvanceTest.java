package com.stocksValidation;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.stocksValidation.utils.TestInfo;

import junit.framework.Assert;

public class StockAdvanceTest extends TestSetup{

	//The first element should be Advances
	@Test
	private void validateFirstElementIsAdvances(){
		TestInfo.startTest(this.getClass().getSimpleName()+"_"+"validateFirstElementIsAdvances");
		StocksWatchPage swp = new StocksWatchPage(driver);
		String firstText=swp.getAdvancesTextFromHeading();
		System.out.println("INFO: The heading of column is: "+firstText);
		Assert.assertEquals("Advances", firstText);
		TestInfo.endTest(this.getClass().getSimpleName()+"_"+"validateFirstElementIsAdvances");
		
	}
	
	//Validate second element is an int
	@Test
	private void validateSecondElementInAdvacesIsInt(){
		TestInfo.startTest(this.getClass().getSimpleName()+"_"+"validateSecondElementInAdvacesIsInt");
		StocksWatchPage swp = new StocksWatchPage(driver);
		String countOfAdvances=swp.getAdvancesCountFromHeading();
		Assert.assertTrue(countOfAdvances.matches("\\d+"));
		TestInfo.endTest(this.getClass().getSimpleName()+"_"+"validateSecondElementInAdvacesIsInt");
	}
	
	@Test
	private void matchAdvancesInHeadingAndTable(){
		TestInfo.startTest(this.getClass().getSimpleName()+"_"+"matchAdvancesInHeadingAndTable");
		StocksWatchPage swp = new StocksWatchPage(driver);
		String countOfAdvancesFromHeading=swp.getAdvancesCountFromHeading();
		String countOfAdvancesFromTable=swp.getAdvancesCountFromTable();
		System.out.println("INFO: Count of Advances from heading: "+countOfAdvancesFromHeading);
		System.out.println("INFO: Count of Advances from Table: "+countOfAdvancesFromTable);
		Assert.assertEquals(countOfAdvancesFromHeading, countOfAdvancesFromTable);
		TestInfo.endTest(this.getClass().getSimpleName()+"_"+"matchAdvancesInHeadingAndTable");
	}
	

}
