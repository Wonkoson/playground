package com.example.tests;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;
import static org.apache.commons.lang3.StringUtils.join;

public class GathererHarvester {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://gatherer.wizards.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testGathererHarvester() throws Exception {
		selenium.open("/Pages/Default.aspx");
		selenium.click("id=ctl00_ctl00_MainContent_Content_SearchControls_setAddText");
		selenium.select("id=ctl00_ctl00_MainContent_Content_SearchControls_setAddText", "label=Alara Reborn");
		selenium.click("id=ctl00_ctl00_MainContent_Content_SearchControls_searchSubmitButton");
		selenium.waitForPageToLoad("30000");
		selenium.click("xpath=(//a[contains(text(),'>')])[2]");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=#ctl00_ctl00_ctl00_MainContent_SubContent_bottomPagingControlsContainer > div.pagingcontrols");
		selenium.click("id=ctl00_ctl00_ctl00_MainContent_SubContent_SubContent_ctl00_listRepeater_ctl44_cardImage");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
