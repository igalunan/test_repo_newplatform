package eltautomation.Tests.Samples;

import org.openqa.selenium.WebDriver;

import eltautomation.Utils.*;

public class SampleTest3 implements ITestScript {
	private TestScenario ts;
	private RunLogger logger;
	private WebDriver driver;
	
	public SampleTest3(WebDriver wd, TestScenario ts, RunLogger logger) {
		this.driver = wd;
		this.ts = ts;
		this.logger = logger;
	}
	
	@Override
	public void logout() {
		
	}

	@Override
	public void login() {
		
	}

	@Override
	public void runTest() {
		
	}

}
