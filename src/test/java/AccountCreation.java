import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.bytebuddy.asm.Advice.Enter;
import DataProvider.TestDataProvider;
import Models.UserSignUp;

public class AccountCreation {

	WebDriver driver;
	UserSignUp signUp;
	Utilities utility;

	public AccountCreation() {

		signUp = new UserSignUp();

	}

	@BeforeClass
	public void setup() {
		// Launch Edge Browser
		System.setProperty("Webdriver.Edge.driver", "C:\\Sangeetha\\Driver\\edgedriver_win64\\msedgedriver.exe");
		driver = new EdgeDriver();
		driver.get("https://automationexercise.com/");
		utility = new Utilities(driver);
	}

	@Test
	public void NavigatetoSignup() {
		// Find Signup/Login link
		utility.GetElementByXpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a").click();

		// Validate Login page URL
		String loginurl = utility.GetCurrentUrl();
		String loginkeyword = loginurl.substring(loginurl.lastIndexOf("/") + 1);
		Assert.assertEquals(loginkeyword, "login", "Keyword does not match");
	}

	@Test(dependsOnMethods = "NavigatetoSignup", dataProvider = "signupData", dataProviderClass = TestDataProvider.class)
	public void Signuppage(String name, String email) {
		
		// Enter Email Address
		List<WebElement> forms = utility.GetElementByTag("form");
		
		for (WebElement form :forms) {
			String action =form.getAttribute("action");
			if(action.endsWith("/signup")) {
				utility.GetElementByName(form ,"name").sendKeys(name);		
				signUp.name = name;
				utility.GetElementByName(form, "email").sendKeys(email);	
				signUp.email = email;
			}
			}
		
		// Submit form
	
		utility.GetElementByXpath("//*[@id=\"form\"]/div/div/div[3]/div/form/button").click();
		
		// Validate Signup page URL
		String signupurl = driver.getCurrentUrl();
		String signupkeyword = signupurl.substring(signupurl.lastIndexOf("/") + 1);
		Assert.assertEquals(signupkeyword, "signup", "Sigup URL validation failed");

	}

	@Test(dependsOnMethods = "Signuppage", dataProvider = "account_information", dataProviderClass = TestDataProvider.class)
	public void AccountInformationpage(UserSignUp accountInfo) {
		
		// Select Title
		utility.GetElementById("uniform-id_gender1").click();

		// Validate stored name and email
		utility.ValidateAttributeByElementId("name", "value", signUp.name, "Name does not match");
		utility.ValidateAttributeByElementId("email","value", signUp.email, "Email does not match");
		
		// Enter password
		utility.enterText("password", accountInfo.password);
	    signUp.password = accountInfo.password;

	    // Select DOB
	   utility.selectDropdownValue("days", accountInfo.day);
	   utility.selectDropdownValue("months",accountInfo.month);
	   utility.selectDropdownValue("years",accountInfo.year);

		// Select newsletter
	   utility.GetElementById("newsletter").click();
		
		// Enter personal information
	   utility.enterText("first_name", accountInfo.firstName);
	   utility.enterText("last_name", accountInfo.lastName);
	   utility.enterText("company", accountInfo.company);
	   utility.enterText("address1", accountInfo.address1);
	   utility.enterText("address2", accountInfo.address2);
	   utility.selectDropdownValue("country", accountInfo.country);
	   utility.enterText("state", accountInfo.state);
	   utility.enterText("city", accountInfo.city);
	   utility.enterText("zipcode", accountInfo.zipcode);
	   utility.enterText("mobile_number", accountInfo.mobilenumber);

	    // Click Create Account
	   utility.GetElementByXpath("//*[@id=\"form\"]/div/div/div/div[1]/form/button").click();
	   
	   String accountcreatedurl = utility.GetCurrentUrl();
		String createdkeyword = accountcreatedurl.substring(accountcreatedurl.lastIndexOf("/") + 1);
		Assert.assertEquals(createdkeyword, "account_created", "account_created Keyword does not match");
	   	    
	}

}
