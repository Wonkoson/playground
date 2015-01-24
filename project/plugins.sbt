// Comment to get more information during initialization
//logLevel := Level.Warn

// Resolvers
resolvers += "TypeSafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "TypeSafe Online Play Repository" at "http://repo.typesafe.com/typesafe/simple/maven-releases/"

resolvers += "Spray Repository" at "http://repo.spray.io"

resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.7.0-SNAPSHOT")

// Sbt plugins
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.3.4")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.7.4")

addSbtPlugin("org.scala-lang.modules.scalajs" % "scalajs-sbt-plugin" % "0.5.6") //latest.integration)// % "0.5.5")

//addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.4.0")