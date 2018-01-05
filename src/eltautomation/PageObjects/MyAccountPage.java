package eltautomation.PageObjects;

import org.openqa.selenium.*;

public class MyAccountPage {
	public static WebElement closeLink(WebDriver driver) throws NoSuchElementException {
		return driver.findElement(By.cssSelector("a#cboxClose"));
	}
	
	public static WebElement getNewPasswordField(WebDriver driver) throws NoSuchElementException {
		return driver.findElement(By.id("edit-pass-pass1"));
	}
	
	public static WebElement getConfirmPasswordField(WebDriver driver) throws NoSuchElementException {
		return driver.findElement(By.id("edit-pass-pass2"));
	}
	
	public static WebElement getSubmitButton(WebDriver driver) throws NoSuchElementException {
		return driver.findElement(By.cssSelector("button#edit-submit"));
	}
}
