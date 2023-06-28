package Automation.ATP;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseUtilities {

	WebDriver driver;

	public BaseUtilities(WebDriver driver) {
		this.driver = driver;
	}
	
	public void waitForElementToBeClickable(By locator, int timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Exception e) {
			System.out.println("Element not Clickable");
		}
	}

	public void waitForVisibility(WebElement element, int timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) { // ElementNotVisibleExpection
			System.out.println("Element not visible");
		}
	}
	
	public void scrollToElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", element);

	}
	
	public void pause() throws InterruptedException {
		Thread.sleep(4);
	}

}
