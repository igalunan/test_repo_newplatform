package eltautomation.Tests.Samples;

import org.openqa.selenium.WebDriver;

import eltautomation.PageActions.Ken_MainLoginPageAct;
import eltautomation.PageActions.MainLoginPageActions;
import eltautomation.PageActions.MainNavigationActions;
import eltautomation.PageActions.MyAccountPageActions;
import eltautomation.PageActions.MyTeachingPageActions;
import eltautomation.PageObjects.MainLoginPage;
import eltautomation.Utils.ITestScript;
import eltautomation.Utils.RunLogger;
import eltautomation.Utils.TestScenario;

public class ChangePasswordMyAccount implements ITestScript{
	private TestScenario ts;
	private RunLogger logger;
	private WebDriver driver;
	
	public ChangePasswordMyAccount(WebDriver wd, TestScenario ts, RunLogger logger) {
		this.driver = wd;
		this.ts = ts;
		this.logger = logger;
	}
	
	@Override
	public void logout() {
		MainNavigationActions.logoutUser(driver, logger);
	}

	@Override
	public void login() {
		MainLoginPageActions.loginUser(driver, logger, "m1teacher1", "passw0rd");
	}
	
	private void login(String uname, String pword) {
		MainLoginPageActions.loginUser(driver, logger, uname, pword);
	}
	
	@Override
	public void runTest() {
		this.logScenario(this.ts.getID(), this.ts.getDescription());
		this.login();
		
		MainNavigationActions.loadMyAccount(driver, logger);
		MyAccountPageActions.changePassword(driver, logger, "passw0rd1");
		MyAccountPageActions.closeMyAccount(driver, logger);
		
		this.logout();
		
		this.login("m1teacher1", "passw0rd1");
		
		MyTeachingPageActions.validateMyTeachingNavLink(driver, logger);
		
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
