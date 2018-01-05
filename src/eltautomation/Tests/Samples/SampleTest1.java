package eltautomation.Tests.Samples;

import org.openqa.selenium.*;

import eltautomation.PageActions.MainLoginPageActions;
import eltautomation.PageActions.MainNavigationActions;
import eltautomation.Utils.*;

public class SampleTest1 implements ITestScript {
	private TestScenario ts;
	private RunLogger logger;
	private WebDriver driver;
	
	public SampleTest1(WebDriver wd, TestScenario ts, RunLogger logger) {
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
		MainLoginPageActions.loginUser(this.driver, this.logger, "m1student1", "passw0rd");
	}

	@Override
	public void runTest() {
		try {
			this.logger.logScenario(this.ts.getID(), this.ts.getDescription());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.login();
		this.logout();
		
		try {
			this.logger.logUpdateScenario();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
