package Automation.ATP;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Cart {

	WebDriver driver;
	BaseUtilities util;
	Select select;

	public Cart(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		util = new BaseUtilities(driver);

	}

	@FindBy(xpath = "//a[normalize-space()='View Cart']")
	public WebElement viewCart;

	@FindBy(xpath = "//button[@title='Remove']")
	public WebElement removeProduct;

	@FindBy(xpath = "//a[normalize-space()='Continue']")
	public WebElement continueButton;

	@FindBy(xpath = "//h5[normalize-space()='Estimate Shipping & Taxes']")
	public WebElement estimateTaxes;

	@FindBy(xpath = "//select[@id='input-country']")
	public WebElement countries;

	@FindBy(xpath = "//select[@id='input-zone']")
	public WebElement state;

	@FindBy(xpath = "//select[@id='input-country']//option")
	public WebElement countrieList;

	@FindBy(xpath = "//input[@id='input-postcode']")
	public WebElement postCode;

	@FindBy(xpath = "//button[@id='button-quote']")
	public WebElement getQuote;

	@FindBy(xpath = "//input[@name='shipping_method']")
	public WebElement shippingMethod;

	@FindBy(xpath = "//button[@id='button-shipping']")
	public WebElement applyShipping;

	@FindBy(xpath = "//td[@class='text-right'][normalize-space()='Total:']/parent::tr/td[2]//strong")
	public WebElement total;

	@FindBy(xpath = "//a[@class='btn btn-lg btn-primary']")
	public WebElement checkout;

	@FindBy(xpath = "//td[@class='text-right'][normalize-space()='Flat Shipping Rate:']/parent::tr/td[2]//strong")
	public WebElement flatShippingRate;

	public void viewCart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(viewCart));
		cart.click();
	}

	public void removeProduct() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement product = wait.until(ExpectedConditions.elementToBeClickable(removeProduct));
		product.click();
	}

	public void continueButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement cont = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
		cont.click();
	}

	public void estimateTaxes() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement estimate = wait.until(ExpectedConditions.elementToBeClickable(estimateTaxes));
		estimate.click();
	}

	public void postCode() {
		postCode.sendKeys("1234");
	}

	public void getQuote() {
		getQuote.click();
	}

	public void applyShipping() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement shipping = wait.until(ExpectedConditions.elementToBeClickable(shippingMethod));
		shipping.click();
		applyShipping.click();
	}

	public double checkPriceFor(String priceFor) {
		String price = driver
				.findElement(By.xpath(
						"//td[@class='text-right'][normalize-space()='" + priceFor + "']/parent::tr/td[2]//strong"))
				.getText().substring(1);
		return Double.parseDouble(price);
	}

	public boolean checkVisibilityFor(String priceFor) {
		try {
			util.scrollToElement(estimateTaxes);
		} catch (Exception StaleElementReferenceException) {
			util.scrollToElement(checkout);
		}
		
		try {
			driver.findElement(By
					.xpath("//td[@class='text-right'][normalize-space()='" + priceFor + "']/parent::tr/td[2]//strong"))
					.isDisplayed();
			return true;
		} catch (Exception e) {
			System.out.println(priceFor + ": is not present");
			return false;
		}

	}

	public int totalCountries() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement countryList = wait.until(ExpectedConditions.elementToBeClickable(countries));
		countryList.click();
		List<WebElement> options = driver.findElements(By.xpath("//select[@id='input-country']//option"));
		return options.size();
	}

	public void selectCountry(int countryNumber) {
		util.scrollToElement(countries);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(countries));
		select = new Select(countries);
		select.selectByIndex(countryNumber);
		//return select.getFirstSelectedOption().getText().toString();
	}

	public void selectState(int stateNumber) {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			select = new Select(state);
			select.selectByIndex(stateNumber);
		} catch (Exception StaleElementReferenceException) {
			try {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				select = new Select(state);
				select.selectByIndex(stateNumber);
			} catch (Exception NoSuchElementException) {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				select = new Select(state);
				select.selectByIndex(stateNumber - 1);
			}
		}

	}

	public double subTotal() {
		util.scrollToElement(total);
		util.waitForVisibility(total, 5);
		if (checkVisibilityFor("Sub-Total:") == true) {
			System.out.println("The Sub Total price is:" + checkPriceFor("Sub-Total:"));
			return checkPriceFor("Sub-Total:");
		} else {
			return 0;
		}
	}

	public double flatShippingRate() {
		if (checkVisibilityFor("Flat Shipping Rate:") == true) {
			return checkPriceFor("Flat Shipping Rate:");
		} else {
			return 0;
		}
	}

	public double ecoTax() {
		if (checkVisibilityFor("Eco Tax (-2.00):") == true) {
			return checkPriceFor("Eco Tax (-2.00):");
		} else {
			return 0;
		}
	}

	public double vat() {
		if (checkVisibilityFor("VAT (20%):") == true) {
			return checkPriceFor("VAT (20%):");
		} else {
			return 0;
		}
	}

	public double totalPrice(String country) {
		if (ecoTax() != 0 || vat() != 0) {
			if (ecoTax() == 0 && vat() != 0) {
				if (ecoTax() != 0 && vat() == 0) {
					System.out.println("For the country " + country + " VAT is 0");

				} else {
					System.out.println("For the country " + country + " Eco Tax is 0");
				}

			} else {
				System.out.println("For the country " + country + " VAT and Eco Tax are 0");
			}

		}
		System.out.println("***************************************");
		double subTotal = subTotal();
		double flatShippingRate = flatShippingRate();
		double ecoTax = ecoTax();
		double vat = vat();
		double totalPrice = subTotal + flatShippingRate + ecoTax + vat;
		System.out.println("The total price for the product is: " + totalPrice + " and The country is "+country);
		System.out.println("***********************************************");
		System.out.println("*        "+country+" - "+totalPrice+"        *");
		System.out.println("***********************************************");
		return totalPrice;

	}

	public double finalPrice() {
		double subTotal = subTotal();
		double flatShippingRate = flatShippingRate();
		System.out.println("flat shipping rate: " + flatShippingRate);
		double ecoTax = ecoTax();
		System.out.println("eco tax is: " + ecoTax);
		double vat = vat();
		System.out.println("vat is: " + vat);
		double totalPrice = subTotal + flatShippingRate + ecoTax + vat;
		System.out.println("The total price for the product is: " + totalPrice);
		return totalPrice;

	}

	public List<WebElement> eachCountry() throws InterruptedException {
		util.scrollToElement(total);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement countryList = wait.until(ExpectedConditions.elementToBeClickable(countries));
		countryList.click();
		List<WebElement> options = driver.findElements(By.xpath("//select[@id='input-country']//option"));
		options.toArray();
		return options;

	}

	public String getCountry() {
		select = new Select(countries);
		return select.getFirstSelectedOption().getText().toString();
	}

	public void checkPriceForEachCountry() {
		for (int i = 1; i < totalCountries() - 1; i++) {
			selectCountry(i);
			String country = getCountry();
			selectState(2);
			postCode();

			getQuote();

			applyShipping();
			totalPrice(country);
			estimateTaxes();
		}

	}

}
