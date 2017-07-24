package org.mileng.ta_sample1.pages;

import org.mileng.ta_sample1.framework.SyncEx;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class EditBasket extends PageBase {

	public EditBasket(final WebDriver wd) {
		super(wd);
		PageFactory.initElements(wd, this);
		// https://www.amazon.co.uk/gp/cart/view.html/ref=lh_cart_dup
		// https://www.amazon.co.uk/gp/huc/view.html?ie=UTF8&increasedItems=C86a50686-7495-43f8-b451-5a33278c8893&newItems=C86a50686-7495-43f8-b451-5a33278c8893%2C1
		// https://www.amazon.co.uk/Harry-Potter-Cursed-Child-Playscript/dp/0751565369/ref=sr_1_1_det?ie=UTF8&qid=1500882102&sr=8-1#productPromotions
		// https://www.amazon.co.uk/Harry-Potter-Cursed-Child-Playscript/dp/0751565369/ref=sr_1_1?ie=UTF8&qid=1500912397&sr=8-1&keywords=Harry+Potter+and+the+Cursed+Child+-+Parts+One+and+Two
		final String expectedPageNamePart = "gp/cart/view.html";
		Assert.assertTrue(SyncEx.wait(() -> wd.getCurrentUrl().contains(expectedPageNamePart)),
				String.format("We are not on the EditBasket '%s' page", expectedPageNamePart));
	}

	private static final String titleXpath = "//span[@class='a-list-item']//span[contains(text(), '%s')]";
	private static final String typeXpath = "//span[@class='a-list-item']//span[contains(text(), '%s')]";
	private static final String quantityXpath = "//span[@class='a-dropdown-prompt'][contains(text(), '%s')]";
	private static final String priceXpath = "//span[contains(text(), '%s')]";
	private static final String totalPriceXpath = "//span[contains(text(), '%s')]";

	public boolean verifyTitle(String title) {
		String xpath = String.format(titleXpath, title);
		return existsWebElement(null, xpath);
	}
	
	public boolean verifySelectedPrintType(String type) {
		String xpath = String.format(typeXpath, type);
		return existsWebElement(null, xpath);
	}
	
	public boolean verifyQuantity(String count) {
		String xpath = String.format(quantityXpath, count);
		return existsWebElementUnique(null, xpath);
	}
	
	public boolean verifyPrice(String price) {
		//TODo The following and the totalPrice locators should be more specific
		//  and using existsWebElementUnique()
		String xpath = String.format(priceXpath, price);
		return existsWebElement(null, xpath);
	}
	
	public boolean verifyTotalPrice(String totalPrice) {
		String xpath = String.format(totalPriceXpath, totalPrice);
		return existsWebElement(null, xpath);
	}
}
