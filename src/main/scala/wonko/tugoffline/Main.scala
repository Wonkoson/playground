package wonko.tugoffline

import java.util.concurrent.TimeUnit

import org.openqa.selenium.By
import org.openqa.selenium.firefox.FirefoxDriver

/**
 * Created by wonko on 2015-04-23.
 */
class Something {

  val driver = new FirefoxDriver
  val baseUrl = "https://online.tugraz.at/"
  driver.manage.timeouts.implicitlyWait(30, TimeUnit.SECONDS)

  case class Lecture(id: String, name: String, lecturers: List[String])

  def retrieveLectures(): List[Lecture] = {
    def login = ???
    def numLectures = ???
    def grabLecture(id: String) = ???

    login


    //    def loopInstitute(maxIndex) = {
    //
    //    }

    // Rewrite to functional style example with loopLectures
    //    val n = numLectures
    //    val lectures = List[Lecture]()
    //    for (i <- 1 to n) {
    //      lectures ++= grabLecture(i)    // !!! reassignment to val
    //    }
    // @see http://seleniumexamples.com/blog/examples/meetup-com-attendance-lists/
    def grabLectures(links: List[String]): List[Lecture] = {
      def loop(l: List[String], r: List[Lecture]): List[Lecture] = {
        if (l.isEmpty)
          r
        else {
          driver.get(l.head)
          val id = driver.findElement(By.id("1")).getText
          val name = driver.findElement(By.xpath("1")).getText
          val lecturers = List(driver.findElement(By.id("1")).getText)
          loop(l.tail, r ++ List(new Lecture(id, name, lecturers)))
        }
      }

      List[Lecture]()
    }

    List[Lecture]()
  }

}
