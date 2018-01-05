package eltautomation.PageObjects;

import org.openqa.selenium.*;

public class Ken_MainLoginPageObj {
	
	public static WebElement getUsernameTextBox(WebDriver driver) throws NoSuchElementException {
	//WebElement - an interface from org.openqa.selenium Package. It represents an HTML element.
	//WebDriver - the main interface to use for testing, which represents an idealised web browser.
			//driver - is an object that sees all the elements in the browser
			//driver.findElement(By.cssSelector("#username")).findElement(arg0)
		return driver.findElement(By.id("username"));
		//findElement - find the first WebElement using the given method. 
	}
	
	
	public static WebElement getPasswordTextBox(WebDriver driver) throws NoSuchElementException {
		return driver.findElement(By.id("password"));
	}
	
	
	public static WebElement getLoginButton(WebDriver driver) throws NoSuchElementException {
		return driver.findElement(By.className("clms-small-btn"));
		//return driver.findElement(By.cssSelector("button.clms-small-btn"));
	}
	
	
	public static WebElement getLoginIframe(WebDriver driver) throws NoSuchElementException {
		return driver.findElement(By.id("cas_iframe"));
	}
	
}
