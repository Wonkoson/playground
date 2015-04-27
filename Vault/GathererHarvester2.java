package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class GathererHarvester2 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://gatherer.wizards.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testGathererHarvester2() throws Exception {
    driver.get(baseUrl + "/Pages/Default.aspx");
    driver.findElement(By.id("ctl00_ctl00_MainContent_Content_SearchControls_setAddText")).click();
    new Select(driver.findElement(By.id("ctl00_ctl00_MainContent_Content_SearchControls_setAddText"))).selectByVisibleText("Alara Reborn");
    driver.findElement(By.id("ctl00_ctl00_MainContent_Content_SearchControls_searchSubmitButton")).click();
    driver.findElement(By.id("ctl00_ctl00_ctl00_MainContent_SubContent_SubContent_ctl00_listRepeater_ctl00_cardImage")).click();
    driver.findElement(By.cssSelector("#ctl00_ctl00_ctl00_MainContent_SubContent_SubContentAnchors_DetailsAnchors_DiscussionLink > span")).click();
    driver.findElement(By.linkText(">")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
