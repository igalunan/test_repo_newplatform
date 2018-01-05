package eltautomation.PageActions;

import org.openqa.selenium.*;

import eltautomation.PageObjects.*;
import eltautomation.Utils.*;

public class MyAccountPageActions {
	public static void closeMyAccount(WebDriver driver, RunLogger logger) {
		MyAccountPage.closeLink(driver).click();
		GenericPageAction.delay(2);
	}
}
