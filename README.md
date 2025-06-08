# Automation Exercise - Test Automation Suite

This project automates testing of user flows on [automationexercise.com](https://automationexercise.com/) using Java, Selenium WebDriver, and TestNG. The main workflows covered include user account creation, login, placing new orders (adding products to cart and checking out), and form validations. Utility classes are used to simplify interactions with web elements.

---

## 🚀 Project Purpose

Automate and validate the following flows on the automationexercise.com demo web application:

- User account creation
- User login
- Adding products to cart
- Placing an order and filling payment details
- Validating successful workflow completion with assertions

---

## 🗂️ Main Components

### 1. `AccountCreation.java`
Automates the user sign-up process:
- Navigates to the signup/login page and validates navigation.
- Fills out the signup form using data from a data provider.
- Submits the form and checks sign-up success.
- Fills in further account information (personal data, DOB, address, etc.).
- Submits the account creation form and verifies success.

### 2. `AccountInfo.java`
- Model class representing user information for account creation.
- Stores password, date of birth, name, address, and mobile number fields.

### 3. `NewOrderPlace.java`
Automates placing an order:
- Logs in to an existing account.
- Searches for products and adds them to the cart.
- Validates that the product was added successfully.
- Proceeds to checkout, fills payment details, and places the order.
- Verifies the order confirmation message.

### 4. `Utilities.java`
A helper class with generic methods for:
- Finding elements by tag, name, ID, XPath, or CSS selector.
- Getting or validating the current page URL.
- Entering text into input fields and selecting dropdown values.
- Validating form field attributes.
- Asserting test validations.

---

## 🛠️ Technologies Used

- **Java**: Main programming language
- **Selenium WebDriver**: Browser automation
- **TestNG**: Test management and execution
- **Maven**: Dependency management

---

## 🔄 Typical Automated Workflow

1. Launch browser and open the target site.
2. Create a new user account with provided details.
3. Log in and validate successful login.
4. Search and add products to the cart.
5. Proceed to checkout, fill payment details, and place the order.
6. Validate that an order completion message is displayed.

---

## Getting Started

## 🚀 Setup Instructions

### Prerequisites

- **Java JDK** (Recommended: Java 8 or above)
- **Maven** (for dependency management and running tests)
- **Edge Browser** (Microsoft Edge installed)
- **Edge WebDriver** (Download and set path to msedgedriver.exe)
- Internet connection

### 1. Clone the repository

```sh
git clone https://github.com/SangeethaPriya1991/automationexercise.git
cd automationexercise
```

### 2. Install dependencies
Ensure Maven is installed. To verify, run:

```sh
mvn -version
```

If Maven is installed, run:

```sh
mvn clean install
```

This will download all project dependencies.

### 3. Configure Edge WebDriver
Download the Edge WebDriver (msedgedriver.exe) compatible with your Edge browser version from here.
Place the msedgedriver.exe in a known folder (e.g., C:\Sangeetha\Driver\edgedriver_win64\).
If you want to use a different path, update the path in the test classes (System.setProperty("Webdriver.Edge.driver", "your/path/to/msedgedriver.exe");).

### 4. Run tests
     ```sh
     mvn test
     ```
Test output and results will be visible in the console and target/surefire-reports directory.

### 5. Customizing Test Data
Test data is provided by the DataProvider classes in the src/test/java/DataProvider/ directory.
You can modify these files to change the data used in test cases.


     
