package io.goodguys.tugoffline

import org.jsoup.Jsoup
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.Select

//import org.scalatest.{FlatSpec, Matchers}

/**
 * Created by wonko on 2015-04-24.
 */
//class GathererHarvester extends FlatSpec with Matchers {
//
//  val driver = new FirefoxDriver()
//  val baseUrl = ""
//
//  "A Gatherer Website" should "Give me all it's cards" in {
//
//  }
//
//
//}

object GathererHarvester extends App {

//  val driver = new FirefoxDriver()

  System.setProperty("webdriver.chrome.driver", "/home/wonko/Scala/Tools/ChromeDriver/chromedriver") //"/usr/bin/chromium-browser")

  val driver = new ChromeDriver()

  val baseUri = "http://gatherer.wizards.com/"

  val startPageUri = "Pages/Default.aspx"

  driver.get(baseUri + startPageUri)
  val startPageSrc = driver.getPageSource

  val startPageDoc = Jsoup.parse(startPageSrc, baseUri + startPageUri)

  val dropDowns = startPageDoc.getElementsByTag("select")
  val cardSetDropDown = dropDowns.get(1)
  val cardSetOptions = cardSetDropDown.getElementsByTag("option")

  def getSets(): List[String] = {
    def loop(i: Int, l: List[String]): List[String] = {
      if (i >= cardSetOptions.size())
        l
      else {
        val entry = cardSetOptions.get(i).attr("value")
        if (entry.isEmpty)
          loop(i + 1, l)
        else
          loop(i + 1, l ++ List(entry))
      }
    }
    loop(0, Nil)
  }

  val sets = getSets()


  // Issue Search by Set

  val set = sets(0)

  driver.findElement(By.id("ctl00_ctl00_MainContent_Content_SearchControls_setAddText")).click
  new Select(driver.findElement(By.id("ctl00_ctl00_MainContent_Content_SearchControls_setAddText"))).selectByVisibleText(set)
  driver.findElement(By.id("ctl00_ctl00_MainContent_Content_SearchControls_searchSubmitButton")).click

  def getLinks = driver.findElementsByXPath("//table[@class=\"cardItemTable\"]//td[@class=\"leftCol\"]/a")

  def loop(i: Int): Unit = {
    val links = getLinks
    println("links.size = " + links.size)

    def getInfo = {
      driver.findElementByXPath("//")
    }

    if (i < links.size) {
      links.get(i).click()

      val (card) = getInfo

      driver.navigate().back()

      loop(i + 1)
    }
  }

  loop(0)

//  val cardLinks = driver.findElementsByXPath("""//table[@class="cardItemTable"]//td[@class="leftCol"]/a""")

  // get details of cards
//  for (i <- 0 until 2) { // cardLinks.size()) {
//
//    cardLinks.get(i).click()
//
//    val source = driver.getPageSource
//
//    println(i + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
//    println(source)
//    println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
//
//    driver.navigate().back()
//  }

}