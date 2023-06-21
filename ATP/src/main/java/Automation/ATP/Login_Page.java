package Automation.ATP;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_Page {
	

	WebDriver driver;

	public Login_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@class='btn btn-secondary btn-block']")
	public WebElement checkout;

	@FindBy(css = "#input-payment-firstname")
	public WebElement firstName;
	
	public void loginUser() {
		
	}
	
}
