package Automation.ATP;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Cart {

	WebDriver driver;
	BaseUtilities util;

	public Cart(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		util = new BaseUtilities(driver);
	}

	@FindBy(xpath = "//a[normalize-space()='View Cart']")
	public WebElement viewCart;

	@FindBy(xpath = "//button[@title='Remove']")
	public WebElement removeProduct;

	@FindBy(xpath = "//a[normalize-space()='Continue']")
	public WebElement continueButton;

	@FindBy(xpath = "//h5[normalize-space()='Estimate Shipping & Taxes']")
	public WebElement estimateTaxes;

	@FindBy(xpath = "//select[@id='input-country']")
	public WebElement countries;

	@FindBy(xpath = "//select[@id='input-country']//option")
	public WebElement countrieList;

	public void viewCart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(viewCart));
		cart.click();
	}

	public void removeProduct() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement product = wait.until(ExpectedConditions.elementToBeClickable(removeProduct));
		product.click();
	}

	public void continueButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement cont = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
		cont.click();
	}

	public void estimateTaxes() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement estimate = wait.until(ExpectedConditions.elementToBeClickable(estimateTaxes));
		estimate.click();
	}

	public String checkPriceFor(String priceFor) {
		return driver
				.findElement(By
						.xpath("//td[@class='" + priceFor + "'][normalize-space()='Total:']/parent::tr/td[2]//strong"))
				.getText();
	}

	public int totalCountries() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement countryList = wait.until(ExpectedConditions.elementToBeClickable(countries));
		countryList.click();
		List<WebElement> options = driver.findElements(By.tagName("option"));
		return options.size();
	}
	
	public void selectCountry(int countryNumber) {
		Select select = new Select(countries);
		select.selectByIndex(countryNumber);
	}
	
	

}
