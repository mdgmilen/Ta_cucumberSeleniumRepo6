package org.mileng.ta_sample1.pages;

import org.mileng.ta_sample1.framework.SyncEx;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ResultDetails extends PageBase {
	public ResultDetails(final WebDriver wd) {
		super(wd);
		PageFactory.initElements(wd, this);
		// https://www.amazon.co.uk/Harry-Potter-Cursed-Child-Playscript/dp/0751565369/ref=sr_1_1_det?ie=UTF8&qid=1500882102&sr=8-1#productPromotions
		// https://www.amazon.co.uk/Harry-Potter-Cursed-Child-Playscript/dp/0751565369/ref=sr_1_1?ie=UTF8&qid=1500912397&sr=8-1&keywords=Harry+Potter+and+the+Cursed+Child+-+Parts+One+and+Two
		final String expectedPageNamePart = "ref=sr_";
		Assert.assertTrue(SyncEx.wait(() -> wd.getCurrentUrl().contains(expectedPageNamePart)),
				String.format("We are not on the ResultDetails '%s' page", expectedPageNamePart));
	}

	private String printType;

	private static final String rdParentXpath = "//h1[@id='title']/../../..";
	private static final String titleXpath = "//span[contains(text(), '%s')]";
	// "//span[contains(text(), 'Paperback')]/../../a[@role='button']"
	private static final String parentAXpath = "//span[contains(text(), '%s')]/../../a[@role='button']";
	// private static final String badgeSpanTextXpath = "//span[text()='%s']";
	// private static final String printTypeXpath = "//span[text()='%s']";
	private static final String priceXpath = "//span[contains(text(), '%s')]";

	@FindBy(xpath = "//input[@id='add-to-cart-button']")
	private WebElement preorderThisItemTodayButton;

	public boolean verifyTitle(String parTitle) {
		String xpath = String.format(titleXpath, parTitle);
		boolean foundUsingParent = existsWebElementUnique(null, rdParentXpath + xpath);
		return foundUsingParent;
		// //h1[@id='title']/../../..//span[contains(text(), 'Harry Potter and the
		// Cursed Child - Parts One and Two')]
	}

	public boolean verifyPrintType(String parPrintType) {
		String xpath = String.format(parentAXpath, parPrintType);
		this.printType = parPrintType;
		boolean foundUsingParent = existsWebElementUnique(rdParentXpath, xpath);
		return foundUsingParent;
	}

	public boolean verifyPrice(String parPrice) {
		String xpath = String.format(priceXpath, parPrice);
		String parentAReadyXpath = String.format(parentAXpath, this.printType);
		boolean foundUsingParent = existsWebElementUnique(null, rdParentXpath + parentAReadyXpath + xpath);
		return foundUsingParent;
		// //h1[@id='title']/../../..//span[contains(text(),
		// 'Paperback')]/../../a[@role='button']//span[text()='£5.99']
	}

	public AmazonBasket addResultToBasket() {
		preorderThisItemTodayButton.click();
		return new AmazonBasket(wd);
	}
}
