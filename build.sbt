ThisBuild / scalaVersion     := "2.12.18"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.swalfie"
ThisBuild / organizationName := "Swalfie LLC"

val sparkVersion: String = "3.5.0"

autoScalaLibrary := false

lazy val root = (project in file("."))
  .settings(
    name := "spark-example",
    assembly / mainClass := Some("com.swalfie.app.Main"),

  )

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "com.typesafe" % "config" % "1.3.2",
  "org.apache.spark" %% "spark-sql" % sparkVersion % Test classifier "tests",
  "org.apache.spark" %% "spark-sql" % sparkVersion % Test classifier "test-sources",
  "org.scalatest" %% "scalatest" % "3.2.17" % Test,
  "org.scalamock" %% "scalamock" % "5.1.0" % Test,
  "com.holdenkarau" %% "spark-testing-base" % "3.5.0_1.4.7" % Test
)

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
