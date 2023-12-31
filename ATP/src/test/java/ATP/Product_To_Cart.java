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

public class Product_To_Cart extends base {

	public WebDriver driver;
	Cart cart;
	Login_Page login;
	Page page;

	@BeforeClass
	public void setUp(ITestContext context) throws IOException {

		driver = initialise("default");
		context.setAttribute("WebDriver", driver);
		cart = new Cart(driver);
		login = new Login_Page(driver);
		page = new Page(driver);
	}

	@Test(priority = 0, enabled = true, description = "Adding the product to the cart")
	public void addProductToCart() throws IOException, InterruptedException{
		
		//login
		login.loginUser();
		
		//select product
		page.home();
		page.selectProduct();
		
		//add to cart
		page.addToCart();
		
		//view cart
		page.viewCart();
		
		//estimate shipping and taxes
		cart.estimateTaxes();
		
		//select country
		cart.selectCountry(3);
		
		//pin code
		cart.postCode();
		
		//select state
		cart.selectState(5);
		
		//get quotes
		cart.getQuote();
		
		//apply shipping
		cart.applyShipping();
		
		//check prices
		cart.finalPrice();
		
		

	}

}
