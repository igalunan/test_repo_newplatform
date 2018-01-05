package eltautomation.PageObjects;

import org.openqa.selenium.*;

public class MainLoginPage {
	public static WebElement getUsernameBox(WebDriver driver) throws NoSuchElementException {
		return driver.findElement(By.id("username"));
	}
	
	public static WebElement getLoginIFrame(WebDriver driver) throws NoSuchElementException {
		return driver.findElement(By.id("cas_iframe"));
	}
	
	public static WebElement getPasswordBox(WebDriver driver) throws NoSuchElementException {
		return driver.findElement(By.id("password"));
	}
	
	public static WebElement getLoginButton(WebDriver driver) throws NoSuchElementException {
		return driver.findElement(By.cssSelector("button.clms-small-btn"));
	}
}
