package DataProvider;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Models.*;

public class SampleDataProvider {

	@DataProvider(name = "signupData")
	public static Object[][] getUserData() {
		return
		new Object[][] { { "Nathan", String.format("nathan%d@example.com", new Random().nextInt(1000))},
		//new Object[][] { { "Sarah", "Sarah@example.com" }
				// {"user2", "user2@example.com"},
				// {"user3", "user3@example.com"}
		//
		};
	}

	@DataProvider(name = "account_information")
	public Object[][] provideAccountData() {

		UserSignUp data = new UserSignUp();
		data.password = "sarah123#";
		data.day = "12";
		data.month = "January";
		data.year = "1991";
		data.firstName = "Sarah";
		data.lastName = "Grace";
		data.company = "HCL";
		data.address1 = "2,Zion Nagar";
		data.address2 = "Rangampalayam";
		data.country = "India";
		data.state = "Tamilnadu";
		data.city = "Erode";
		data.zipcode = "638009";
		data.mobilenumber = "9150146345";
		return new Object[][] { { data } };
	}

	@DataProvider(name = "Logindata")
	public static Object[][] LoginDetails() {
		return new Object[][] { { "Sarah@example.com", "sarah123#" },
				// {"user2", "user2@example.com"},
				// {"user3", "user3@example.com"}
		};
	}

	@DataProvider(name = "ItemName")
	public static Object[][] productName() {
		return new Object[][] { { "Stylish Dress", "2" }, { "Sleeves Printed Top - White", "3" },
				{ "Frozen Tops For Kids", "4" }, };
	}

	@DataProvider(name = "paymentinfo")
	public Object[][] providePaymentData() {

		UserSignUp data = new UserSignUp();
		data.password = "sarah123#";
		data.nameOnCard = "Sarah";
		data.cardNumber = "12334567890";
		data.cvc = "231";
		data.expiryMonth = "10";
		data.expiryYear = "2030";
		return new Object[][] { { data } };
	}

}
