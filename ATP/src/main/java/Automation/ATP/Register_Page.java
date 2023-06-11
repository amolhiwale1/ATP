package Automation.ATP;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Register_Page {

	WebDriver driver;
	Page page;
	Properties prop;
	String passwords = "nuvolo123";

	public Register_Page(WebDriver driver) {
		this.driver = driver;
		page = new Page(driver);
		prop = new Properties();
		PageFactory.initElements(driver, this);
	}
	
	

	@FindBy(xpath = "//a[@role='button']//span[@class='title'][normalize-space()='My account']")
	public WebElement myAccount;

	@FindBy(xpath = "//a[contains(text(),'Register')]")
	public WebElement register;

	@FindBy(css = "#input-firstname")
	public WebElement firstName;

	@FindBy(css = "#input-lastname")
	public WebElement lastName;

	@FindBy(css = "#input-email")
	public WebElement email;

	@FindBy(css = "#input-telephone")
	public WebElement telephone;

	@FindBy(css = "#input-password")
	public WebElement password;

	@FindBy(css = "#input-confirm")
	public WebElement confirmPassowrd;

	@FindBy(css = "label[for='input-agree']")
	public WebElement privacyPolicy;

	@FindBy(xpath = "//input[@value='Continue']")
	public WebElement continueButton;

	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	public WebElement logout;

	@FindBy(css = "input[value='Login']")
	public WebElement login;
	
	

	public void myAccount() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement myAccountlink = wait.until(ExpectedConditions.elementToBeClickable(myAccount));
		myAccountlink.click();

	}

	public void userRegister() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(register));
		registerButton.click();
	}

	public WebElement firstName() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.visibilityOf(firstName));

	}

	public WebElement continueButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.elementToBeClickable(continueButton));

	}

	public void userDetails() {
		firstName().sendKeys("Amol");
		lastName.sendKeys("Hiwale");
	}

	public String emailID() throws IOException {
		FileOutputStream out = new FileOutputStream(System.getProperty("user.dir")+"//src//main//resources//config.properties");

		String mailID = page.getSaltString() + "@nuvolo.com";
		prop.setProperty("email", mailID);
		prop.store(out, null);
		return mailID;
	}

	public void email() {
		try {
			email.sendKeys(emailID());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void telephone() {
		telephone.sendKeys("9876543210");
	}

	public void password() {
		password.sendKeys(passwords);
	}

	public void confirmPassowrd() {
		confirmPassowrd.sendKeys(passwords);
	}

	public void privacyPolicy() {
		privacyPolicy.click();
	}

	public void logout() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		WebElement logOut = wait.until(ExpectedConditions.elementToBeClickable(logout));
		logOut.click();
	}

	public void login() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//config.properties");
		prop.load(fis);
		email.sendKeys(prop.getProperty("email"));
		password.sendKeys(passwords);
		login.click();
	}

}
