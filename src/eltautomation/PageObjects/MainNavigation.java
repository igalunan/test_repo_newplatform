package eltautomation.PageObjects;

import org.openqa.selenium.*;

public class MainNavigation {
	public static WebElement getUserMenu(WebDriver driver) throws NoSuchElementException {
		return driver.findElement(By.cssSelector("a.nav-remove-bg > i.icon"));
	}
	
	public static WebElement getUserMenuLogoutLink(WebDriver driver) throws NoSuchElementException {
		return driver.findElement(By.cssSelector("ul.csssubmenu > li.submenu-logout"));
	}
	
	public static WebElement getUserMenuMyAccountLink(WebDriver driver) throws NoSuchElementException {
		return driver.findElement(By.cssSelector("ul.csssubmenu > li.submenu-my-account"));
	}
}
