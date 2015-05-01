package io.goodguys.tugoffline

import java.io.File
import java.net.URL
import javax.imageio.ImageIO

import org.jsoup.Jsoup
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.{Select, WebDriverWait}
import org.openqa.selenium.{By, WebElement}

import scala.annotation.tailrec
import scala.collection.JavaConverters._

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
  val driverWait = new WebDriverWait(driver, 30)

  val baseUri = "http://gatherer.wizards.com/"

  val startPageUri = "Pages/Default.aspx"

  driver.get(baseUri + startPageUri)
  val startPageSrc = driver.getPageSource

  val startPageDoc = Jsoup.parse(startPageSrc, baseUri + startPageUri)

  val dropDowns = startPageDoc.getElementsByTag("select")
  val cardSetDropDown = dropDowns.get(1)
  val cardSetOptions = cardSetDropDown.getElementsByTag("option")

  def getExpansions: List[String] = {
    @tailrec
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

  val expansions = getExpansions


  // Issue Search by Set

  def loopExpansions(expansions: List[String]) {

    driver.get(baseUri + startPageUri)

    if (expansions != Nil) {
      val expansion = expansions.head
      println(s"Getting Cards for $expansion")

      driver.findElement(By.id("ctl00_ctl00_MainContent_Content_SearchControls_setAddText")).click()
      new Select(driver.findElement(By.id("ctl00_ctl00_MainContent_Content_SearchControls_setAddText"))).selectByVisibleText(expansion)
      driver.findElement(By.id("ctl00_ctl00_MainContent_Content_SearchControls_searchSubmitButton")).click()


      // Get card details
      def cardDetailLoop(i: Int, retry: Int, expansion: String, escapedExpansion: String): Unit = {

        def getRowValue(key: String, cells: List[WebElement]): Option[WebElement] = {
          @tailrec
          def loop(i: Int, takeThis: Boolean, cells: List[WebElement]): Option[WebElement] = {
            if (i >= cells.size)
              None
            else {
              if (takeThis)
                Some(cells(i))
              else {
                //            println("'" + cells(i).getText.trim() + "' == '" + key + "'")
                if (cells(i).getText.trim().matches(key))
                  loop(i + 1, true, cells)
                else
                  loop(i + 1, false, cells)
              }
            }
          }
          loop(0, false, cells)
        }

        def getRowValues(key: String, tagName: String, cells: List[WebElement]): List[WebElement] = {
          @tailrec
          def loop(i: Int, takeThis: Boolean, cells: List[WebElement]): List[WebElement] = {
            if (i >= cells.size)
              List()
            else {
              if (takeThis) {
                //            val html = cells(i).getText
                //            println(html)
                cells(i).findElements(By.tagName(tagName)).asScala.toList
              }
              else {
                //            println("'" + cells(i).getText.trim() + "' == '" + key + "'")
                if (cells(i).getText.trim().matches(key))
                  loop(i + 1, true, cells)
                else
                  loop(i + 1, false, cells)
              }
            }
          }
          loop(0, false, cells)
        }

        def getName(cells: List[WebElement]): String = getRowValue(MagicCard.CardNameKey, cells).getOrElse(EmptyWebElement).getText.trim

        def getCastingCost(cells: List[WebElement]): ManaList = {
          val elems = getRowValues(MagicCard.CastingCostKey, "img", cells)
          val mana: List[Mana] = for (e <- elems) yield Mana.AltTextToMana(e.getAttribute("alt"))
          mana
        }

        // @return (cardTypes: List[String, subTypes: [List[String]]])
        def getTypes(cells: List[WebElement]): (List[String], List[String]) = {
          val text = getRowValue(MagicCard.CardTypesKey, cells).getOrElse(EmptyWebElement).getText
          // \u2014 == &mdash;
          val types = text.split("\u2014").map(_.trim).map(_.replaceAll(" +", " "))
          val cardTypes = types(0).split(" ").toList
          val subTypes = if (types.length > 1) types(1).split(" ").toList else List[String]()
          (cardTypes, subTypes)
        }


        def getMultiVerseId(url: String): Int = {
          @tailrec
          def idFromPart(parts: List[String]): Int = {
            parts match {
              case Nil => -1
              case x :: xs =>
                if (x.startsWith("multiverseid"))
                  x.split("=")(1).toInt
                else
                  idFromPart(xs)
            }
          }
          val parts = url.split('?').toList
          idFromPart(parts)
        }

        // TODO: Replace with <div class="1"/><div class="U/W">...
//        def replaceManaSymbols(text: String): String = {
//          if (text.matches( """<img.+alt="(.+)".*>""")) {
//            val replacedMana = text.replaceAll( """<img.+alt="(.+)".*>""", "[" + Mana.AltTextToMana("$1").toString + "]")
//            val contractedMana = text.replaceAll("][", "")
//            contractedMana
//          }
//          else text
//        }

        def replaceManaSymbols(text: String): String = {
          val doc = Jsoup.parse(text)
          val images = doc.getElementsByTag("img")
          val numImages = images.size()

          def replacements(i: Int, l: List[String]): List[String] = {
            if (i >= images.size()) l
            else {
              val repl = Mana.AltTextToMana(images.get(i).attr("alt")).toString
              replacements(i + 1, l ++ List(repl))
            }
          }

          def replace(repl: List[String], res: String): String = {
            repl match {
              case Nil => res
              case x :: xs => replace(repl.tail, res.replaceFirst("""<img.+?>""", x))
            }
          }

          val repl = replacements(0, Nil)
          replace(repl, text)
        }

        def getCardText(elems: List[WebElement]): List[String] = {
          def loop(i: Int, l: List[String]): List[String] = {
            if (i >= elems.size)
              l
            else {
//              val elementText = elems(i).getText
//              println(s"elementText:$elementText")
              val text = replaceManaSymbols(elems(i).getAttribute("innerHTML"))
              loop(i + 1, l ++ List(text))
            }
          }
          loop(0, Nil)
        }

        def getFlavorText(elems: List[WebElement]): List[String] = {
          def loop(i: Int, l: List[String]): List[String] = {
            if (i >= elems.size)
              l
            else
              loop(i + 1, l ++ List(elems(i).getAttribute("innerHTML")))
          }
          loop(0, Nil)
        }

        def parsePowerToughness(text: String): (Option[Int], Option[Int]) = {
          def parseStrength(text: String): Option[Int] = {
            def parseFormula(text: String): Option[Int] = {
              text match {
                case "*" => Some(-40)
                case "1 + *" => Some(-1)
                case "2 + *" => Some(-2)
              }
            }
            val t = text.trim
            if (t.matches("^\\d+$")) Some(t.toInt) else parseFormula(t)
          }
          val split = text.split("/")
          (parseStrength(split(0)), parseStrength(split(1)))
        }

        //  def getLinks = driver.findElementsByXPath("//table[@class=\"cardItemTable\"]//td[@class=\"leftCol\"]/a")
        def getLinks = driver.findElementsByXPath("//td[@class=\"leftCol\"]/a")

        //    val html = driver.getPageSource

        val links = getLinks
        //    println("links.size = " + links.size)

        if (i < links.size()) {
          links.get(i).click()
          // need to wait here @see http://stackoverflow.com/questions/7102931/webdriver-explicit-wait-in-scala
          //      val readyState: Boolean = driver.executeScript("return document.readyState == 'complete'").asInstanceOf[Boolean]

          // @see http://stackoverflow.com/questions/8808921/selecting-a-css-class-with-xpath
          val tableCells = driver.findElementsByXPath( """//div[@class="smallGreyMono"]/div[contains(concat(" ", normalize-space(@class), " "), " row ")]//div""").asScala.toList

          val multiVerseId = getMultiVerseId(driver.getCurrentUrl)
          val cardNumber = i + 1
          val name = getName(tableCells)
          val castingCost: ManaList = getCastingCost(tableCells)
          val convertedManaCost = getRowValue(MagicCard.ConvertedManaCostKey, tableCells).getOrElse(EmptyWebElement).getText.toInt
          val (mainTypes, subTypes) = getTypes(tableCells)
          val cardText = getCardText(driver.findElementsByXPath("""//div[@class="cardtextbox"]""").asScala.toList)
          val flavorText = getFlavorText(driver.findElementsByXPath( """//div[@class="flavortextbox"]""").asScala.toList)
          val (power: Option[Int], toughness: Option[Int]) = if (mainTypes.contains("Creature")) {
            parsePowerToughness(getRowValue(MagicCard.PowerToughnessKey, tableCells).getOrElse(EmptyWebElement).getText)
          } else {
            (None, None)
          }
          val rarity = getRowValue(MagicCard.RarityKey, tableCells).getOrElse(EmptyWebElement).getText
          val artist = getRowValue(MagicCard.ArtistKey, tableCells).getOrElse(EmptyWebElement).getText

          val imageUrl = new URL(driver.findElementByXPath( """//div[@class="cardImage"]/img""").getAttribute("src"))
          val image = ImageIO.read(imageUrl)
          val imageFileName = name.replaceAll("[^0-9a-zA-Z-]+", "_")
          val storagePath = "/home/wonko/Scala/Projects/TugOffline/data/images/" + escapedExpansion + "/" + imageFileName + ".jpg"
          val file = new File(storagePath)
          file.mkdirs()
          ImageIO.write(image, "jpg", file)

          println(s"Card(id:$multiVerseId, #:$cardNumber, n:$name, cc:$castingCost, cmc:$convertedManaCost, t:$mainTypes--$subTypes, i:$imageFileName, ct:$cardText, ft:$flavorText, p/t:$power/$toughness, e:$expansion, r:$rarity, a:$artist")

          driver.navigate().back()

          cardDetailLoop(i + 1, 0, expansion, escapedExpansion)
        }
        else {
          if (retry < 3) {
            println("############################################# retry")
            cardDetailLoop(i, retry + 1, expansion, escapedExpansion)
          }
          else {
            val pagingControls = driver.findElementsByXPath("""//div[@class="pagingcontrols"]/a""")
            val last = pagingControls.get(pagingControls.size() - 1)
            if (last.getText.trim == ">") {
              last.click()
              cardDetailLoop(i + 1, 0, expansion, escapedExpansion)
              driver.navigate().back()
            }
          }
        }

      }

      val escapedExpansion = expansion.replaceAll("[^0-9a-zA-Z-]+", "_")
      cardDetailLoop(0, 0, expansion, escapedExpansion)
    }

    expansions match {
      case Nil => println("Finished all Expansions")
      case x :: xs => {
        println(); println(); println("Continuing with next Expansion")
        driver.navigate().back()
        loopExpansions(xs)
      }
    }
  }

  loopExpansions(expansions)

//    def getInfo: List[AnyRef] = {
//
//      def getAttribyteWithName(rows: List[WebElement]): Option[CardAttribute] = {
//
////        def parse(name: String, text: String): CardAttribute = {
////          match name {
////            case "Card Name" => Name(text)
////            case "Mana Cost" => CastingCost(Mana.parse(text))
////          }
////        }
//
//        def createFromText(key: String, name: String, rows: List[WebElement]): Option[CardAttribute] = {
//          rows match {
//            case Nil => None
//            case x :: xs => {
//              if (x.getText.contains(key)) {
//                if (xs == Nil)
//                  None
//                else {
////                  match
//                  None
//                }
//              }
//              else {
//                createFromText(key, name, xs)
//              }
//            }
//
//          }
//        }
//      }
//
//      val rows = driver.findElementsByXPath("//table[@class=\"cardDetails\"]//div[@class=\"row\"]")
//
////      getElementWithName(rows)
//
////      val name =
//
//    }

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