# MultiProject SBT Template - build.sbt



// QuickSettings

lazy val projectNameDelimiter = "-"

lazy val projectOrganization = "io.GoodGuys"			// ?should use lower case here?

lazy val commonProjectName = "MultiProjectSBTTemplate"
lazy val jvmProjectName = commonProjectName + projectNameDelimiter + "JVM"
lazy val scalajsProjectName = commonProjectName + projectNameDelimiter + "ScalaJS"

lazy val commonProjectVersion = "0.1.20150123"
lazy val jvmProjectVersion = commonProjectVersion
lazy val scalajsProjectVersion = commonProjectVersion

lazy val commonScalaVersion = "2.11.5"
lazy val jvmScalaVersion = commonScalaVersion
lazy val scalajsScalaVersion = commonScalaVersion

lazy val commonProjectDirectory = "common_src"
lazy val jvmProjectDirectory = "jvm_src"
lazy val scalajsProjectDirectory = "scalajs_src"



// Projects

lazy val root = (project in file(".")).
  aggregate(commonProject, jvmProject, scalajsProject).
  //settings(
  //  aggregate in update := false // avoid aggregating the update task
  //).
  dependsOn(commonProject, jvmProject, scalajsProject) // now code in core can use classes from util

lazy val commonProject = (project in file(commonProjectDirectory)).
  resolvers += commonResolvers.
  settings(commonSettings: _*).
  settings(
    name := globalProjectName
    version := commonProjectVersion
    scalaVersion := commonScalaVersion
  )

lazy val jvmProject = (project in file(jvmProjectDirectory)).
  resolvers += commonResolvers ++ jvmResolvers.
  settings(commonSettings: _*).
  settings(jvmSettings: _*).
  settings(
    name := jvmProjectName
  ).
  dependsOn(commonProject)

lazy val scalajsProject = (project in file(scalajsProjectDirectory)).
  resolvers += commonResolvers ++ scalajsResolvers.
  settings(commonSettings: _*).
  settings(scalajsSettings: _*).
  settings(
    name := scalajsProjectName
  ).
  dependsOn(commonProject)



// Settings

lazy val commonSettings = Seq(
  organization := projectOrganization,
  version := commonProjectVersion,
  scalaVersion := commonScalaVersion,
  libraryDependencies += commonDependencies
)

lazy val jvmSettings = Seq(
  organization := projectOrganization,
  version := jvmProjectVersion,
  scalaVersion := jvmScalaVersion,
  libraryDependencies += commonDependencies ++ jvmDependencies
)

lazy val scalajsSettings = Seq(
  organization := projectOrganization,
  version := scalajsProjectVersion,
  scalaVersion := scalajsScalaVersion,
  libraryDependencies += commonDependencies ++ scalajsDependencies
)



// Tasks

//lazy val runLinter = taskkey[Unit](???)



// Dependencies

lazy val commonResolvers = Seq()
val commonDependencies = Seq(
  
)

lazy val jvmResolvers = Seq()
val jvmDependencies = commonDependencies ++ Seq(
  filters,
  jdbc,
  anorm,
  "com.typesafe.slick" %% "slick" % "2.1.+",
  "com.typesafe.play" %% "play-slick" % "0.8.+",
  "com.lihaoyi" %% "upickle" % "0.2.+",
  "org.webjars" %% "webjars-play" % "2.3.+",
  "org.webjars" % "jquery" % "2.1.+",
  "org.webjars" % "codemirror" % "4.3",
  "org.webjars" % "bootstrap" % "3.2.+",
  "org.webjars" % "font-awesome" % "4.1.+",
  "net.glxn.qrgen" % "javase" % "2.0",                      // QR Code generator libray
  "org.seleniumhq.selenium" % "selenium-java" % "2.44.+",   // Selenium Web Automation Suite (Java)
  "com.github.detro" % "phantomjsdriver" % "1.2.+",         // WebDriver that uses PhantomJS as back-end
  "io.spray" % "spray-client" % "1.3.+"                     // Spray HTTP client
)

lazy val scalajsResolvers = Seq()
val scalajsDependencies = commonDependencies ++ Seq(
  "org.scala-lang.modules.scalajs" %%% "scalajs-dom" % Versions.scalajsDom,
  "org.scala-lang.modules.scalajs" %% "scalajs-jasmine-test-framework" % scalaJSVersion % "test",
  "com.lihaoyi" %%% "upickle" % "0.2.4",
  "com.scalatags" %%% "scalatags" % "0.4.0",
  "com.scalarx" %%% "scalarx" % "0.2.6",
  "org.scala-lang.modules.scalajs" %%% "scalajs-jquery" % "0.6"
)