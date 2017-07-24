package org.mileng.ta_sample1.tests;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.mileng.ta_sample1.framework.WebDriverEx;
import org.mileng.ta_sample1.pages.AmazonHome;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class TestBase extends WebDriverEx {

	private boolean stepFailuresPresent;

	@AfterMethod
	public void cleanup() {
		if (wd != null) {
			wd.quit();
		}
	}

	public void startTest(String testName) {
		int browserWidth = 1024;
		int browserHeight = 768;

		System.out.println("**** Starting test: " + testName + ", dateTime: " + new Date());
		System.out.println(String.format("(%dx%d)", browserWidth, browserHeight));

		wd = initWebDriver();
		wd.manage().window().setSize(new Dimension(browserWidth, browserHeight));
		wd.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);

	}

	private WebDriver initWebDriver() {
		System.setProperty("webdriver.chrome.driver", "/home/improve1-pc1/User1/Installed/chromedriver");
		return new ChromeDriver();
	}

	/**
	 * Logs the start of a test step
	 *
	 * @param stepDescription
	 *            step descriptions supporting String.format style macros
	 * @param args
	 *            The values for the format macros
	 */
	public void startStep(String stepDescription, Object... args) {
		String formattedString = String.format(stepDescription, args);
		System.out.println(" -  " + formattedString + "...");
	}

	/**
	 * Logs the completion of a test step
	 *
	 * @param isPassing
	 *            - whether the test step passed successfully or not
	 */
	public void endStep(boolean isPassing) {
		if (!isPassing) {
			System.out.println("    ^FAILED^");
			stepFailuresPresent = true;
		}
		// stepFinalized = true;
	}

	/**
	 * Logs the completion of a test step as successful
	 */
	public void endStep() {
		endStep(true);
	}

	/**
	 * Ends a test
	 */
	public void endTest() {
		try {
			if (stepFailuresPresent) {
				Assert.fail("Some test steps did not pass. Check the TestNG reporter output for more info.");
			}
			cleanup();
		} catch (Exception e) {
			cleanup();
			e.printStackTrace();
		}
	}

	public AmazonHome goToAmazonHome(String url) {
		if (url == null) {
			url = "https://www.amazon.co.uk/";
		}
		wd.get(url);
		return new AmazonHome(wd);
	}
}
