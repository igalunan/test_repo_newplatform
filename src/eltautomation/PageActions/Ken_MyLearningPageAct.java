package eltautomation.PageActions;

import org.openqa.selenium.*;

import eltautomation.PageObjects.*;
import eltautomation.Utils.*;

public class Ken_MyLearningPageAct {
	public static void validateMyLearningNavLink(WebDriver driver, RunLogger logger) {

		try {		
			String mylearningclass = Ken_MyLearningObj.getMyLearningNav(driver).getAttribute("class");
			
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

	
	
	public static void clickMyLearning(WebDriver driver, RunLogger logger) {
		try {	
				
			Ken_MyLearningObj.getMyLearningNav(driver).click();
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
	
	
}

	