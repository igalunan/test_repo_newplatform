package eltautomation.PageObjects;

import org.openqa.selenium.*;

public class MyTeachingPage {

	//Object to validate if the My Teaching tab in the Menu Panel is Active
	public static WebElement getMyTeachingNav(WebDriver driver) throws NoSuchElementException {
		return driver.findElement(By.cssSelector("li.myteaching"));
	}
	
	public static WebElement getMyTeachingNavLink(WebDriver driver) throws NoSuchElementException {
		return MyTeachingPage.getMyTeachingNav(driver).findElement(By.cssSelector("a"));
	}
	

	//Object to validate if the teacher is able to view the default school
	public static WebElement getSchoolNameNav(WebDriver driver) throws NoSuchElementException {
		return driver.findElement(By.cssSelector("li.class-nav-resources"));
	}
	
	
	public static WebElement getSchoolName(WebDriver driver) throws NoSuchElementException {
		return MyTeachingPage.getSchoolNameNav(driver).findElement(By.cssSelector("a.class-campus"));
	}
	
	
}
