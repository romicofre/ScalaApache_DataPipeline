name := "ScalaApache_DataPipeline"

version := "0.1"

scalaVersion := "2.13.5"

val kafka_client_version = "2.4.0-SNAPSHOT"
val beam_version = "2.14.0"


libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.3" % Runtime

// official documentation : error
//resolvers += Resolver.bintrayRepo("cakesolutions", "maven")
//libraryDependencies += "net.cakesolutions" %% "scala-kafka-client" % "2.3.1"
// fix unresolved bintray cakesolutions uri :

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
libraryDependencies ++= Seq(
  "net.glorat" %% "scala-kafka-client" % kafka_client_version,
  "net.glorat" %% "scala-kafka-client-testkit" % kafka_client_version % "test",
)

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.3"
)
libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.8.0-alpha2"  // logging

// Beam from java libreries
libraryDependencies += "org.apache.beam" % "beam-runners-google-cloud-dataflow-java" % beam_version
libraryDependencies += "org.apache.beam" % "beam-runners-direct-java" % beam_version
libraryDependencies += "org.apache.beam" % "beam-sdks-java-io-kafka" % beam_version
//libraryDependencies += "org.apache.beam" % " beam-sdks-java-io-google-cloud-platform" % "2.1.0"
