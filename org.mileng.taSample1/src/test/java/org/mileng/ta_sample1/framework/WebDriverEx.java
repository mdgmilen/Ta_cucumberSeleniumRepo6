package org.mileng.ta_sample1.framework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebDriverEx {

	public WebDriver wd;
	protected final static int IMPLICIT_WAIT_TIMEOUT = 20;
	
	public boolean existsWebElementUnique(String parentXpath, String xpath) {
		List<WebElement> list;
		if (parentXpath == null) {
			list = wd.findElements(By.xpath(xpath));
//			System.out.println("is null");
		} else {
			list = wd.findElement(By.xpath(parentXpath)).findElements(By.xpath(xpath));
//			System.out.println("is not null");
		}
		return list.size() == 1;
	}
	
	public boolean existsWebElement(String parentXpath, String xpath) {
		List<WebElement> list;
		if (parentXpath == null) {
			list = wd.findElements(By.xpath(xpath));
		} else {
			list = wd.findElement(By.xpath(parentXpath)).findElements(By.xpath(xpath));
		}
		return list.size() > 0;
	}
}
