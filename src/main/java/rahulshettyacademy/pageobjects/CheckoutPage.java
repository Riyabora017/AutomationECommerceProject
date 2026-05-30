package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".action__submit")
	WebElement submit;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;

	By results = By.cssSelector(".ta-results");
//
//	public void selectCountry(String countryName) {
//		Actions a = new Actions(driver);
//		a.sendKeys(country, countryName).build().perform();
//		waitForElementToAppear(By.cssSelector(".ta-results"));
//		selectCountry.click();
//	}
	public void selectCountry(String countryName) {

	    Actions a = new Actions(driver);
	    a.sendKeys(country, countryName).build().perform();

	    // Wait for suggestion items instead of container
	    waitForElementToAppear(By.cssSelector(".ta-item"));

	    List<WebElement> countries =
	            driver.findElements(By.cssSelector(".ta-item"));

	    for (WebElement option : countries) {

	        if (option.getText().equalsIgnoreCase(countryName)) {
	            option.click();
	            break;
	        }
	    }
	}
	public ConfirmationPage submitOrder() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView(true);", submit);

		js.executeScript("arguments[0].click();", submit);

		return new ConfirmationPage(driver);
	}

}