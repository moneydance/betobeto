
lazy val commonSettings = Seq(
  scalaVersion := "2.12.10",
  organization := "com.betobeto"
)

lazy val root = project.settings(
    name := "betobeto-build",
    commonSettings,
    publish / skip := true
  )
  .dependsOn(
    core,
  )

lazy val core = (project in file("betobeto-build-core"))
  .settings(
    commonSettings,
    name := "betobeto-build-core",
    version := "1.0.0-SNAPSHOT",
    isSnapshot := true,
    libraryDependencies ++= Seq(
      "org.scala-sbt" % "sbt" % "1.4.6" % "provided",
      "org.typelevel" %% "cats-core" % "2.1.1",
      "com.beachape" %% "enumeratum" % "1.5.15",
      "com.beachape" %% "enumeratum-cats" % "1.5.16",
    )
  )

