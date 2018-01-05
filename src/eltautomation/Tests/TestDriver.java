package eltautomation.Tests;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.*;

import eltautomation.Utils.*;

public class TestDriver implements Runnable {
	private WebDriver wd;
	private TestScenario ts;
	private RunLogger logger;
	
	public TestDriver(TestScenario ts) {
		this.ts = ts;
	}
	
	@Override
	public void run() {
		try {
			if (MainTest.areTestsStopped()) {
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to determine if tests should be stopped.");
			return;
		}
		
		this.initializeLogger();
		this.initializeWebDriver();
		this.executeTest();
		this.finalizeTest();
	}
	
	private void initializeWebDriver() {
		this.wd = WebDriverFactory.getDriver(this.ts.getBrowser());
		GenericPageAction.navigateToURL(this.wd, this.ts.getStartURL(), 60);
	}
	
	private void executeTest() {
		try {
			Class<?> testscriptclass = Class.forName(this.ts.getClassName());
			Method testmethod = testscriptclass.getMethod(this.ts.getMethodName(), (Class<?>[]) null);
			Constructor<?> ctor = testscriptclass.getDeclaredConstructors()[0];
			Object testscript = ctor.newInstance(this.wd, this.ts, this.logger);
			testmethod.invoke(testscript, (Object[]) null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void finalizeTest() {
		this.wd.quit();
	}
	
	private void initializeLogger() {
		this.logger = new RunLogger(MainTest.getRunID(), MainTest.getRunLabel(), MainTest.getRunTags());
		this.logger.setBrowser(this.ts.getBrowser());
		this.logger.setScenarioAppModule(this.ts.getAppModule());
		this.logger.setScenarioComplexity(this.ts.getComplexity());
		this.logger.setScenarioPkgClassMethod(this.ts.getPkgClassMethod());
		this.logger.setScenarioStoryPts(this.ts.getStoryPts());
	}
}

