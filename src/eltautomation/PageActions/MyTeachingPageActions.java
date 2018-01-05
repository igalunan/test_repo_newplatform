package eltautomation.PageActions;

import org.openqa.selenium.*;

import eltautomation.PageObjects.*;
import eltautomation.Utils.*;

public class MyTeachingPageActions {
	

	//Action to Click the My Teaching tab in the Menu Panel
	public static void clickMyTeaching(WebDriver driver, RunLogger logger) {
		try {	
				
			MyTeachingPage.getMyTeachingNav(driver).click();
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
	

	//Validate if the My Teaching tab in the Menu Panel is Active
	public static void validateMyTeachingNavLink(WebDriver driver, RunLogger logger) {
	try {	
		String mylearningclass = MyTeachingPage.getMyTeachingNav(driver).getAttribute("class");	
	
		logger.setStepDesc("Validate if my teaching navlink is active");
		logger.setExpectedResult("My teaching navlink should be active");
		
		if (mylearningclass.contains("active")) {
			logger.setActualResult("My teaching navlink is active");
			logger.setStepMark(RunLogger.PASS);
		} else {
			logger.setActualResult("My teaching navlink is not active");
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
	


	//Validate if the teacher is able to view the default school
	public static void validateSchoolName(WebDriver driver, RunLogger logger) {
		try {	
			
			String schoolName = MyTeachingPage.getSchoolName(driver).getText();
			
			logger.setStepDesc("Validate if the School Name is zUAT Main (Level 4) Campus");
			logger.setExpectedResult("The School Name should be zUAT Main (Level 4) Campus");
			
			if (schoolName.contains("zUAT Main (Level 4...")) {
				logger.setActualResult("The School Name is zUAT Main (Level 4) Campus");
				logger.setStepMark(RunLogger.PASS);
			} else {
				logger.setActualResult("The School Name is NOT zUAT Main (Level 4) Campus");
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

	
	
	
	
	
	
	
	/*
	 * 
	try {	
	//Your code here
		
		
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
	
	*/
	
	

	