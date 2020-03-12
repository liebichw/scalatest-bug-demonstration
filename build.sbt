import sbt.Keys._
import sbt._

name := "sbt-bug-test"

//enablePlugins(PackPlugin)

resolvers in ThisBuild += "Typesafe Releases" at "https://repo.typesafe.com/typesafe/releases/"

persistLogLevel in ThisBuild := Level.Info

version in ThisBuild := "0.0.0.1"


compileOrder := CompileOrder.JavaThenScala

updateOptions in ThisBuild := (updateOptions in ThisBuild).value.withGigahorse(false)


scalacOptions in ThisBuild ++=Seq("-encoding","iso8859-15","-feature","-deprecation","-target:jvm-1.8")


organization in ThisBuild := "somewhere.there"

publishArtifact in (ThisBuild,packageSrc) := false

publishArtifact in (ThisBuild,packageDoc) := true

scalaVersion in ThisBuild := "2.13.1"

crossPaths in ThisBuild := false

lazy val Javadoc = config("genjavadoc") extend Compile

lazy val javadocSettings = inConfig(Javadoc)(Defaults.configSettings) ++ Seq(
  addCompilerPlugin("com.typesafe.genjavadoc" %% "genjavadoc-plugin" % "0.15" cross CrossVersion.full),
  scalacOptions += s"-P:genjavadoc:out=${target.value}/java",
  packageDoc in Compile := (packageDoc in Javadoc).value,
  sources in Javadoc := {
    val res=(target.value / "java" ** "*.java").get ++
    (sources in Compile).value.filter(_.getName.endsWith(".java"))
    println(s"""sources are ${res.mkString("\t")}""")
    res },
  javacOptions in Javadoc := Seq(),
  artifactName in packageDoc in Javadoc := ((sv, mod, art) =>
    "" + mod.name + "-" + mod.revision + "-javadoc.jar")
)

val LOG4J_VERSION="2.13.1"
val LOG4J_ORG="org.apache.logging.log4j"


val LOG4J_CORE_LIBS=Seq(LOG4J_ORG % "log4j-api" % LOG4J_VERSION,
                        LOG4J_ORG % "log4j-core" % LOG4J_VERSION)


val LOG4J_LIBS = LOG4J_CORE_LIBS++Seq(LOG4J_ORG % "log4j-1.2-api" % LOG4J_VERSION)

libraryDependencies in ThisBuild += "junit" % "junit" % "4.13" % "test"

libraryDependencies in ThisBuild ++= Seq("com.novocode" % "junit-interface" % "0.11" % "test",
                                          "org.pegdown" % "pegdown" % "1.6.0" % "test")

testOptions in ThisBuild += Tests.Argument(TestFrameworks.ScalaTest,"-h",(target.value / "html-test-report").getAbsolutePath)


lazy val SCALATEST_LIBS = "org.scalatest" %% "scalatest" % "3.1.1" % "test"
lazy val SCALACHECK_LIBS = "org.scalacheck" %% "scalacheck" % "1.14.3" % "test"

lazy val proj1        = project
  .configs(Javadoc).settings(javadocSettings: _*)
  .settings(libraryDependencies ++= LOG4J_LIBS++Seq(SCALATEST_LIBS))
