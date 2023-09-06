package com.raghib.selenium.table.pagination;

//NOTE :- Try to type one import statement then only import reference will be provided by eclipse.

/*
* REFERENCE:-
* https://www.youtube.com/watch?v=p3q_Wy4AjHs
* https://www.youtube.com/watch?v=jD5eWhNsaRk&t=27s
*/

import java.util.List;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.raghib.selenium.BaseClass;

public class PaginationWebTableUsingBestWay extends BaseClass {
	
	public static void main(String args[]) {
		//countRowsandColumnsInPage();
		printNamesOfColumn();
	}

	static WebDriver driver;
	
	static String browserName = "chrome";
	static String browserVersion = "116";
	static String url = "https://datatables.net/examples/advanced_init/dt_events.html";
	
	static int totalRows = 0;
	static int totalRowsInPage = 0;
	static int totalColumns = 0;
	static int totalPages = 0;
	static int totalNames = 0;
	static String displayCounts = null; 

	static By noOfColumns = By.xpath("//table[@id='example']/thead/tr/th");
	static By noOfRows = By.xpath("//table[@id=\"example\"]/tbody/tr");
	
	static By particularCellValue = By.xpath("//table[@id='example']/tbody/tr/td[1]");
	
	static By noOfPages = By.xpath("//div[@id='example_paginate']/span/a"); 
	static By totalEntries = By.xpath("//div[@id='example_info']");
	
	static List<String> nameList = new ArrayList<String>();

	public static void getDriverandHitDatatablesURL() {
		// Chrome Browser
		driver = BaseClass.getDriver(browserName, browserVersion);
		// Modify Wait time as per the Network Ability.
		// From Selenium 4 onwards implicitWait and WebDriverWait(Explicit wait) for
		// long is deprecated.
		// So we can handle WebDriverWait in Selenium like below
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		driver.get(url);
	}

	public static void countRowsandColumnsInPage() {
		getDriverandHitDatatablesURL();
		
		// Finding number of Rows
		totalRowsInPage = driver.findElements(noOfRows).size();
		if (totalRowsInPage > 0) {
			System.out.println("No of rows in this page : " + totalRowsInPage);
		} else {
			System.out.println("No of rows in this page : " + totalRowsInPage);
		}

		// Finding number of Columns
		totalColumns = driver.findElements(noOfColumns).size();
		if (totalColumns > 0) {
			System.out.println("No of columns in this page : " + totalColumns);
		} else {
			System.out.println("No of columns in this page : " + totalColumns);
		}
	}
	
	public static void printNamesOfColumn() {
		getDriverandHitDatatablesURL();

		totalPages = driver.findElements(noOfPages).size();
		System.out.println("Total Pages : " + totalPages);
		
		//Retrieving The Data of particular column.
		List<WebElement> nameElements = driver.findElements(particularCellValue);
		
		//Adding the data into list.
		for (WebElement nameElement : nameElements) {
			nameList.add(nameElement.getText());
			totalRows++;
		}
		
		//Next button element locator store in the below ref variable.
		String nextButton = driver.findElement(By.id("example_next")).getAttribute("class");		
		
		//Checking the status of next button.
		while(!nextButton.contains("disabled")) {	//False, become True. And True, become False.
			driver.findElement(By.id("example_next")).click();
			nameElements = driver.findElements(particularCellValue);
			
			//Adding the data into list.
			for (WebElement nameElement : nameElements) {
				nameList.add(nameElement.getText());
				totalRows++;
			}
			
			//Again taking the status of next button.
			nextButton = driver.findElement(By.id("example_next")).getAttribute("class");
		}
		
		//Printing all the name data.
		for (String nameValue : nameList) {
			System.out.println("Name : " + nameValue);
		}

		// Finding number of Rows
		if (totalRows > 0) {
			System.out.println("No of rows in this table : " + totalRows);
		} else {
			System.out.println("No of rows in this table : " + totalRows);
		}

		totalNames = nameList.size();
		System.out.println("Total Names Count : " + totalNames);
		
		//Showing 1 to 10 of 57 entries
		displayCounts = driver.findElement(totalEntries).getText().split(" ")[5];
		System.out.println("Total Number Of Entries : " + displayCounts);

		if (displayCounts.equals(String.valueOf(totalNames))) {
			System.out.println("Record Match");
		} else {
			System.out.println("Record Not Match");
		}
		BaseClass.quitDriver();
	}	
}