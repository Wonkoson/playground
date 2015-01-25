// MultiProject SBT Template - build.sbt

// import sbt.Keys._
// import scala.scalajs.sbtplugin.ScalaJSPlugin._
// import ScalaJSKeys._

import sbt._
import Keys._
import play.Play._
// import scala.scalajs.sbtplugin.ScalaJSPlugin._
// import ScalaJSKeys._
import com.typesafe.sbt.packager.universal.UniversalKeys
// import com.typesafe.sbteclipse.core.EclipsePlugin.EclipseKeys

// import play.Play.autoImport._
// import PlayKeys._



// GlobalSettings

// scalaJSSettings

enablePlugins(ScalaJSPlugin)

transitiveClassifiers := Seq(Artifact.SourceClassifier)

// persistLauncher := true

// persistLauncher in Test := false

scalaVersion := commonScalaVersion


// QuickSettings

lazy val projectNameDelimiter = "-"

lazy val projectOrganization = "io.GoodGuys"			// ?should use lower case here?

lazy val commonProjectName = "MultiProjectSBTTemplate"
lazy val jvmProjectName = commonProjectName + projectNameDelimiter + "JVM"
lazy val scalajsProjectName = commonProjectName + projectNameDelimiter + "ScalaJS"

lazy val commonProjectVersion = "0.0.1.20150123"
lazy val jvmProjectVersion = commonProjectVersion
lazy val scalajsProjectVersion = commonProjectVersion

lazy val commonScalaVersion = "2.11.5"
lazy val jvmScalaVersion = commonScalaVersion
lazy val scalajsScalaVersion = commonScalaVersion

lazy val commonProjectDirectory = "common_src"
lazy val jvmProjectDirectory = "jvm_src"
lazy val scalajsProjectDirectory = "scalajs_src"

// QuickDependencyVersions
lazy val scalajsVersion = "0.6.0-RC2"
lazy val scalajsDomVersion = "0.7.0"



// Projects

lazy val root = (project in file(".")).
  aggregate(commonProject, jvmProject, scalajsProject).
  dependsOn(commonProject, jvmProject, scalajsProject) // now code in root can use classes from util

lazy val commonProject = (project in file(commonProjectDirectory)).
  settings(
    name := commonProjectName,
    version := "0.0.1.20150123",
    scalaVersion := commonScalaVersion,
    libraryDependencies ++= commonDependencies
  )

lazy val jvmProject = (project in file(jvmProjectDirectory)).
  settings(
    name := jvmProjectName,
    version := "0.0.1.20150123",
    scalaVersion := commonScalaVersion,
    libraryDependencies ++= commonDependencies ++ jvmDependencies
  ).
  dependsOn(commonProject, scalajsProject)

lazy val scalajsProject = (project in file(scalajsProjectDirectory)).
  settings(
    persistLauncher := true,
    persistLauncher in Test := false,
    name := scalajsProjectName,
    version := "0.0.1.20150123",
    scalaVersion := commonScalaVersion,
    libraryDependencies ++= commonDependencies ++ scalajsDependencies.value
  ).
  dependsOn(commonProject)



// // Tasks
// 
// //lazy val runLinter = taskkey[Unit](???)



// // Dependencies
// 
// lazy val commonResolvers = Def.setting[Resolver](Seq[Resolver]())
val commonDependencies = Seq[ModuleID]()

// lazy val jvmResolvers = Def.setting(Seq[Resolver]]())
lazy val jvmDependencies = Seq[ModuleID](
  //filters,
  //jdbc,
  //anorm,
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

// lazy val scalajsResolvers = Def.setting(Seq[Resolver]())

// Note: %%% can only be used within a task or setting macro, such as :=, +=, ++=, Def.task, or Def.setting...

// lazy val scalajsDependencies = commonDependencies ++ Def.setting(Seq[ModuleID](
//   "org.scala-lang.modules.scalajs" %%% "scalajs-dom" % scalajsDomVersion,
//   "org.scala-lang.modules.scalajs" %%% "scalajs-jasmine-test-framework" % scalajsVersion % "test",
//   "com.lihaoyi" %%% "upickle" % "0.2.4",
//   "com.scalatags" %%% "scalatags" % "0.4.0",
//   "com.scalarx" %%% "scalarx" % "0.2.6",
//   "org.scala-lang.modules.scalajs" %%% "scalajs-jquery" % "0.6"
// ))

lazy val scalajsDependencies = Def.setting(Seq[ModuleID](
//   "org.scala-js" %%% "scalajs-dom" % scalajsDomVersion, // TODO: doesn't work yet
  "org.scala-js" %%% "scalajs-dom_sjs0.6.0-RC1" % scalajsDomVersion,
//   "org.scala-lang.modules.scalajs" %%% "scalajs-jasmine-test-framework" % scalajsVersion % "test",
  "com.lihaoyi" %%% "upickle" % "latest.integration",//"0.2.4",
  "com.scalatags" %%% "scalatags" % "latest.integration",//"0.4.0",
  "com.scalarx" %%% "scalarx" % "latest.integration"//"0.2.6"
//   "org.scala-js" %%% "scalajs-jquery_sjs0.6.0-RC1" % scalajsDomVersion/
))

//DONE?
// scalajs-compiler
// scalajs-library

//TODO:
// scalajs-ir
// scalajs-javalib-ex
// scalajs-js-envs
// scalajs-sbt-test-adapter
// scalajs-test-interface
// scalajs-angular
// scalajs-d3
// scalajs-tools
// scalajs-jquery