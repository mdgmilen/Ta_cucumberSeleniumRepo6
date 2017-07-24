package org.mileng.ta_sample1.pages;

import org.mileng.ta_sample1.framework.SyncEx;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AmazonHome extends PageBase {

	public AmazonHome(final WebDriver wd) {
		super(wd);
		PageFactory.initElements(wd, this);
		// https://www.amazon.co.uk/
		final String expectedPageNamePart = "amazon.co.uk";
		Assert.assertTrue(SyncEx.wait(() -> wd.getCurrentUrl().contains(expectedPageNamePart)),
				String.format("We are not on the '%s' page", expectedPageNamePart));
	}

	@FindBy(xpath = "//input[@type='text']")
	private WebElement searchInput;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement searchButton;

	public SearchResults searchFor(String textToSearchFor) {
		searchInput.sendKeys(textToSearchFor);
		searchButton.click();
		return new SearchResults(wd);
	}
}
