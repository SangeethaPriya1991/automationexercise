
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Utilities {

	WebDriver driver;

	public Utilities(WebDriver driver) {
		this.driver = driver;
	}
	
	public String GetCurrentUrl()
	{
		return driver.getCurrentUrl();
	}
	public List<WebElement> GetElementByTag(String tagName)
	{
		return driver.findElements(By.tagName(tagName));
	}
	
	public List<WebElement> GetElementByTag(WebElement element, String name)
	{
		return element.findElements(By.tagName(name));
	}
	
	public WebElement GetElementByName(String name)
	{
		return driver.findElement(By.name(name));
	}
	
	public WebElement GetElementByName(WebElement element, String name)
	{
		return element.findElement(By.name(name));
	}
	
	public WebElement GetElementById(String elementId)
	{
		return driver.findElement(By.id(elementId));
	}
	
	public WebElement GetElementById(WebElement element, String elementId)
	{
		return element.findElement(By.id(elementId));
	}
	
	public WebElement GetElementByXpath(String xpath) {
		
		return driver.findElement(By.xpath(xpath));
	}
	public List<WebElement> GetElementByXpathList(String elementxpath) {
		
		return driver.findElements(By.xpath(elementxpath));
	}

	public List<WebElement> GetElementbycssSelector(String cssSelector){
		return driver.findElements(By.cssSelector(cssSelector));
	}
	public List<WebElement> GetElementbycssSelector(WebElement element, String cssSelector){
		return driver.findElements(By.cssSelector(cssSelector));
	}
	
	public String GetAttributeValue(String elementId, String attributeName)
	{
		WebElement field = GetElementById(elementId);
		return field.getAttribute(attributeName);
	}	

	public void ValidateAttributeByElementId(String elementId, String attributeName, String expectedValue, String errorMessage) {
		String attributeValue = GetAttributeValue(elementId, attributeName);
		Assert.assertEquals(attributeValue, expectedValue, errorMessage);
	}	
	
	public void enterText(String id, String value) {
		WebElement field = GetElementById(id);
		Assert.assertTrue(field.isDisplayed() && field.isEnabled(), id + " is not interactable");
		field.clear();
		field.sendKeys(value);
	}
	public void enterTextbyName(String id, String name, String value) {
		WebElement field = GetElementByName(name);
		Assert.assertTrue(field.isDisplayed() && field.isEnabled(), name + " is not interactable");
		field.clear();
		field.sendKeys(value);
	}
	

	public void selectDropdownValue(String id, String visibleText) {
		Select dropdown = new Select(GetElementById(id));
		dropdown.selectByVisibleText(visibleText);
	}

}
