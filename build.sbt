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

// official documentation
//resolvers += Resolver.bintrayRepo("cakesolutions", "maven")
//libraryDependencies += "net.cakesolutions" %% "scala-kafka-client" % "2.3.1"

// fix unresolved bintray cakesolutions uri
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
libraryDependencies ++= Seq(
  "net.glorat" %% "scala-kafka-client" % "2.4.0-SNAPSHOT",
  "net.glorat" %% "scala-kafka-client-testkit" % "2.4.0-SNAPSHOT" % "test",
)

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.3"
)
