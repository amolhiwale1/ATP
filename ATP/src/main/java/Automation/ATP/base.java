package Automation.ATP;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class base {

	public WebDriver driver;
	Properties prop;
	String url = "https://ecommerce-playground.lambdatest.io/";

	public WebDriver initialise(String condition) throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//resources//config.properties");
		prop.load(fis);
		switch (condition) {
		case "default":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.get(url);
			
			break;
			
		case "chromeoption":
			WebDriverManager.chromedriver().setup();
            ChromeOptions option = new ChromeOptions();
            option.setExperimentalOption("debuggerAddress", "localhost:55851");
            option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            driver = new ChromeDriver(option);
			break;

		default:
			break;
		}
		return driver;

	}

	public String title() {
		return driver.getTitle();
	}

	public void quite() {
		driver.quit();
	}

}
