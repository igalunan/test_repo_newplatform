package eltautomation.PageObjects;

import org.openqa.selenium.*;

public class Ken_MyLearningObj {
	public static WebElement getMyLearningNav(WebDriver driver) throws NoSuchElementException {
		return driver.findElement(By.cssSelector("li.mylearning"));
	}
	
	public static WebElement getMyLearningNavLink(WebDriver driver) throws NoSuchElementException {
		return Ken_MyLearningObj.getMyLearningNav(driver).findElement(By.cssSelector("a"));
	}
	
	
	
}
