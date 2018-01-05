package eltautomation.Tests.Samples;

import org.openqa.selenium.*;

import eltautomation.PageActions.MainLoginPageActions;
import eltautomation.PageActions.MainNavigationActions;
import eltautomation.PageActions.MyAccountPageActions;
import eltautomation.Utils.*;

public class SampleTest2 implements ITestScript {
	private TestScenario ts;
	private RunLogger logger;
	private WebDriver driver;
	
	public SampleTest2(WebDriver wd, TestScenario ts, RunLogger logger) {
		this.driver = wd;
		this.ts = ts;
		this.logger = logger;
	}
	
	@Override
	public void logout() {
		MainNavigationActions.logoutUser(this.driver, this.logger);
	}

	@Override
	public void login() {
		MainLoginPageActions.loginUser(this.driver, this.logger, "m1student2", "passw0rd");
	}

	@Override
	public void runTest() {
		this.logScenario(this.ts.getID(), this.ts.getDescription());
		this.login();
		MainNavigationActions.loadMyAccount(this.driver, this.logger);
		MyAccountPageActions.closeMyAccount(this.driver, this.logger);
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
