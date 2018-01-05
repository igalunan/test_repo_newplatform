package eltautomation.PageActions;

import org.openqa.selenium.*;

import eltautomation.PageObjects.*;
import eltautomation.Utils.*;

public class MainLoginPageActions {
	public static void loginUser(WebDriver driver, RunLogger logger, String uname, String pword) {
		try {
			logger.setPreStepScreenshot(driver);
			driver.switchTo().frame(MainLoginPage.getLoginIFrame(driver));
			MainLoginPage.getUsernameBox(driver).sendKeys(uname);
			logger.logStep("Enters username in text box " + uname,
					uname + " is entered in textbox",
					uname + " is entered in textbox",
					RunLogger.PASS, driver);
			
			logger.setPreStepScreenshot(driver);
			MainLoginPage.getPasswordBox(driver).sendKeys(pword);
			logger.logStep("Enters password in text box " + pword,
					pword + " is password in textbox",
					pword + " is password in textbox",
					RunLogger.PASS, driver);
			
			logger.setPreStepScreenshot(driver);
			MainLoginPage.getLoginButton(driver).click();
			GenericPageAction.delay();
			GenericPageAction.waitPageLoad(driver, 60);
			logger.logStep("Clicks login button",
					"User is logged in",
					"User is logged in",
					RunLogger.PASS, driver);
			
		} catch (NoSuchElementException e) {
			try {
				logger.logStep("User logs in",
						"User successfully logs in",
						"User is not logged in",
						RunLogger.ERR, driver);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
