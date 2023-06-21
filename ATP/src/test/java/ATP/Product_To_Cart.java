package ATP;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Automation.ATP.Cart;
import Automation.ATP.base;

public class Product_To_Cart extends base {

	public WebDriver driver;
	Cart cart;

	@BeforeClass
	public void setUp(ITestContext context) throws IOException {

		driver = initialise();
		context.setAttribute("WebDriver", driver);
		cart = new Cart(driver);
	}

	@Test(priority = 0, enabled = true)
	public void addProductToCart() {
		
		//login
		
		//select product
		
		//

	}

}
