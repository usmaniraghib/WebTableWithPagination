package com.raghib.selenium.table.pagination;

//NOTE :- Try to type one import statement then only import reference will be provided by eclipse.

/*
* REFERENCE:-
* https://www.youtube.com/watch?v=p3q_Wy4AjHs
* https://www.youtube.com/watch?v=jD5eWhNsaRk&t=27s
*/

import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.NameList;

public class PaginationWebTableExampleUsingSelenium {
	
	public static void main(String args[]) {
		countRowsandColumnsInPage();
		printNamesOfColumn();
	}

	static WebDriver driver;
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
		driver = BrowserSelection.usingChrome();

		driver.get("https://datatables.net/examples/advanced_init/dt_events.html");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(100,TimeUnit.SECONDS);
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
		//driver.quit();
	}
	
	public static void printNamesOfColumn() {
		getDriverandHitDatatablesURL();

		totalPages = driver.findElements(noOfPages).size();
		System.out.println("Total Pages : " + totalPages);

		for (int i = 1; i <= totalPages; i++) {
			By eachPageButtonClick = By.xpath("//*[@id='example_paginate']/span/a[" + i + "]");
			driver.findElement(eachPageButtonClick).click();

			List<WebElement> nameElements = driver.findElements(particularCellValue);

			for (WebElement nameElement : nameElements) {
				nameList.add(nameElement.getText());
				totalRows++;
			}
		}

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

		displayCounts = driver.findElement(totalEntries).getText().split(" ")[5];
		System.out.println("Total Number Of Entries : " + displayCounts);

		if (displayCounts.equals(String.valueOf(totalNames))) {
			System.out.println("Record Match");
		} else {
			System.out.println("Record Not Match");
		}
		driver.quit();
	}
	
}
