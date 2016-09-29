package com.stocksValidation;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.stocksValidation.utils.TestInfo;

public class ChangePercentDescendingTest extends TestSetup{


	@Test
	public void changePercentIsDescending(){
		TestInfo.startTest(this.getClass().getSimpleName()+"_"+"changePercentIsDescending");
		StocksWatchPage swp = new StocksWatchPage(driver);
		ArrayList<Float> changePercent= swp.getPercentChange();
		
		
		//Compare fist element with 2nd element from change percent column all the way till last element.
		
		for (int i = 0; i < (changePercent.size()-1); i++) {
			int comparisionResult=Float.compare((changePercent.get(i)),changePercent.get(i+1));
			//In case two values are same, the test still passes.
			System.out.println("INFO: verifing that "+changePercent.get(i)+" is greater >= "+changePercent.get(i+1));
			if(comparisionResult== 0)
				comparisionResult=1;
			Assert.assertEquals(comparisionResult, 1);			
		}
	TestInfo.endTest(this.getClass().getSimpleName()+"_"+"changePercentIsDescending");
	}

}
