package eltautomation.Utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class GenericPageAction {
	public static void waitPageLoad(WebDriver driver, int seconds) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
            	return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			}
		};
		
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		
		wait.until(pageLoadCondition);
		GenericPageAction.delay(1);
		driver.switchTo().defaultContent();
	}
	
	public static void delay(int seconds) {
		try {
        	Thread.sleep(seconds * 1000);
        } catch (Exception e) {
        }
	}
	
	public static void delay() {
		GenericPageAction.delay(1);
	}
	
	public static void navigateToURL(WebDriver driver, String newurl, int seconds) {
		driver.get(newurl);
		GenericPageAction.waitPageLoad(driver, seconds);
	}
}
