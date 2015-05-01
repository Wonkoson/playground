package io.goodguys.tugoffline

import org.jsoup.Jsoup

/**
 * Created by wonko on 2015-04-23.
 */





object Main extends App {

//  //  val white = W //OneColor(White())
//  //  white.draw()
//  val all = Mana(White() :: Blue() :: Black() :: Red() :: Green() :: OneColorless() :: Nothing())
//
//  val nr = Mana(Nothing() :: Red())
//
//  val c = Mana(OneColorless())
//
//  val u = Mana(Blue())
//
//  val ur = Mana(Blue() :: Red())
//
//  val G = Green()
//
//
//  val specialChars = """+a/b?c.d\u1234e"""
//
//  println(specialChars)
//
//  val sanitized = specialChars.replaceAll("[^0-9a-zA-Z-]+", "")
//
//  def parsePowerToughness(text: String): Int = {
//    val t = text.trim
//    if (t.matches("^\\d+$")) t.toInt
//    else 0
//  }
//
//  val a = parsePowerToughness("1234")
//  val b = parsePowerToughness("021")
//  val d = parsePowerToughness("123v")
//
//  println(s"a:$a, b:$b, d:$d")
//
//  println(sanitized)
//
//
//  println(Mana.parseMana("Two or Green"))
//  println(Mana.parseMana("Black or Green"))
//  println(Mana.parseMana("15"))


  def replaceManaSymbols(text: String): String = {
    if (text.matches( """<img.+alt="(.+)".*>""")) {
      val replacedMana = text.replaceAll( """<img.+alt="(.+)".*>""", "[" + Mana.AltTextToMana("$1").toString + "]")
      val contractedMana = text.replaceAll("][", "")
      contractedMana
    }
    else text
  }

  val t0 = """donst' match"""
  val t1 = """Unearth <img align="absbottom" alt="5" src="/Handlers/Image.ashx?size=small&amp;name=5&amp;type=symbol"><img align="absbottom" alt="Black" src="/Handlers/Image.ashx?size=small&amp;name=B&amp;type=symbol"><img align="absbottom" alt="Red" src="/Handlers/Image.ashx?size=small&amp;name=R&amp;type=symbol"> <i>(<img align="absbottom" alt="5" src="/Handlers/Image.ashx?size=small&amp;name=5&amp;type=symbol"><img align="absbottom" alt="Black" src="/Handlers/Image.ashx?size=small&amp;name=B&amp;type=symbol"><img align="absbottom" alt="Red" src="/Handlers/Image.ashx?size=small&amp;name=R&amp;type=symbol">: Return this card from your graveyard to the battlefield. It gains haste. Exile it at the beginning of the next end step or if it would leave the battlefield. Unearth only as a sorcery.)</i>"""
//  val matchMana = """<img.+alt="(.+)".*>""".r
//  val matchMana(mana0) = t0
//  val matchMana(mana1) = t1
//  println(mana0)
//  println(mana1)

  val doc = Jsoup.parse(t1)
  val images = doc.getElementsByTag("img")

  def printImages(i: Int): Unit = {
    if (i >= images.size()) {}
    else {
      println(Mana.AltTextToMana(images.get(i).attr("alt")))
      printImages(i + 1)
    }
  }

  printImages(0)




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


//  println("ur pays  u: " + (ur pays  u))
//  println("u pays ur: " + (u pays ur))
//  println("nr pays  all: " + (nr pays  all))
//  println("all pays nr: " + (all pays nr))
//  println("all pays G: " + (all pays G))
//  println("all pays ur: " + (all pays ur))

  //  println(W)
  //  println(W + "" + U + B + R + G + C + N)
}



//class Something {
//
//  val driver = new FirefoxDriver
//  val baseUrl = "https://online.tugraz.at/"
//  driver.manage.timeouts.implicitlyWait(30, TimeUnit.SECONDS)
//
//  case class Lecture(id: String, name: String, lecturers: List[String])
//
//  def retrieveLectures(): List[Lecture] = {
//    def login = ???
//    def numLectures = ???
//    def grabLecture(id: String) = ???
//
//    login
//
//
//    //    def loopInstitute(maxIndex) = {
//    //
//    //    }
//
//    // Rewrite to functional style example with loopLectures
//    //    val n = numLectures
//    //    val lectures = List[Lecture]()
//    //    for (i <- 1 to n) {
//    //      lectures ++= grabLecture(i)    // !!! reassignment to val
//    //    }
//    // @see http://seleniumexamples.com/blog/examples/meetup-com-attendance-lists/
//    def grabLectures(links: List[String]): List[Lecture] = {
//      def loop(l: List[String], r: List[Lecture]): List[Lecture] = {
//        if (l.isEmpty)
//          r
//        else {
//          driver.get(l.head)
//          val id = driver.findElement(By.id("1")).getText
//          val name = driver.findElement(By.xpath("1")).getText
//          val lecturers = List(driver.findElement(By.id("1")).getText)
//          loop(l.tail, r ++ List(new Lecture(id, name, lecturers)))
//        }
//      }
//
//      List[Lecture]()
//    }
//
//    List[Lecture]()
//  }
//
//}
