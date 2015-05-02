package io.goodguys.tugoffline

/**
 * Created by wonko on 2015-04-23.
 */





object Main extends App {

  import io.goodguys.tugoffline.Macros._

  println("" + W + U + R + G)


//  def replaceManaSymbols(text: String): String = {
//    if (text.matches( """<img.+alt="(.+)".*>""")) {
//      val replacedMana = text.replaceAll( """<img.+alt="(.+)".*>""", "[" + Mana.AltTextToMana("$1").toString + "]")
//      val contractedMana = text.replaceAll("][", "")
//      contractedMana
//    }
//    else text
//  }
//
//  val t0 = """donst' match"""
//  val t1 = """Unearth <img align="absbottom" alt="5" src="/Handlers/Image.ashx?size=small&amp;name=5&amp;type=symbol"><img align="absbottom" alt="Black" src="/Handlers/Image.ashx?size=small&amp;name=B&amp;type=symbol"><img align="absbottom" alt="Red" src="/Handlers/Image.ashx?size=small&amp;name=R&amp;type=symbol"> <i>(<img align="absbottom" alt="5" src="/Handlers/Image.ashx?size=small&amp;name=5&amp;type=symbol"><img align="absbottom" alt="Black" src="/Handlers/Image.ashx?size=small&amp;name=B&amp;type=symbol"><img align="absbottom" alt="Red" src="/Handlers/Image.ashx?size=small&amp;name=R&amp;type=symbol">: Return this card from your graveyard to the battlefield. It gains haste. Exile it at the beginning of the next end step or if it would leave the battlefield. Unearth only as a sorcery.)</i>"""
////  val matchMana = """<img.+alt="(.+)".*>""".r
////  val matchMana(mana0) = t0
////  val matchMana(mana1) = t1
////  println(mana0)
////  println(mana1)
//
//  val doc = Jsoup.parse(t1)
//  val images = doc.getElementsByTag("img")
//
//  def printImages(i: Int): Unit = {
//    if (i >= images.size()) {}
//    else {
//      println(Mana.AltTextToMana(images.get(i).attr("alt")))
//      printImages(i + 1)
//    }
//  }
//
//  printImages(0)




//  System.setProperty("webdriver.chrome.driver", "/home/wonko/Scala/Tools/ChromeDriver/chromedriver") //"/usr/bin/chromium-browser")
//
//  val driver = new ChromeDriver()
//  val driverWait = new WebDriverWait(driver, 30)
//
//  val baseUri = "http://gatherer.wizards.com/"
//
//  val startPageUri = "Pages/Default.aspx"
//
//  driver.get(baseUri + startPageUri)
//

}


