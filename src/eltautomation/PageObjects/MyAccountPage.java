package eltautomation.PageObjects;

import org.openqa.selenium.*;

public class MyAccountPage {
	public static WebElement closeLink(WebDriver driver) throws NoSuchElementException {
		return driver.findElement(By.cssSelector("a#cboxClose"));
	}
}
