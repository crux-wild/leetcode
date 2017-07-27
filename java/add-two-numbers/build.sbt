//val junitInterfaceDependence =
  //"com.novocode" % "junit-interface" % "0.11" % "test"

val junitInterfaceTestOption = Tests.Argument(TestFrameworks.JUnit, "-q", "-v")

libraryDependencies += "junit" % "junit" % "4.12" % "test"

libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"

lazy val commonSettings = Seq(
  organization := "leetcode.com",

  version := "0.0.1-SNAPSHOT",

  scalaVersion := "2.11.7",

  // Enables publishing to maven repo
  publishMavenStyle := true,

  // Do not append Scala versions to the generated artifacts
  crossPaths := false,

  // This forbids including Scala related libraries into the dependency
  autoScalaLibrary := false
)

lazy val root = (project in file("."))
  .settings(
    name := "add-two-numbers",
    commonSettings,
    testOptions += junitInterfaceTestOption
  )
