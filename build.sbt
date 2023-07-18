name := "foo-plugin"
organization := "foo"
scalaVersion := "3.3.1-RC4"
(ThisBuild / version) := "0.0.1-SNAPSHOT"

lazy val plugin = project
  .in(file("./plugin"))
  .settings(
    organization := "foo",
    scalaVersion := "3.3.1-RC4",
    name := "foo-plugin",
    libraryDependencies += "org.scala-lang" %% "scala3-compiler" % scalaVersion.value % Provided
  )

lazy val example = project
  .in(file("./example/"))
  .settings(
    organization := "foo",
    scalaVersion := "3.3.1-RC4",
    name := "foo-example",
    addCompilerPlugin("foo" %% "foo-plugin" % "0.0.1-SNAPSHOT")
  )
