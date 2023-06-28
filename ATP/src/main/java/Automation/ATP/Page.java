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

	public Page(WebDriver driver) {
		this.driver = driver;
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
	
	public String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

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
		Select select = new Select(state);
		select.selectByVisibleText(stateName);
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

	public void fillTheForm() {
		//First name and Last name
		firstName.sendKeys("Amol");
		lastName.sendKeys("Hiwale");

		//Email and Telephone
		email.sendKeys(getSaltString()+"@nuvolo.com");
		telePhone.sendKeys("987654543210");
		
		//Password
		password.sendKeys("Nuvolo123");
		confirmPassword.sendKeys("Nuvolo123");
		
		//Billing Address
		company.sendKeys("Nuvolo Technologies");
		address1.sendKeys("Pune, Maharashtra, India");
		city.sendKeys("Pune");
		postCode.sendKeys("411045");
		
		//Country and State
		selectCountry("India");
		selectState("Maharashtra");
		
		//Selecting privacy policy and terms condition
		privacyPolicy.click();
		termsCondition();
		
		//Saving the fprm
		saveButton();
	}

}
