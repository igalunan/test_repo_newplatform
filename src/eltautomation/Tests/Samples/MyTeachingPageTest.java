package eltautomation.Tests.Samples;

import org.openqa.selenium.WebDriver;

import eltautomation.PageActions.*;
import eltautomation.Utils.ITestScript;
import eltautomation.Utils.RunLogger;
import eltautomation.Utils.TestScenario;

public class MyTeachingPageTest implements ITestScript{
	private TestScenario ts;
	private RunLogger logger;
	private WebDriver driver;
	
	public MyTeachingPageTest(WebDriver wd, TestScenario ts, RunLogger logger) {
		this.driver = wd;
		this.ts = ts;
		this.logger = logger;
	}
	
	@Override
	public void logout() {
		// TODO Auto-generated method stub
		MainNavigationActions.logoutUser(driver, logger);
	}

	@Override
	public void login() {
		// TODO Auto-generated method stub
		Ken_MainLoginPageAct.login(driver, logger, "prodksgtea01", "cup123");
		
	}
	
	@Override
	public void runTest() {
		// TODO Auto-generated method stub
		
		this.logScenario(this.ts.getID() + "A", "Validate if My Teaching navlink is active");
		
		this.login();	
		MyTeachingPageActions.clickMyTeaching(driver, logger);
		MyTeachingPageActions.validateMyTeachingNavLink(driver, logger);
		this.logUpdateScenario();
		
		this.logScenario(this.ts.getID() + "B", "Validate if user is able to view the default school");
		MyTeachingPageActions.validateSchoolName(driver, logger);
		
		this.logout();
		this.logUpdateScenario();
		
	}
	
		
	public void logScenario(String scenarioID, String scenariodesc) {
		try {
			this.logger.logScenario(scenarioID, scenariodesc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public void logUpdateScenario(){
		try {
			this.logger.logUpdateScenario();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
	
	
	
