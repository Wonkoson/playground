
//scalaVersion := "2.11.6"

// Projects

lazy val root = (project in file(".")).
  settings(rootSettings: _*).
  settings(name := "TugOffline").
  aggregate(macros, core).
  dependsOn(macros, core)

lazy val macros = (project in file("src/main/io/goodguys/macros")).
  settings(macrosSettings: _*).
  settings(name := "TugOffline Macros")

lazy val core = (project in file("src/main/io/goodguys/tugoffline")).
  settings(coreSettings: _*).
  settings (name := "TugOffline Core").
  dependsOn(macros)




// Dependecies

val commonDeps = Seq(
  "org.scala-lang" % "scala-reflect" % "2.11.6",
  "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.3"
)

val rootDeps = commonDeps ++ Seq(

)

val macrosDeps = commonDeps ++ Seq(

)

val coreDeps = commonDeps ++ Seq(
  // Selenium
  "org.seleniumhq.selenium" % "selenium-java" % "2.44.+",   // Selenium Web Automation Suite (Java)
  "com.github.detro" % "phantomjsdriver" % "1.2.+",         // WebDriver that uses PhantomJS as back-end
  // Jsoup
  "org.jsoup" % "jsoup" % "1.8.+",                          // HTML Parser and Extractor
  // ScalaTest
  "org.scalatest" % "scalatest_2.11" % "2.2.+",             // Test Framework
  // MongoDB
  "org.mongodb" % "casbah_2.11" % "2.8.+",                  // MongoDB Casbah
  // Slick
  "com.typesafe.slick" % "slick_2.11" % "3.0.+"             // Slick
)



// Settings

lazy val Organization = "io.goodguys"
lazy val Version = "1.0"
lazy val ScalaVersion = "2.11.6"

lazy val commonSettings = Seq(
  organization := Organization,
  version := Version,
  scalaVersion := ScalaVersion
)

lazy val rootSettings = commonSettings ++ Seq(
  libraryDependencies ++= commonDeps ++ rootDeps ++ macrosDeps ++ coreDeps
)

lazy val macrosSettings = commonSettings ++ Seq(
  libraryDependencies ++= commonDeps ++ macrosDeps
)

lazy val coreSettings = commonSettings ++ Seq(
  libraryDependencies ++= commonDeps ++ coreDeps
)
