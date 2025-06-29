

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.time.temporal.TemporalAmount;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import DataProvider.SampleDataProvider;
import Models.UserSignUp;
import Utilities.*;

public class NewOrderPlaceTest {
	WebDriver driver;
	UserSignUp signup;
	WebDriverUtility utility;

	public NewOrderPlaceTest() {
		signup = new UserSignUp();

	}

	@BeforeClass
	public void set() {
		System.setProperty("Webdriver.Edge.driver", "C:\\Sangeetha\\Driver\\edgedriver_win64\\msedgedriver.exe");
		driver = new EdgeDriver();
		driver.get("https://automationexercise.com/");
		utility = new WebDriverUtility(driver);
	}

	@Test
	public void Loinpage() {
		// Find Signup/Login Link
		utility.GetElementByXpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a").click();

		// Validate Login Page URL
		String loginurl = utility.GetCurrentUrl();
		String loginkeyword = loginurl.substring(loginurl.lastIndexOf("/") + 1);
		Assert.assertEquals(loginkeyword, "login", "Keyword does not match");

	}

	@Test(dependsOnMethods = { "Loinpage" }, dataProvider = "Logindata", dataProviderClass = SampleDataProvider.class)
	public void LogintoAccount(String email, String password) {

		List<WebElement> forms = utility.GetElementByTag("form");

		for (WebElement form : forms) {
			String action = form.getAttribute("action");
			if (action.endsWith("/login")) {
				utility.GetElementByName(form, "email").sendKeys(email);
				signup.email = email;
				utility.GetElementByName(form, "password").sendKeys(password);
				signup.password = password;

				// Click Submit
				List<WebElement> submit = utility.GetElementByTag(form, "button");
				Assert.assertEquals(submit.size(), 1);
				submit.get(0).click();
				break;
			}
		}

		// Validate Home Page

		WebElement homepage = utility.GetElementByXpath("//*[@id=\'header\']/div/div/div/div[2]/div/ul/li[4]/a");
		String text = homepage.getText();
		System.out.println(text);
		Assert.assertEquals(text, "Logout", "Home page is not displayed");
	}

	@Test(dependsOnMethods = {
			"LogintoAccount" }, dataProvider = "ItemName", dataProviderClass = SampleDataProvider.class)
	public void AddtoCart(String productName, String quantity) {

		List<WebElement> item = utility.GetElementbycssSelector(".product-image-wrapper");

		for (WebElement product : item) {
			List<WebElement> product_text = utility.GetElementByTag(product, "p");
			if (product_text.get(0).getText().equals(productName)) {
				List<WebElement> viewProduct = product.findElements(By.className("choose"));
				if (!viewProduct.isEmpty()) {
					WebElement firstProduct = viewProduct.get(0);
					List<WebElement> anchorWebElements = firstProduct.findElements(By.tagName("a"));
					Assert.assertEquals(anchorWebElements.size(), 1);
					WebElement anchor = anchorWebElements.get(0);
					if (anchor.getAttribute("href") != null) {
						anchor.click();
					}
				}

				WebElement verifyProduct = driver.findElement(By.xpath("//*[@class='product-information']/h2"));
				if (productName.equals(verifyProduct.getText())) {
					WebElement quantityInput = utility.GetElementById("quantity");
					quantityInput.clear();
					quantityInput.sendKeys(String.valueOf(quantity));
					driver.findElement(By.xpath("//*[@class='product-information']//following::span/button")).click();
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
					WebElement addedmessage = wait
							.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartModal")));
					String message = addedmessage.findElement(By.tagName("p")).getText();
					Assert.assertEquals(message, "Your product has been added to cart.");
					var cart_button = addedmessage.findElement(By.tagName("button"));
					cart_button.click();
					driver.navigate().back();
					break;

				}

			}

		}
	}
	

	@Test(dependsOnMethods = {"AddtoCart"}, dataProvider = "ItemName", dataProviderClass = SampleDataProvider.class)
	public void CartValidation(String productName, String quantity) {

		// CLick on Cart
		utility.GetElementByXpath("//*[@id=\'header\']/div/div/div/div[2]/div/ul/li[3]").click();

		// Validate Items in Cart
		WebElement items_description = utility.GetElementById("cart_info");
		List<WebElement> addedProducts = utility.GetElementByTag(items_description, "tr");
		for (WebElement row : addedProducts) {
		List<WebElement> cartDescriptionElement = row.findElements(By.className("cart_description"));
		if(!cartDescriptionElement.isEmpty()) {
			WebElement cartDescription =  cartDescriptionElement.get(0);
			List<WebElement> anchorCartElements = cartDescription.findElements(By.tagName("a"));
			Assert.assertEquals(anchorCartElements.size(), 1);
			WebElement anchorText = anchorCartElements.get(0);
		    String cartText = anchorText.getText();
			if(productName.equals(cartText)) {
				Assert.assertTrue(true);				
				return;
			}
		}
		}
		Assert.fail();
		
	}
		
	
		@Test(dependsOnMethods = {"CartValidation"} )
		
		public void CartCheckout() {
			
		// Click Proceed to Checkout
		WebElement checkout = utility.GetElementById("cart_items");
		var ProceedtoCheckout = checkout.findElement(By.id("do_action"));
		var button = ProceedtoCheckout.findElement(By.tagName("a"));
		button.click();

		// Click PlaceOrder
		var placeOrder = utility.GetElementByXpath("//*[@id=\"cart_items\"]/div/div[7]/a");
		placeOrder.click();
	}

	@Test(dependsOnMethods = { "CartCheckout" }, dataProvider = "paymentinfo", dataProviderClass = SampleDataProvider.class)
	public void payment(UserSignUp paymentdetials) {

		utility.enterTextbyName("payment-form", "name_on_card", paymentdetials.nameOnCard);
		utility.enterTextbyName("payment-form", "card_number", paymentdetials.cardNumber);
		utility.enterTextbyName("payment-form", "cvc", paymentdetials.cvc);
		utility.enterTextbyName("payment-form", "expiry_month", paymentdetials.expiryMonth);
		utility.enterTextbyName("payment-form", "expiry_year", paymentdetials.expiryYear);

		utility.GetElementById("submit").click();

		// Validate Order Placed Message

		WebElement confirmationMessage = driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! Your order has been confirmed!')]"));

		// Validate the text content
		String expectedMessage = "Congratulations! Your order has been confirmed!";
		String actualMessage = confirmationMessage.getText();

		Assert.assertEquals(actualMessage, expectedMessage, "Order confirmation message mismatch!");

		System.out.println(actualMessage);

	}
}
