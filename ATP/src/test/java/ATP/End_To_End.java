package ATP;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Automation.ATP.Page;
import Automation.ATP.base;


public class End_To_End extends base {
	
	public WebDriver driver;
	Page page;
	
	@BeforeClass
	public void setUp(ITestContext context) throws IOException {

		driver = initialise("default");
		context.setAttribute("WebDriver", driver);
		page = new Page(driver);
		

	}
	
	@Test(priority = 0, enabled = true, description = "The end to end flow from regitering the user to checkout the product")
	public void addProduct() throws InterruptedException {
		
		page.selectProduct();
		Assert.assertEquals("iPhone", title());
		
		page.addToCart();
		page.checkout();
		Assert.assertEquals("Checkout", title());
		
		page.fillTheForm();
		
		page.confirmOrder();
		Assert.assertEquals("Your order has been placed!", title());
		
	}
	
	@AfterClass
	public void tearDown() {
		quite();
	}

}
