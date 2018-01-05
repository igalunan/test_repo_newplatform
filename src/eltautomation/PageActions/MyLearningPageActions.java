package eltautomation.PageActions;

import org.openqa.selenium.*;

import eltautomation.PageObjects.*;
import eltautomation.Utils.*;

public class MyLearningPageActions {
	
	//Action to Click the My Learning tab in the Menu Panel
	public static void clickMyLearning(WebDriver driver, RunLogger logger) {
		try {	
				
			MyLearningPage.getMyLearningNav(driver).click();
			GenericPageAction.delay();
			GenericPageAction.waitPageLoad(driver, 60);
			
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
	
	//Validate if the My Learning tab in the Menu Panel is Active
	public static void validateMyLearningNavLink(WebDriver driver, RunLogger logger) {

		try {		
			String mylearningclass = MyLearningPage.getMyLearningNav(driver).getAttribute("class");
			
			logger.setStepDesc("Validate if my learning navlink is active");
			logger.setExpectedResult("My learning navlink should be active");
			
			if (mylearningclass.contains("active")) {
				logger.setActualResult("My learning navlink is active");
				logger.setStepMark(RunLogger.PASS);
			} else {
				logger.setActualResult("My learning navlink is not active");
				logger.setStepMark(RunLogger.FAIL);
			}
			
			try {
				logger.setPostStepScreenshot(driver);
				logger.logStep();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
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

	
	//Validate if user is able to view the My Learning header
	public static void validateMyLearningHeader(WebDriver driver, RunLogger logger) {

		try {		
			String mylearningclass = MyLearningPage.getMyLearningHeader(driver).getText(); 
			
			logger.setStepDesc("Validate if user is able to view the My Learning header");
			logger.setExpectedResult("User should be able to view the My learning header");
			
			if (mylearningclass.contains("My Learning")) {
				logger.setActualResult("User is able to view the My learning header");
				logger.setStepMark(RunLogger.PASS);
			} else {
				logger.setActualResult("User is NOT able to view the My learning header");
				logger.setStepMark(RunLogger.FAIL);
			}
			
			try {
				logger.setPostStepScreenshot(driver);
				logger.logStep();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
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

	