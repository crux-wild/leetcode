resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"

lazy val commonSettings = Seq(
  organization := "com.leetcode.scala",
  version := "0.0.1-SNAPSHOT",
  scalaVersion := "2.11.7"
)

lazy val root = (project in file("."))
  .settings(
    name := "8. String to Integer (atoi)",
    commonSettings,
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.1" % "test"
    )
  )
