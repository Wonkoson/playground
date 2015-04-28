package io.goodguys.tugoffline

import com.mongodb.casbah.MongoClient

/**
 * Created by wonko on 2015-04-23.
 */





object Main extends App {

  //  val white = W //OneColor(White())
  //  white.draw()
  val all = Mana(White() :: Blue() :: Black() :: Red() :: Green() :: Colorless() :: Nothing())

  val nr = Mana(Nothing() :: Red())

  val c = Mana(Colorless())

  val u = Mana(Blue())

  val ur = Mana(Blue() :: Red())

  val G = Green()

  val mongoClient = MongoClient("tugoffline")


  println("ur pays  u: " + (ur pays  u))
  println("u pays ur: " + (u pays ur))
  println("nr pays  all: " + (nr pays  all))
  println("all pays nr: " + (all pays nr))
  println("all pays G: " + (all pays G))
  println("all pays ur: " + (all pays ur))

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
