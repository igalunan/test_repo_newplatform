package eltautomation.PageActions;

import org.openqa.selenium.*;

import eltautomation.PageObjects.*;
import eltautomation.Utils.*;

public class Ken_MainLoginPageAct {
	public static void login(WebDriver driver, RunLogger logger, String uname, String pword) {
	//RunLogger?
		
	try {
		
		//Switch to Iframe to get the username and password textboxes.
		driver.switchTo().frame(Ken_MainLoginPageObj.getLoginIframe(driver));
		logger.logStep("Switching to Iframe", 
				"Iframe should be present", 
				"Iframe is present", 
				RunLogger.PASS, driver);

		
		Ken_MainLoginPageObj.getUsernameTextBox(driver).sendKeys(uname);
		logger.logStep("Switching to Iframe", 
				"Username textbox should be present", 
				"Username textbox is present", 
				RunLogger.PASS, driver);
		
		Ken_MainLoginPageObj.getPasswordTextBox(driver).sendKeys(pword);
		logger.logStep("Switching to Iframe", 
				"Password textbox should be present", 
				"Password textbox is present", 
				RunLogger.PASS, driver);
		
		Ken_MainLoginPageObj.getLoginButton(driver).click();
		logger.logStep("Switching to Iframe", 
				"Login button should be present", 
				"Login button is present", 
				RunLogger.PASS, driver);
		
		GenericPageAction.delay();
		GenericPageAction.waitPageLoad(driver, 120);
		
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
