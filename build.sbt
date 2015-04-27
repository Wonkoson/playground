name := "TugOffline"

version := "1.0"

scalaVersion := "2.11.6"


// Projects

lazy val root = (project in file(".")) aggregate (macros, tugoffline)

lazy val macros = project in file("src/main/io/goodguys/macros")

lazy val tugoffline = (project in file("src/main/io/goodguys/tugoffline")) dependsOn macros




// Selenium

libraryDependencies ++= Seq (
  "org.seleniumhq.selenium" % "selenium-java" % "2.44.+",   // Selenium Web Automation Suite (Java)
  "com.github.detro" % "phantomjsdriver" % "1.2.+"         // WebDriver that uses PhantomJS as back-end
)


// Jsoup
libraryDependencies += "org.jsoup" % "jsoup" % "1.8.+"    // HTML Parser and Extractor


// ScalaTest

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.+"
