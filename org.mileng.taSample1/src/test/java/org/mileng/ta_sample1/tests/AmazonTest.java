package org.mileng.ta_sample1.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.mileng.ta_sample1.pages.AmazonHome;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class AmazonTest extends TestBase {

	@Test(dataProvider = "dp")
	public void verifyHarryPotterBook(Integer n, String s) {

		startTest("AmazonTest>verifyHarryPotterBook");

		startStep("Go to Amazon home page");
		// UserInfo user = createUserAndLogin(true);
		@SuppressWarnings("unused")
		AmazonHome homePage = goToAmazonHome(null);
		endStep();

		endTest();
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { 1, "a" } // ,
				// new Object[] { 2, "b" },
		};
	}

	@BeforeClass
	public void beforeClass() {
	}

	@AfterClass
	public void afterClass() {
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
	}

	@BeforeSuite
	public void beforeSuite() {
	}

	@AfterSuite
	public void afterSuite() {
	}

}
