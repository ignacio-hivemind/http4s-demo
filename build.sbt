ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.5.2"

lazy val root = (project in file("."))
  .settings(name := "http4s-demo")

addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt")
addCommandAlias("check", "clean; fmt; all compile Test/compile IntegrationTest/compile; test")

val http4sVersion = "1.0.0-M41"
val circeVersion  = "0.14.9"

libraryDependencies ++= Seq(
  "org.http4s"    %% "http4s-ember-server" % http4sVersion,
  "org.http4s"    %% "http4s-ember-client" % http4sVersion,
  "org.http4s"    %% "http4s-dsl"          % http4sVersion,
  "org.http4s"    %% "http4s-circe"        % http4sVersion,
  "io.circe"      %% "circe-core"          % circeVersion,
  "io.circe"      %% "circe-generic"       % circeVersion,
  "io.circe"      %% "circe-parser"        % circeVersion,
  "org.typelevel" %% "log4cats-slf4j"      % "2.6.0", // Log4Cats for logging
  "ch.qos.logback" % "logback-classic"     % "1.5.6", // Logback as the SLF4J implementation
)

scalacOptions ++= Seq(
  "-deprecation",     // emit warning and location for usages of deprecated APIs
  "-explain",         // explain errors in more detail
  "-explain-types",   // explain type errors in more detail
  "-feature",         // emit warning and location for usages of features that should be imported explicitly
  "-indent",          // allow significant indentation.
  "-new-syntax",      // require `then` and `do` in control expressions.
  "-print-lines",     // show source code line numbers.
  "-unchecked",       // enable additional warnings where generated code depends on assumptions
  "-Xkind-projector", // allow `*` as wildcard to be compatible with kind projector
  "-Werror",          // fail the compilation if there are any warnings
  "-Xmigration",      // warn about constructs whose behavior may have changed since version
  "-explain-cyclic",
)
