package ATP;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Automation.ATP.Cart;
import Automation.ATP.Login_Page;
import Automation.ATP.Page;
import Automation.ATP.base;

public class Cart_Each_Country extends base {

	public WebDriver driver;
	Cart cart;
	Login_Page login;
	Page page;

	@BeforeClass
	public void setUp(ITestContext context) throws IOException {

		driver = initialise("default");//chromeoption default
		context.setAttribute("WebDriver", driver);
		cart = new Cart(driver);
		login = new Login_Page(driver);
		page = new Page(driver);
	}

	@Test(priority = 0, enabled = true, description = "Checking the price for each country")
	public void addProductToCart() throws IOException, InterruptedException {

		// select product
		page.selectProduct();

		// add to cart
		page.addToCart();

		// view cart
		page.viewCart();

		// estimate shipping and taxes
		cart.estimateTaxes();

		// check for each country
		cart.checkPriceForEachCountry();
	}
}
