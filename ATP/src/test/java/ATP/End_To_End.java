package ATP;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Automation.ATP.DataSupplier;
import Automation.ATP.Page;
import Automation.ATP.base;

public class End_To_End extends base {

	public WebDriver driver;
	Page page;
//	DataSupplier data;

	@BeforeClass
	public void setUp(ITestContext context) throws IOException {

		driver = initialise("default");
		context.setAttribute("WebDriver", driver);
		page = new Page(driver);
//		data = new DataSupplier(driver);

	}

	@DataProvider
	public String[][] formData() {
		return new String[][] { { "Amol", "Hiwale", page.getSaltString(), "9876543210", "nuvolo123", "nuvolo123",
				"Nuvolo", "Pune, India", "Pune", "12345", "India", "3" } };

	}

	@Test(priority = 0, enabled = true, description = "The end to end flow from regitering the user to checkout the product", dataProvider = "formData")
	public void addProduct(String name, String lastName, String email, String phone, String password,
			String confirmPassword, String company, String address, String city, String postCode, String country,
			String state) throws InterruptedException {

		page.selectProduct();
		Assert.assertEquals("iPhone", title());

		page.addToCart();
		page.checkout();
		Assert.assertEquals("Checkout", title());

		// filling the form
		page.firstName(name);

		Assert.assertTrue(page.lastName.isDisplayed());
		page.lastName(lastName);

		page.generateEmail(email);

		page.telePhone(phone);

		page.enterPassword(password);

		page.enterConfirmPassword(confirmPassword);

		page.companyName(company);

		page.address(address);

		page.cityName(city);

		page.postCode(postCode);

		page.selectCountry(country);

		page.selectState(state);

		Assert.assertTrue(page.privacyPolicy.isDisplayed());
		page.clickPrivacyPolicy();
		
		Assert.assertTrue(page.termsConditions.isDisplayed());
		page.termsCondition();

		Assert.assertTrue(page.save.isDisplayed());
		page.saveButton();

		page.confirmOrder();
		Assert.assertEquals("Your order has been placed!", title());

	}

	@AfterClass
	public void tearDown() {
//		quite();
	}

}
