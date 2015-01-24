// MultiProject SBT Template - build.sbt

import sbt.Keys._
import scala.scalajs.sbtplugin.ScalaJSPlugin._
import ScalaJSKeys._

import sbt._
import Keys._
import play.Play._
import scala.scalajs.sbtplugin.ScalaJSPlugin._
import ScalaJSKeys._
import com.typesafe.sbt.packager.universal.UniversalKeys
import com.typesafe.sbteclipse.core.EclipsePlugin.EclipseKeys

import play.Play.autoImport._
import PlayKeys._


scalaJSSettings

transitiveClassifiers := Seq(Artifact.SourceClassifier)


// QuickSettings

lazy val projectNameDelimiter = "-"

lazy val projectOrganization = "io.GoodGuys"			// ?should use lower case here?

lazy val commonProjectName = "MultiProjectSBTTemplate"
lazy val jvmProjectName = commonProjectName + projectNameDelimiter + "JVM"
lazy val scalajsProjectName = commonProjectName + projectNameDelimiter + "ScalaJS"

lazy val commonProjectVersion = Def.ScopedKey(commonProjectScope, AttributeKey[String]("0.0.1.20150123"))
lazy val jvmProjectVersion = commonProjectVersion
lazy val scalajsProjectVersion = commonProjectVersion

lazy val commonScalaVersion = "2.11.5"
lazy val jvmScalaVersion = commonScalaVersion
lazy val scalajsScalaVersion = commonScalaVersion

lazy val commonProjectDirectory = "common_src"
lazy val jvmProjectDirectory = "jvm_src"
lazy val scalajsProjectDirectory = "scalajs_src"

// QuickDependencyVersions
lazy val scalajsVersion = "0.6"
lazy val scalajsDomVersion = "0.6"

// Projects

// lazy val root = (project in file(".")).
//   aggregate(commonProject, jvmProject, scalajsProject).
//   //settings(
//   //  aggregate in update := false // avoid aggregating the update task
//   //).
//   dependsOn(commonProject, jvmProject, scalajsProject) // now code in root can use classes from util

lazy val commonProjectScope: Scope = Scope(commonProject, Unit, Unit, Unit)
lazy val commonProject = (project in file(commonProjectDirectory)).
//   settings(commonResolvers.value).
  settings(commonSettings).
  settings(
    name := commonProjectName
//     version := commonProjectVersion,
//     scalaVersion := commonScalaVersion
  )

// lazy val jvmProject = (project in file(jvmProjectDirectory)).
//   //resolvers += commonResolvers ++ jvmResolvers.
//   settings((commonResolvers.value ++ jvmResolvers.value): _*).
//   settings((commonSettings.value ++ jvmSettings.value): _*).
//   settings(
//     name := jvmProjectName
//   ).
//   dependsOn(commonProject, scalajsProject)
// 
// lazy val scalajsProject = (project in file(scalajsProjectDirectory)).
//   //resolvers += commonResolvers ++ scalajsResolvers.
//   settings((commonResolvers.value ++ scalajsResolvers.value): _*).
//   settings((commonSettings.value ++ scalajsSettings.value): _*).
//   settings(
//     name := scalajsProjectName
//   ).
//   dependsOn(commonProject)



// Settings

lazy val commonSettings = Def.setting(
//   organization := projectOrganization,
  version := commonProjectVersion,
  scalaVersion := commonScalaVersion
//   libraryDependencies += commonDependencies: _*
)

// lazy val jvmSettings = Seq(
//   organization := projectOrganization,
//   version := jvmProjectVersion,
//   scalaVersion := jvmScalaVersion,
//   libraryDependencies ++= commonDependencies.value ++ jvmDependencies.value
// )
// 
// lazy val scalajsSettings = Seq(
//   organization := projectOrganization,
//   version := scalajsProjectVersion,
//   scalaVersion := scalajsScalaVersion,
//   libraryDependencies ++= commonDependencies.value ++ scalajsDependencies.value
// )
// 
// 
// 
// // Tasks
// 
// //lazy val runLinter = taskkey[Unit](???)
// 
// 
// 
// // Dependencies
// 
// lazy val commonResolvers = Def.setting[Resolver](Seq[Resolver]())
val commonDependencies = Def.setting(Seq[ModuleID]())
// 
// lazy val jvmResolvers = Def.setting(Seq[Resolver]]())
// lazy val jvmDependencies = commonDependencies.value ++ Def.setting(Seq[ModuleID](
//   //filters,
//   //jdbc,
//   //anorm,
//   "com.typesafe.slick" %% "slick" % "2.1.+",
//   "com.typesafe.play" %% "play-slick" % "0.8.+",
//   "com.lihaoyi" %% "upickle" % "0.2.+",
//   "org.webjars" %% "webjars-play" % "2.3.+",
//   "org.webjars" % "jquery" % "2.1.+",
//   "org.webjars" % "codemirror" % "4.3",
//   "org.webjars" % "bootstrap" % "3.2.+",
//   "org.webjars" % "font-awesome" % "4.1.+",
//   "net.glxn.qrgen" % "javase" % "2.0",                      // QR Code generator libray
//   "org.seleniumhq.selenium" % "selenium-java" % "2.44.+",   // Selenium Web Automation Suite (Java)
//   "com.github.detro" % "phantomjsdriver" % "1.2.+",         // WebDriver that uses PhantomJS as back-end
//   "io.spray" % "spray-client" % "1.3.+"                     // Spray HTTP client
// ))
// 
// lazy val scalajsResolvers = Def.setting(Seq[Resolver]())
// //lazy val scalajsDependencies = commonDependencies ++ Seq[ModuleID](
// lazy val scalajsDependencies = commonDependencies.value ++ Def.setting(Seq[ModuleID](
//   "org.scala-lang.modules.scalajs" %%% "scalajs-dom" % scalajsDomVersion,
//   "org.scala-lang.modules.scalajs" %%% "scalajs-jasmine-test-framework" % scalajsVersion % "test",
//   "com.lihaoyi" %%% "upickle" % "0.2.4",
//   "com.scalatags" %%% "scalatags" % "0.4.0",
//   "com.scalarx" %%% "scalarx" % "0.2.6",
//   "org.scala-lang.modules.scalajs" %%% "scalajs-jquery" % "0.6"
// ))