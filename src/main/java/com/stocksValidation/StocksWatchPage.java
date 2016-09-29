package com.stocksValidation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class StocksWatchPage {

	public WebDriver driver;
	
	//Page factory annotation to eliminate stale object errors and readability.
	
	@FindBy(xpath=".//*[@id='advDecData']/li[1]")
	WebElement advancesElement;
	
	@FindBy(xpath=".//*[@id='advDecData']/li[2]")
	WebElement declinesElement;

	
	public StocksWatchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Get all the column heading to validate all columns are as expected.
	
	public List<WebElement> getColumnHeadings(){
		WebElement firstRow=driver.findElement(By.xpath("//*[@id='dataTable']/tbody/tr[1]"));
		List<WebElement> columnHeadings=firstRow.findElements(By.tagName("th"));
		return columnHeadings;
	}
	//Get opening value per stock, populate in an ArrayList and return.
	
	public ArrayList<Float> getOpening(){
		ArrayList<Float> openingValues= new ArrayList<Float>();
		int tableRowsSize=driver.findElements(By.xpath("//*[@id='dataTable']/tbody/tr")).size();
		
		//Nifty cummulative should be compared as well, so we start from 2nd row in table.
		
		for (int i = 3; i <= tableRowsSize; i++) {
			//Collect open values per row in strin and prase it into Intger ArrayList.
			
			String openValue=driver.findElement(By.xpath("//*[@id='dataTable']/tbody/tr["+i+"]/td[4]")).getText();
			openValue=openValue.replace(",", "");
			openingValues.add(Float.parseFloat(openValue));
		}
		
		return openingValues;
	}
	
	
	//Get change value per stock, populate in an ArrayList and return.
	
	public ArrayList<Float> getChange(){
		ArrayList<Float> changeValues= new ArrayList<Float>();
		int tableRowsSize=driver.findElements(By.xpath("//*[@id='dataTable']/tbody/tr")).size();
		for (int i = 3; i <= tableRowsSize; i++) {
			String changeValue=driver.findElement(By.xpath("//*[@id='dataTable']/tbody/tr["+i+"]/td[8]")).getText();
			changeValue=changeValue.replace(",", "");
			changeValues.add(Float.parseFloat(changeValue));
		}
		
		return changeValues;
	}
	
	//Get first row containing cumulative data
	
	public ArrayList<Float> getNiftyCummlative(){
		ArrayList<Float> firstRow=new ArrayList<Float>();
		//Validate the row name is nifty
		String rowName=driver.findElement(By.id("gettd")).getText();

		//Assert for this value here as ArrayList is of type float.
		Assert.assertEquals("NIFTY 50",rowName);
					
		//Store Opening value
		firstRow.add(Float.parseFloat(driver.findElement(By.xpath("//*[@id='dataTable']/tbody/tr[2]/td[4]")).getText().replace(",", "")));
		//Store change
		firstRow.add(Float.parseFloat(driver.findElement(By.xpath("//*[@id='dataTable']/tbody/tr[2]/td[8]")).getText().replace(",", "")));
		//Store percent change
		firstRow.add(Float.parseFloat(driver.findElement(By.xpath("//*[@id='dataTable']/tbody/tr[2]/td[9]")).getText().replace(",", "")));
		return firstRow;
	}
	
	
	//Get change percent value per stock, populate in an ArrayList and return.
	
	public ArrayList<Float> getPercentChange(){
		ArrayList<Float> changePercentValues= new ArrayList<Float>();
		int tableRowsSize=driver.findElements(By.xpath("//*[@id='dataTable']/tbody/tr")).size();
		for (int i = 3; i <= tableRowsSize; i++) {
			String changePercentValue=driver.findElement(By.xpath("//*[@id='dataTable']/tbody/tr["+i+"]/td[9]")).getText();
			changePercentValue=changePercentValue.replace(",", "");

			changePercentValues.add(Float.parseFloat(changePercentValue));
		}
		
		return changePercentValues;
	}
	

	
	// Code to filter advances from table below.
	
	//Split the text and send  element before "-"
	public String getAdvancesTextFromHeading(){
		String[] parts = advancesElement.getText().split("-");
		String firstText=parts[0].trim();
		return firstText;	
	}
	
	public String getAdvancesCountFromHeading(){
		String[] parts = advancesElement.getText().split("-");
		String count=parts[1].trim();
		return count;	
	}
	
	public String getAdvancesCountFromTable(){
		//Get the number of rows in the table
		
		int tableRowsSize=driver.findElements(By.xpath("//*[@id='dataTable']/tbody/tr")).size();
		int advanceCountFromTable=0;
		
		//Check that the title of column in chng
		
		String columnTitle=driver.findElement(By.xpath("//*[@id='dataTable']/tbody/tr[1]/th[8]")).getText();
		if(!columnTitle.equals("Chng")){
			throw new IllegalArgumentException("ERROR: Incorrect column, was expecting \"Chng\"");
		}
		//Start with third row as first is heading and 2nd is total sensex.
		//Column number 8 contains the net changes from previous day
		for (int i = 3; i <= tableRowsSize; i++) {
			String changeValue=driver.findElement(By.xpath("//*[@id='dataTable']/tbody/tr["+i+"]/td[8]")).getText();
			if(changeValue.contains("-")){
				break;
			}else if(changeValue.equals("0") || changeValue.equals("0.00")){
				break;
			}else
				advanceCountFromTable++;
		}
		
		return String.valueOf(advanceCountFromTable);
		
	}
	
	
	// Code to filter declines from table below.
	
	//Split the text and send  element before "-"
	public String getDeclinesTextFromHeading(){
		String[] parts = declinesElement.getText().split("-");
		String firstText=parts[0].trim();
		return firstText;	
	}
	
	public String getDeclinesCountFromHeading(){
		String[] parts = declinesElement.getText().split("-");
		String count=parts[1].trim();
		return count;	
	}
	
	public String getDeclinesCountFromTable(){
		//Get the number of rows in the table
		
		int tableRowsSize=driver.findElements(By.xpath("//*[@id='dataTable']/tbody/tr")).size();
		int declinesCountFromTable=0;
		
		//Check that the title of column in chng
		
		String columnTitle=driver.findElement(By.xpath("//*[@id='dataTable']/tbody/tr[1]/th[8]")).getText();
		if(!columnTitle.equals("Chng")){
			throw new IllegalArgumentException("ERROR: Incorrect column, was expecting \"Chng\"");
		}
		
		//Start with third row as first is heading and 2nd is total sensex.
		//Column number 8 contains the net changes from previous day
		for (int i = 3; i <= tableRowsSize; i++) {
			String changeValue=driver.findElement(By.xpath("//*[@id='dataTable']/tbody/tr["+i+"]/td[8]")).getText();
			if (changeValue.contains("-")){
				declinesCountFromTable++;
			}
		}
		
		return String.valueOf(declinesCountFromTable);
	}
}
