package eltautomation.PageActions;

import org.openqa.selenium.*;

import eltautomation.PageObjects.*;
import eltautomation.Utils.*;

public class MyAccountPageActions {
	public static void closeMyAccount(WebDriver driver, RunLogger logger) {
		MyAccountPage.closeLink(driver).click();
		GenericPageAction.delay(2);
	}
	
	public static void changePassword(WebDriver driver, RunLogger logger, String pword) {
		try {
			driver.switchTo().frame(MyAccountPage.getMyAccountIFrame(driver));
			
			MyAccountPageActions.enterPassword(driver, logger, MyAccountPage.getNewPasswordField(driver), pword, 
					"User should be able to enter a password in the New Password field");
			
			MyAccountPageActions.enterPassword(driver, logger, MyAccountPage.getConfirmPasswordField(driver), pword, 
					"User should be able to enter a password in the Confirm Password field");
			
			MyAccountPageActions.submit(driver, logger);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void enterPassword(WebDriver driver, RunLogger logger, WebElement pwordField, String pword, String stepDesc) {
		try {
			pwordField.sendKeys(pword);
			logger.logStep(stepDesc,
					"Password should be entered: " +pword,
					"Password is entered: " +pword, 
					RunLogger.PASS, driver);
		} catch(NoSuchElementException nsee) {
			try {
				logger.logStep(stepDesc, 
						"Password should be entered: " +pword,
						"Password field is missing",
						RunLogger.FAIL, driver);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void submit(WebDriver driver, RunLogger logger) {
		try {
			MyAccountPage.getSubmitButton(driver).click();
			
			GenericPageAction.delay();
			GenericPageAction.waitPageLoad(driver, 60);
			
			logger.logStep("User should be able to submit changes", 
					"Changes should be submitted",
					"Changes are submitted",
					RunLogger.PASS, driver);
		} catch(NoSuchElementException nsee) {
			try {
				logger.logStep("User should be able to submit changes", 
						"Changes should be submitted",
						"Submit button missing",
						RunLogger.FAIL, driver);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
