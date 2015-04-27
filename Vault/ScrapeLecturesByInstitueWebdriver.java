package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ScrapeLecturesByInstitueWebdriver {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://online.tugraz.at/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testScrapeLecturesByInstitueWebdriver() throws Exception {
    driver.get(baseUrl + "/tug_online/webnav.ini");
    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | menue | ]]
    driver.findElement(By.name("key")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=detail | ]]
    driver.findElement(By.id("puMask7684740179")).clear();
    driver.findElement(By.id("puMask7684740179")).sendKeys("0pen$esame");
    driver.findElement(By.id("puMask7684740177")).clear();
    driver.findElement(By.id("puMask7684740177")).sendKeys("wonko");
    driver.findElement(By.id("puMask7684740183")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=menue | ]]
    driver.findElement(By.linkText("Jakob Stückler")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=detail | ]]
    driver.findElement(By.linkText("LV-An/Abmeldung")).click();
    driver.findElement(By.linkText("LV-Anmeldung")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | ANMELD | 30000]]
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=ANMELD | ]]
    driver.findElement(By.name("clvbez")).clear();
    driver.findElement(By.name("clvbez")).sendKeys("*");
    new Select(driver.findElement(By.name("cinst"))).selectByVisibleText("1000 Fakultät für Architektur");
    driver.findElement(By.id("btnSearch")).click();
    new Select(driver.findElement(By.name("cinst"))).selectByVisibleText("1430 Institut für Architekturtheorie, Kunst- und Kulturwisse");
    driver.findElement(By.id("btnSearch")).click();
    new Select(driver.findElement(By.name("cinst"))).selectByVisibleText("4390 Institut für Elektronik");
    driver.findElement(By.id("btnSearch")).click();
    driver.findElement(By.xpath("(//img[@alt='eine Seite weiter'])[2]")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=menue | ]]
    driver.findElement(By.name("keyAn")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=ANMELD | ]]
    driver.findElement(By.xpath("//tbody[@id='pulist7684747566']/tr[4]/td[3]/a/span")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | lvdetail | 30000]]
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
    driver.findElement(By.cssSelector("div.hitarea.expandable-hitarea")).click();
    driver.findElement(By.cssSelector("div.hitarea.collapsable-hitarea")).click();
    driver.findElement(By.cssSelector("#sizzle-1429790311783 > div.hitarea.expandable-hitarea")).click();
    driver.findElement(By.linkText("Recording")).click();
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
