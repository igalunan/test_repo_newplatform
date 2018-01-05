package eltautomation.Utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

public class WebDriverFactory {
	
	public static WebDriver getGridDriver(String browser) {
		WebDriver driver = null;
		
		switch (browser.toLowerCase()) {
			case "firefox":
				System.setProperty("webdriver.gecko.driver", "C:\\selenium-drivers\\geckodriver.exe");
				FirefoxOptions ffopt = new FirefoxOptions();
				DesiredCapabilities ffdc = DesiredCapabilities.firefox();
				/*DesiredCapabilities cap = DesiredCapabilities.safari();
				cap.(SafariOptions.CAPABILITY, )
				RemoteWebDriver rwd = new RemoteWebDriver(capabilities)*/
				
				ffopt.setAcceptInsecureCerts(true);
				ffopt.setCapability("marionette", true);
				ffopt.setCapability("javascriptEnabled", true);
				ffopt.addPreference("browser.helperApps.alwaysAsk.force", false);
				ffopt.addPreference("browser.download.dir", System.getProperty("user.dir") + "/Downloads");
				ffopt.addPreference("browser.download.folderList", 2);
				ffopt.addPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv,text/plain,text/html,application/plain");
				ffdc.setCapability(FirefoxOptions.FIREFOX_OPTIONS, ffopt);
				
				driver = new RemoteWebDriver(ffdc);
				break;
				
			case "ie":
				System.setProperty("webdriver.ie.driver", "C:\\selenium-drivers\\IEDriverServer64.exe");
				DesiredCapabilities iedc = DesiredCapabilities.internetExplorer();
				
				iedc.setCapability("javascriptEnabled", true);
				iedc.setCapability("nativeEvents", false);
				iedc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				// iedc.setCapability("ignoreProtectedModeSettings", true);
				iedc.setCapability("ignoreZoomSetting", false);
				iedc.setCapability("initialBrowserUrl", "about:blank");
				iedc.setCapability("enablePersistentHover", true);
				iedc.setCapability("elementScrollBehavior", false);
				iedc.setCapability("requireWindowFocus", false);
				iedc.setCapability("unexpectedAlertBehaviour", "accept");
				iedc.setCapability("ie.ensureCleanSession", true);
				iedc.setCapability("ie.forceCreateProcessApi", true);
				
				driver = new RemoteWebDriver(iedc);
				break;
				
			case "ie32":
				break;
				
			case "chrome":
				System.setProperty("webdriver.chrome.driver", "C:\\selenium-drivers\\chromedriver.exe");
				
				Map<String, Object> prefs = new HashMap<String, Object>();
				
				prefs.put("profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1);
				prefs.put("download.prompt_for_download", "false");
				prefs.put("download.default_directory", System.getProperty("user.dir") + "\\Downloaded");
				prefs.put("download.extensions_to_open", "text/csv");
				
				ChromeOptions copt = new ChromeOptions();
				DesiredCapabilities cdc = DesiredCapabilities.chrome();
				
				copt.addArguments(Arrays.asList("start-maximized", "test-type", "disable-popup-blocking", "disable-default-apps", "auto-launch-at-startup",
						"always-authorize-plugins", "disable-infobars", "disable-infobar-for-protected-media-identifier",
						"safebrowsing-disable-download-protection", "ignore-certificate-errors"));
				copt.setCapability("prefs", prefs);
				copt.setCapability("javascriptEnabled", true);
				cdc.setCapability(ChromeOptions.CAPABILITY, copt);
				
				driver = new RemoteWebDriver(cdc);
				break;
				
			case "safari":
				break;
				
			default:
				
		}
		
		driver.manage().window().maximize();
		
		return driver;
	}
	
	public static WebDriver getDriver(String browser) {
		WebDriver driver = null;
		
		switch (browser.toLowerCase()) {
			case "firefox":
				System.setProperty("webdriver.gecko.driver", "C:\\selenium-drivers\\geckodriver.exe");
				
				FirefoxOptions ffopt = new FirefoxOptions();
				/*DesiredCapabilities cap = DesiredCapabilities.safari();
				cap.(SafariOptions.CAPABILITY, )
				RemoteWebDriver rwd = new RemoteWebDriver(capabilities)*/
				
				ffopt.setAcceptInsecureCerts(true);
				ffopt.setCapability("marionette", true);
				ffopt.setCapability("javascriptEnabled", true);
				ffopt.addPreference("browser.helperApps.alwaysAsk.force", false);
				ffopt.addPreference("browser.download.dir", System.getProperty("user.dir") + "/Downloads");
				ffopt.addPreference("browser.download.folderList", 2);
				ffopt.addPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv,text/plain,text/html,application/plain");
				
				driver = new FirefoxDriver(ffopt);
				break;
				
			case "ie":
				System.setProperty("webdriver.ie.driver", "C:\\selenium-drivers\\IEDriverServer64.exe");
				
				InternetExplorerOptions ieopt = new InternetExplorerOptions();
				
				ieopt.setCapability("javascriptEnabled", true);
				ieopt.setCapability("nativeEvents", false);
				ieopt.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				ieopt.setCapability("ignoreProtectedModeSettings", true);
				ieopt.setCapability("ignoreZoomSetting", false);
				ieopt.setCapability("initialBrowserUrl", "about:blank");
				ieopt.setCapability("enablePersistentHover", true);
				ieopt.setCapability("elementScrollBehavior", false);
				ieopt.setCapability("requireWindowFocus", false);
				ieopt.setCapability("unexpectedAlertBehaviour", "accept");
				ieopt.setCapability("ie.ensureCleanSession", true);
				ieopt.setCapability("ie.forceCreateProcessApi", true);
				
				driver = new InternetExplorerDriver(ieopt);
				break;
				
			case "ie32":
				System.setProperty("webdriver.ie.driver", "C:\\selenium-drivers\\IEDriverServer32.exe");
				
				InternetExplorerOptions ie32opt = new InternetExplorerOptions();
				
				ie32opt.setCapability("javascriptEnabled", true);
				ie32opt.setCapability("nativeEvents", true);
				ie32opt.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				ie32opt.setCapability("nativeEvents", true);
				ie32opt.setCapability("ignoreProtectedModeSettings", true);
				ie32opt.setCapability("ignoreZoomSetting", false);
				ie32opt.setCapability("initialBrowserUrl", "about:blank");
				ie32opt.setCapability("enablePersistentHover", true);
				ie32opt.setCapability("elementScrollBehavior", false);
				ie32opt.setCapability("requireWindowFocus", false);
				ie32opt.setCapability("unexpectedAlertBehaviour", "accept");
				ie32opt.setCapability("ie.ensureCleanSession", true);
				ie32opt.setCapability("ie.forceCreateProcessApi", true);
				
				driver = new InternetExplorerDriver(ie32opt);
				break;
				
			case "chrome":
				System.setProperty("webdriver.chrome.driver", "C:\\selenium-drivers\\chromedriver.exe");
				
				Map<String, Object> prefs = new HashMap<String, Object>();
				
				prefs.put("profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1);
				prefs.put("download.prompt_for_download", "false");
				prefs.put("download.default_directory", System.getProperty("user.dir") + "\\Downloaded");
				prefs.put("download.extensions_to_open", "text/csv");
				
				ChromeOptions copt = new ChromeOptions();
				
				copt.addArguments(Arrays.asList("start-maximized", "test-type", "disable-popup-blocking", "disable-default-apps", "auto-launch-at-startup",
						"always-authorize-plugins", "disable-infobars", "disable-infobar-for-protected-media-identifier",
						"safebrowsing-disable-download-protection", "ignore-certificate-errors"));
				copt.setCapability("prefs", prefs);
				copt.setCapability("javascriptEnabled", true);
				
				driver = new ChromeDriver(copt);
				break;
			
			case "safari":
				DesiredCapabilities capability = DesiredCapabilities.firefox();
				// capability.setBrowserName("safari");
				// capability.setPlatform(Platform.MAC);
				// capability.setVersion("9");
				
				try {
					driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
					driver = new Augmenter().augment(driver);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			default: // default browser is edge
				System.setProperty("webdriver.edge.driver", "C:\\selenium-drivers\\MicrosoftWebDriver14393.exe");
				
				/*EdgeOptions eopt = new EdgeOptions();
				eopt.setCapability("javascriptEnabled", true);*/
				
				driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		
		return driver;
		
		/* if (browser.equalsIgnoreCase("Chrome")){
		
			Map<String, Object> prefs = new HashMap<String, Object>();
			//prefs.put("profile.default_content_settings.popups", 0);
			//prefs.put("browser.download.dir", System.getProperty("user.dir")+"\\Downloaded");
			
			// Turn off Multiple download warning [ Only works in Chrome v30 and above ]
			prefs.put("profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1);
			prefs.put("download.prompt_for_download", "false");
			prefs.put("download.default_directory", System.getProperty("user.dir") + "\\Downloaded");
			//prefs.put("download.extensions_to_open", "application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/octet-stream");
			prefs.put("download.extensions_to_open", "text/csv");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			options.addArguments("start-maximized");
			options.addArguments("disable-infobars");
			// DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\libraries\\chromedriver.exe");
			capabilities.setCapability("chrome.switches", Arrays.asList("--disable-popup-blocking", "--disable-default-apps", "--auto-launch-at-startup","--always-authorize-plugins","--disable-infobars","--disable-infobar-for-protected-media-identifier","--safebrowsing-disable-download-protection")); // ,"--use-fake-ui-for-media-stream","--disable-web-security ", "--safebrowsing-disable-download-protection","--ignore-certificate-errors",
			//capabilities.setCapability("chrome.switches",Arrays.asList("use-fake-ui-for-media-stream"));
			capabilities.setCapability(ChromeOptions.CAPABILITY,options); 
			capabilities.setCapability("chrome.prefs", prefs);
			driver = new ChromeDriver(capabilities);
		}
		
		return driver; */
	}
}
