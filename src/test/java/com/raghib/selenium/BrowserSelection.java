package com.raghib.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserSelection {

	static WebDriver driver;

	public static WebDriver usingChrome() {
		// Above Selenium Version 4.6+ means 4.9.1
		/*
		 * How To Remove Chrome Warning In Selenium WebDriver- Fix Timed out receiving
		 * message from renderer. java.net.socketexception connection reset
		 */
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Driver\\chromedriver.exe");
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		return driver;
		
//		WebDriverManager.chromedriver().setup();
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--remote-allow-origins=*");
//		options.addArguments("--disable-notifications");
//		DesiredCapabilities dc = new DesiredCapabilities();
//		dc.setCapability(ChromeOptions.CAPABILITY, options);
//		options.merge(dc);
//		WebDriver driver = new ChromeDriver(options);
//		driver.manage().window().maximize();
//		return driver;
		
//		WebDriverManager.chromedriver().setup();
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--remote-allow-origins=*");
//		options.addArguments("--disable-notifications");
//		WebDriver driver = new ChromeDriver(options);
//		driver.manage().window().maximize();
//		return driver;
		
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--remote-allow-origins=*");
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver(options);
//		driver.manage().window().maximize();
//		return driver;		
	}
}
