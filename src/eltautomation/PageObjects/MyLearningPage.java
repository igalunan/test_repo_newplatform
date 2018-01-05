package eltautomation.PageObjects;

import org.openqa.selenium.*;

public class MyLearningPage {

	//Object to validate if the My Learning tab in the Menu Panel is Active
	public static WebElement getMyLearningNav(WebDriver driver) throws NoSuchElementException {
		return driver.findElement(By.cssSelector("li.mylearning"));
	}
	
	public static WebElement getMyLearningNavLink(WebDriver driver) throws NoSuchElementException {
		return MyLearningPage.getMyLearningNav(driver).findElement(By.cssSelector("a"));
	}
	
	//Object to validate the page header "My Learning"
	public static WebElement getMyLearningHeader(WebDriver driver) throws NoSuchElementException {
		return driver.findElement(By.cssSelector("h1.bold-font"));
	}
	
	
}
