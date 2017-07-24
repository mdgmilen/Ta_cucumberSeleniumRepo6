package org.mileng.ta_sample1.pages;

import org.mileng.ta_sample1.framework.WebDriverEx;
import org.openqa.selenium.WebDriver;

public class PageBase extends WebDriverEx {
	public PageBase(WebDriver wd) {
		this.wd = wd;
	}
}
