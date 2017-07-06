val mainDeps = Seq(
  "org.typelevel"              %% "cats"                     % "0.9.0",
  "io.frees"                   %% "freestyle"                % "0.3.1")

addCompilerPlugin("org.scalameta" % "paradise" % "3.0.0-M9" cross CrossVersion.full)

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.2",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "freestyle-test",
    libraryDependencies ++= mainDeps
  )
