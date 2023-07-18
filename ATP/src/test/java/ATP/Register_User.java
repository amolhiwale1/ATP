package ATP;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Automation.ATP.Register_Page;
import Automation.ATP.base;

public class Register_User extends base {

	public WebDriver driver;
	Register_Page register;

	@BeforeClass
	public void setUp(ITestContext context) throws IOException {
		driver = initialise("default");
		context.setAttribute("WebDriver", driver);
		register = new Register_Page(driver);
	}

	@Test(priority = 0, enabled = true, description = "User Registration")
	public void register() {

		// Go to my acccount page
		register.myAccount();
		Assert.assertEquals("Account Login", title());

		// Click on Register link
		register.userRegister();
		Assert.assertEquals("Register Account", title());

		// Fill the form details
		register.userDetails();
		register.email();
		register.telephone();
		register.password();
		register.confirmPassowrd();
		register.privacyPolicy();

		register.continueButton().click();
		register.logout();

	}

	@Test(dependsOnMethods = { "register" }, enabled = true, description = "User Login")
	public void loginUser() throws IOException {

		// Go to my acccount page
		register.myAccount();
		Assert.assertEquals("Account Login", title());
		
		register.login();
		Assert.assertEquals("My Account", title());
	}

	@AfterClass
	public void tearDown() {
		quite();
	}

}
