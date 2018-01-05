package eltautomation.PageActions;

import org.openqa.selenium.*;

import eltautomation.PageObjects.*;
import eltautomation.Utils.*;

public class MainNavigationActions {
	public static void logoutUser(WebDriver driver, RunLogger logger) {
		MainNavigationActions.openUserMenu(driver, logger);
		MainNavigation.getUserMenuLogoutLink(driver).click();
		GenericPageAction.delay();
		GenericPageAction.waitPageLoad(driver, 60);
	}
	
	public static void loadMyAccount(WebDriver driver, RunLogger logger) {
		MainNavigationActions.openUserMenu(driver, logger);
		MainNavigation.getUserMenuMyAccountLink(driver).click();
		GenericPageAction.delay(10);
//		GenericPageAction.waitPageLoad(driver, 60);
	}
	
	public static void openUserMenu(WebDriver driver, RunLogger logger) {
		MainNavigation.getUserMenu(driver).click();
		GenericPageAction.delay();
	}
}
