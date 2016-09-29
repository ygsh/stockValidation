package com.stocksValidation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.base.Verify;
import com.stocksValidation.utils.TestInfo;


public class ChangePercentValidationTest extends TestSetup{

	//Validate all the three required heading in below test
	@Test
	public void validateColumnHeadings(){
		TestInfo.startTest(this.getClass().getSimpleName()+"_"+"validateColumnHeadings");
		List<WebElement> columnHeading=new ArrayList<WebElement>();
		StocksWatchPage swp = new StocksWatchPage(driver);
		columnHeading=swp.getColumnHeadings();
		Assert.assertEquals(columnHeading.get(3).getText(), "Open");
		Assert.assertEquals(columnHeading.get(7).getText(), "Chng");
		Assert.assertEquals(columnHeading.get(8).getText(), "% Chng");
		TestInfo.endTest(this.getClass().getSimpleName()+"_"+"validateColumnHeadings");
	}
	@Test
	public void validateChangePercentNiftyCumulative(){
		TestInfo.startTest(this.getClass().getSimpleName()+"_"+"validateChangePercentNiftyCumulative");
		StocksWatchPage swp = new StocksWatchPage(driver);
		ArrayList<Float> niftyCummulative=swp.getNiftyCummlative();
		
		//Validate for Nifty cumulative separately as stocks data for individual stocks is in a separate table
		
		Float niftyOpening=niftyCummulative.get(0);
		Float niftyChange=niftyCummulative.get(1);
		Float niftyChangePercent=niftyCummulative.get(2);
		
		Float calculatedNiftyPercent=(niftyChange/niftyOpening) * 100;
		calculatedNiftyPercent=(float) (Math.round(calculatedNiftyPercent * 100.00)/100.00);
		
		System.out.println("INFO: Change percent from server: "+niftyChangePercent);
		System.out.println("INFO: Change percent calculated in test: "+calculatedNiftyPercent);
		
		Assert.assertTrue(calculatedNiftyPercent.equals(niftyChangePercent));
		TestInfo.endTest(this.getClass().getSimpleName()+"_"+"validateChangePercentNiftyCumulative");
	}
	
	@Test
	public void validateChangePercentAllStocks(){
		TestInfo.startTest(this.getClass().getSimpleName()+"_"+"validateChangePercentAllStocks");
		StocksWatchPage swp = new StocksWatchPage(driver);
		ArrayList<Float> openValues=swp.getOpening();
		ArrayList<Float> changeValues=swp.getChange();
		ArrayList<Float> changePercent=swp.getPercentChange();
		
		
		//Compare individual stock percent changes for the day below and verify each row.
		
		for (int i = 0; i < openValues.size(); i++) {
			Float calculatedPercent=(changeValues.get(i) / openValues.get(i)) * 100;
			Float calculatedPercentRound=(float) (Math.round(calculatedPercent*100.0)/100.0);
			System.out.println("INFO: Change percent from server: "+changePercent.get(i));
			System.out.println("INFO: Change percent calculated in test: "+calculatedPercentRound);
			Assert.assertTrue(changePercent.get(i).equals(calculatedPercentRound));
			TestInfo.endTest(this.getClass().getSimpleName()+"_"+"validateChangePercentAllStocks");

		}
		
	}
	

}
