package com.stocksValidation;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.stocksValidation.utils.TestInfo;

import junit.framework.Assert;

public class StockDeclineTest extends TestSetup{
	
	//The first element should be Advances
	@Test
	private void validateFirstElementIsDeclines(){
		TestInfo.startTest(this.getClass().getSimpleName()+"_"+"validateFirstElementIsDeclines");
		StocksWatchPage swp = new StocksWatchPage(driver);
		String firstText=swp.getDeclinesTextFromHeading();
		System.out.println("INFO: The heading of column is: "+firstText);
		Assert.assertEquals("Declines", firstText);
		TestInfo.endTest(this.getClass().getSimpleName()+"_"+"validateFirstElementIsDeclines");
	}
	
	//Validate second element is an int
	@Test
	private void validateSecondElementInDeclinesIsInt(){
		TestInfo.startTest(this.getClass().getSimpleName()+"_"+"validateSecondElementInDeclinesIsInt");
		StocksWatchPage swp = new StocksWatchPage(driver);
		String countOfAdvances=swp.getDeclinesCountFromHeading();
		Assert.assertTrue(countOfAdvances.matches("\\d+"));
		TestInfo.endTest(this.getClass().getSimpleName()+"_"+"validateSecondElementInDeclinesIsInt");
	}
	
	@Test
	private void matchDeclinesInHeadingAndTable(){
		TestInfo.startTest(this.getClass().getSimpleName()+"_"+"matchDeclinesInHeadingAndTable");
		StocksWatchPage swp = new StocksWatchPage(driver);
		String countOfDeclinesFromHeading=swp.getDeclinesCountFromHeading();
		String countOfDeclinesFromTable=swp.getDeclinesCountFromTable();
		System.out.println("INFO: Count of Declines from heading: "+countOfDeclinesFromHeading);
		System.out.println("INFO: Count of Declines from Table: "+countOfDeclinesFromTable);
		Assert.assertEquals(countOfDeclinesFromHeading, countOfDeclinesFromTable);
		TestInfo.endTest(this.getClass().getSimpleName()+"_"+"matchDeclinesInHeadingAndTable");
	}
	

}
