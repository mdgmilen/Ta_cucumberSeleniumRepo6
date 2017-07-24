package org.mileng.ta_sample1.pages;

import org.mileng.ta_sample1.framework.SyncEx;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SearchResults extends PageBase {
	private String parentXpath;
	private String title;

	public String getParentXpath() {
		return parentXpath;
	}

	public SearchResults(final WebDriver wd) {
		super(wd);
		PageFactory.initElements(wd, this);
		// https://www.amazon.co.uk/s/ref=nb_sb_noss/259-1503930-8163902?url=search-alias%3Daps&field-keywords=Harry+Potter+and+the+Cursed+Child
		final String expectedPageNamePart = "/s/ref=nb_sb_noss";
		Assert.assertTrue(SyncEx.wait(() -> wd.getCurrentUrl().contains(expectedPageNamePart)),
				String.format("We are not on the '%s' page", expectedPageNamePart));
	}

	private static final String itemWithTitleXpath = "//h2[contains(@data-attribute, '%s')]";
	private static final String aColRightParentByTitleXpath = "//h2[contains(@data-attribute, '%s')]/../../../..";
	private static final String badgeSpanTextXpath = "//span[text()='%s']";
	private static final String printTypeXpath = "//h3[text()='%s']";
	private static final String priceXpath = "//span[text()='%s']";
	private static final String aTitleXpath = "//a[contains(@title, '%s')]";

//	@FindBy(xpath = "//a[text()='See Details']")
//	private WebElement seeDetailsLink;
	
	public boolean verifyFirstItemTitle(String parTitle) {
		String xpath = String.format(itemWithTitleXpath, parTitle);
		parentXpath = String.format(aColRightParentByTitleXpath, parTitle);
		title = parTitle;
		// boolean found = existsWebElementUnique(null, xpath);
		boolean foundUsingParent = existsWebElementUnique(parentXpath, xpath);
		return foundUsingParent;
	}

	public boolean verifyHasBadge(String parParentXpath, String badgeText) {
		String xpath = String.format(badgeSpanTextXpath, badgeText);
		boolean foundUsingParent = existsWebElementUnique(parParentXpath, xpath);
		return foundUsingParent;
	}

	public boolean verifySelectedType(String parParentXpath, String typeText) {
		String xpath = String.format(printTypeXpath, typeText);
		boolean res = existsWebElementUnique(null, parParentXpath+ xpath);
		return res;
		// //h2[contains(@data-attribute, 'Harry Potter and the Cursed Child - Parts One and Two')]/../../../..//h3[text()='Paperback']
	}

	public boolean verifyPrice(String parParentXpath, String priceText) {
		String xpath = String.format(priceXpath, priceText);
		return existsWebElement(parParentXpath, xpath);
	}
	
	public ResultDetails goToResultDetails(String resultContextXpath, String parTitle) {
		// //h2[contains(@data-attribute, 'Harry Potter and the Cursed Child - Parts One and Two')]/../../../..//a[contains(@title, '')]
		//seeDetailsLink.click();
		String xpath = String.format(aTitleXpath, title);
		wd.findElement(By.xpath(resultContextXpath)).findElement(By.xpath(xpath)).click();
		return new ResultDetails(wd);
	}

	public String getTitle() {
		return title;
	}
}
