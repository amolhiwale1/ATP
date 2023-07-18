package Automation.ATP;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

	WebDriver driver;
	BaseUtilities util;

	public Page(WebDriver driver) {
		this.driver = driver;
		util = new BaseUtilities(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[normalize-space()='Home']")
	public WebElement home;

	@FindBy(xpath = "//img[@alt='Iphone 11 pro max']")
	public WebElement product;

	@FindBy(xpath = "(//button[@title='Add to Cart'][normalize-space()='Add to Cart'])[2]")
	public WebElement addToCart;

	@FindBy(xpath = "//a[normalize-space()='View Cart']")
	public WebElement viewCart;
	
	@FindBy(xpath = "//a[@class='btn btn-secondary btn-block']")
	public WebElement checkout;

	@FindBy(css = "#input-payment-firstname")
	public WebElement firstName;

	@FindBy(xpath = "//input[@id='input-payment-lastname']")
	public WebElement lastName;

	@FindBy(xpath = "//input[@id='input-payment-email']")
	public WebElement email;

	@FindBy(xpath = "//input[@id='input-payment-telephone']")
	public WebElement telePhone;

	@FindBy(xpath = "//input[@id='input-payment-password']")
	public WebElement password;

	@FindBy(xpath = "//input[@id='input-payment-confirm']")
	public WebElement confirmPassword;

	@FindBy(xpath = "//input[@id='input-payment-company']")
	public WebElement company;

	@FindBy(xpath = "//input[@id='input-payment-address-1']")
	public WebElement address1;

	@FindBy(xpath = "//input[@id='input-payment-city']")
	public WebElement city;
	
	@FindBy(xpath = "//input[@id='input-payment-postcode']")
	public WebElement postCode;

	@FindBy(xpath = "//select[@id='input-payment-country']")
	public WebElement country;

	@FindBy(xpath = "//select[@id='input-payment-zone']")
	public WebElement state;

	@FindBy(xpath = "//label[@for='input-account-agree']")
	public WebElement privacyPolicy;

	@FindBy(xpath = "//label[@for='input-agree']")
	public WebElement termsConditions;

	@FindBy(xpath = "//button[@id='button-save']")
	public WebElement save;
	
	@FindBy(xpath = "//button[@id='button-confirm']")
	public WebElement confirmOrder;
	
	@FindBy(xpath = "//h5[normalize-space()='Use Gift Certificate']")
	public WebElement giftCertificate;
	
	public String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr+"@nuvolo.com";

	}
	
	public void home() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement Home = wait.until(ExpectedConditions.elementToBeClickable(home));
		Home.click();
	}
	
	public void selectProduct() {
		product.click();
	}
	

	public void addToCart() {
		addToCart.click();
	}
	
	public void viewCart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement vCart = wait.until(ExpectedConditions.elementToBeClickable(viewCart));
		vCart.click();
	}

	public void checkout() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement cout = wait.until(ExpectedConditions.elementToBeClickable(checkout));
		cout.click();
	}
	
	public void selectCountry(String countryName) {
		
		Select select = new Select(country);
		select.selectByVisibleText(countryName);
		
	}
	
	public void selectState(String stateName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement stateOption = wait.until(ExpectedConditions.visibilityOf(state));
		Select select = new Select(stateOption);
		select.selectByIndex(Integer.parseInt(stateName));
	}
	
	public void confirmOrder() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement confirmOrderButton = wait.until(ExpectedConditions.elementToBeClickable(confirmOrder));
		confirmOrderButton.click();
	}
	
	public void termsCondition() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement termsConditionsButton = wait.until(ExpectedConditions.elementToBeClickable(termsConditions));
		termsConditionsButton.click();
	}
	
	public void saveButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement termsConditionsButton = wait.until(ExpectedConditions.elementToBeClickable(save));
		termsConditionsButton.click();

	}
	
	public void firstName(String name) {
		firstName.sendKeys(name);
	}
	
	public void lastName(String name) {
		lastName.sendKeys(name);
	}
	
	public void generateEmail(String eMail) {
		 email.sendKeys(eMail);
	}
	
	public void telePhone(String number) {
		telePhone.sendKeys(number);
	}

	public void enterPassword(String passwords) {
		password.sendKeys(passwords);
		
	}
	
	public void enterConfirmPassword(String passwords) {
		confirmPassword.sendKeys(passwords);
	}
	
	public void companyName(String companyName) {
		company.sendKeys(companyName);
	}
	
	public void address(String address) {
		address1.sendKeys(address);//"Pune, Maharashtra, India"
	}
	
	public void cityName(String cityName) {
		city.sendKeys(cityName);
	}
	
	public void postCode(String code) {
		postCode.sendKeys(code);
	}
	
	public void clickPrivacyPolicy() {
		privacyPolicy.click();
	}
	
	public void giftCertificate() {
		util.scrollToElement(giftCertificate);
	}

}
