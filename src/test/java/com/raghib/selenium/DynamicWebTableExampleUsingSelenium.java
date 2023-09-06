package com.raghib.selenium;

import java.time.Duration;

//NOTE :- Try to type one import statement then only import reference will be provided by eclipse.

/*
* REFERENCE:-
* https://www.youtube.com/watch?v=1Sy7Wdx_NgA&list=PLYIXQpA4Ir9mJcizhqLt6WjcjOpTQUK0K&index=3&t=60s
*/

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DynamicWebTableExampleUsingSelenium extends BaseClass {
	
	public static void main(String args[]) {
		countRowsandColumns();
		printAllTableData();
		printDependingTableData();
	}

	static WebDriver driver;
	static String browserName = "chrome";
	static String browserVersion = "116";
	static String url = "https://datatables.net/examples/basic_init/scroll_y.html";
	static int totalRows = 0;
	static int totalColumns = 0;

	static By noOfColumns = By.xpath("//*[@id='example']//thead/tr/th");
	static By noOfRows = By.xpath("//*[@id='example']/tbody/tr");

	static By particularCellValue = By.xpath("//table[@id='example']/tbody/tr[20]/td[1]");

	public static void getDriverandHitURL() {
		// Chrome Browser
		driver = BaseClass.getDriver(browserName, browserVersion);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		driver.get(url);
	}

	public static void countRowsandColumns() {
		getDriverandHitURL();
		
		// Finding number of Rows
		totalRows = driver.findElements(noOfRows).size();
		if (totalRows > 0) {
			System.out.println("No of rows in this table : " + totalRows);
		} else {
			System.out.println("No of rows in this table : " + totalRows);
		}

		// Finding number of Columns
		totalColumns = driver.findElements(noOfColumns).size();
		if (totalColumns > 0) {
			System.out.println("No of columns in this table : " + totalColumns);
		} else {
			System.out.println("No of columns in this table : " + totalColumns);
		}
		//BaseClass.quitDriver();
	}
	
	public static void printAllTableData() {
		//getDriverandHitURL();
		
		int rows = driver.findElements(noOfRows).size();
		System.out.println("No Of Rows : "+rows);
		int columns = driver.findElements(noOfColumns).size();
		System.out.println("No Of Columns : "+columns);

		// Retrieve all the data of the table - Dynamic Web Table
		System.out.println("All The Data From Table!");
		for (int rowIndex = 1; rowIndex <= rows; rowIndex++) {
			for (int colIndex = 1; colIndex <= columns; colIndex++) {
				String allData = driver
						.findElement(
								By.xpath("//table[@id='example']/tbody/tr[" + rowIndex + "]/td[" + colIndex + "]"))
						.getText();
				System.out.print(allData + "     |     ");
			}
			System.out.println();
		}
		//BaseClass.quitDriver();
	}
	
	public static void printDependingTableData() {
		//getDriverandHitURL();
		
		int rows = driver.findElements(noOfRows).size();
		System.out.println("No Of Rows : "+rows);

		// Finding the cell value at specific row and specific column
		WebElement cellAddress = driver.findElement(particularCellValue);
		String value = cellAddress.getText();
		System.out.println("The Cell Value is : " + value);

		// Print Age and Salary of Gavin Joyce name - Dynamic Web Table
		System.out.println("The Age and Salary of this person " + value + " is ");
		for (int rowIndex = 1; rowIndex <= rows; rowIndex++) {
			String nameData = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + rowIndex + "]/td[1]"))
					.getText();
			if (nameData.equalsIgnoreCase(value)) {
				String ageData = driver
						.findElement(By.xpath("//table[@id='example']/tbody/tr[" + rowIndex + "]/td[4]")).getText();
				String salaryData = driver
						.findElement(By.xpath("//table[@id='example']/tbody/tr[" + rowIndex + "]/td[6]")).getText();
				System.out.println(nameData + " | " + ageData + " | " + salaryData);
			}
		}
		//BaseClass.quitDriver();
	}
}