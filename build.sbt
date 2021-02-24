name := "ScalaApache_DataPipeline"

version := "0.1"

scalaVersion := "2.13.5"

////addSbtPlugin("com.typesafe.scala-logging" %% "scala-logging" % "3.9.2")
//libraryDependencies ++= Seq(
////  "ch.qos.logback" % "logback-classic" % "1.1.7",
//  "com.typesafe.scala-logging" %% "scala-logging" % "3.4.0"
//)

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.3" % Runtime
