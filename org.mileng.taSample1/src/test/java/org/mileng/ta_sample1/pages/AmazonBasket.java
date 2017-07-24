package org.mileng.ta_sample1.pages;

import org.mileng.ta_sample1.framework.SyncEx;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AmazonBasket extends PageBase {

	public AmazonBasket(final WebDriver wd) {
		super(wd);
		PageFactory.initElements(wd, this);
		// https://www.amazon.co.uk/gp/huc/view.html?ie=UTF8&increasedItems=C86a50686-7495-43f8-b451-5a33278c8893&newItems=C86a50686-7495-43f8-b451-5a33278c8893%2C1
		// https://www.amazon.co.uk/Harry-Potter-Cursed-Child-Playscript/dp/0751565369/ref=sr_1_1_det?ie=UTF8&qid=1500882102&sr=8-1#productPromotions
		// https://www.amazon.co.uk/Harry-Potter-Cursed-Child-Playscript/dp/0751565369/ref=sr_1_1?ie=UTF8&qid=1500912397&sr=8-1&keywords=Harry+Potter+and+the+Cursed+Child+-+Parts+One+and+Two
		final String expectedPageNamePart = "gp/huc/view.html";
		Assert.assertTrue(SyncEx.wait(() -> wd.getCurrentUrl().contains(expectedPageNamePart)),
				String.format("We are not on the AmazonBasket '%s' page", expectedPageNamePart));
	}
	
	private static final String notificationXpath = "//h1[contains(text(), '%s')]";
	//Added to Basket
	private static final String itemsTextXpath = "//span[contains(text(), '%s')]";
	// 1 item, 2 items

	@FindBy(xpath = "//a[contains(text(), 'Edit basket')]")
	private WebElement editBasketButton;
	
	public boolean verifyNotification(String message) {
		String xpath = String.format(notificationXpath, message);
		return existsWebElementUnique(null, xpath);
	}
	
	public boolean verifyItemsNumberByText(String itemsTextInclCount) {
		String xpath = String.format(itemsTextXpath, itemsTextInclCount);
		return existsWebElement(null, xpath);
	}
	
	public EditBasket editBasket() {
		editBasketButton.click();
		return new EditBasket(wd);
	}
}
