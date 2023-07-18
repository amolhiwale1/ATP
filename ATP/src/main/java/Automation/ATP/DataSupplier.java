package Automation.ATP;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

public class DataSupplier {

	WebDriver driver;
	Page page;

	public DataSupplier(WebDriver driver) {
		this.driver = driver;
		page = new Page(driver);
	}

	// String email = page.getSaltString();

	@DataProvider
	public String[][] formData() {
		return new String[][] { { "Amol", "Hiwale", page.getSaltString(), "9876543210", "nuvolo123", "nuvolo123",
				"Nuvolo", "Pune, India", "Pune", "12345", "India", "Kerala" } };

	}
}
