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

public class ScrapeLecturesByInstitueWebdriverBacked {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "https://online.tugraz.at/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testScrapeLecturesByInstitueWebdriverBacked() throws Exception {
		selenium.open("/tug_online/webnav.ini");
		selenium.selectFrame("menue");
		selenium.click("name=key");
		selenium.selectWindow("name=detail");
		selenium.type("id=puMask7684740179", "0pen$esame");
		selenium.type("id=puMask7684740177", "wonko");
		selenium.click("id=puMask7684740183");
		selenium.waitForPageToLoad("30000");
		selenium.selectWindow("name=menue");
		selenium.click("link=Jakob Stückler");
		selenium.selectWindow("name=detail");
		selenium.click("link=LV-An/Abmeldung");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=LV-Anmeldung");
		selenium.waitForPopUp("ANMELD", "30000");
		selenium.selectWindow("name=ANMELD");
		selenium.type("name=clvbez", "*");
		selenium.select("name=cinst", "label=1000 Fakultät für Architektur");
		selenium.click("id=btnSearch");
		selenium.waitForPageToLoad("30000");
		selenium.select("name=cinst", "label=1430 Institut für Architekturtheorie, Kunst- und Kulturwisse");
		selenium.click("id=btnSearch");
		selenium.waitForPageToLoad("30000");
		selenium.select("name=cinst", "label=4390 Institut für Elektronik");
		selenium.click("id=btnSearch");
		selenium.waitForPageToLoad("30000");
		selenium.click("xpath=(//img[@alt='eine Seite weiter'])[2]");
		selenium.waitForPageToLoad("30000");
		selenium.selectWindow("name=menue");
		selenium.click("name=keyAn");
		selenium.waitForPageToLoad("30000");
		selenium.selectWindow("name=ANMELD");
		selenium.click("//tbody[@id='pulist7684747566']/tr[4]/td[3]/a/span");
		selenium.waitForPopUp("lvdetail", "30000");
		selenium.selectWindow("null");
		selenium.click("css=div.hitarea.expandable-hitarea");
		selenium.click("css=div.hitarea.collapsable-hitarea");
		selenium.click("css=#sizzle-1429790311783 > div.hitarea.expandable-hitarea");
		selenium.click("link=Recording");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
