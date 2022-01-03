import com.betobeto.build.sbt.core._

lazy val root = (project in file("."))
  .settings(name := "betobeto-events", description := "BetoBeto's event tracking backend")
  .aggregate(core, data, service)

lazy val core = (project in file("betobeto-events-core"))
  .settings(Settings.core, Settings.unitTests, name := "betobeto-events-core", libraryDependencies ++= Seq())

lazy val data = (project in file("betobeto-events-data"))
  .configs(IntegrationTest)
  .settings(
    Settings.core,
    Settings.unitTests,
    Settings.integrationTests,
    name := "betobeto-events-data",
    libraryDependencies ++= Seq()
  )
  .dependsOn(core % "test->test;compile->compile")

lazy val service = (project in file("betobeto-events-service"))
  .configs(IntegrationTest)
  .settings(
    Settings.core,
    Settings.unitTests,
    Settings.integrationTests,
    name := "betobeto-events-service",
    libraryDependencies ++=
      Seq(
        Dependencies.Libraries.catsCore,
        Dependencies.Libraries.catsEffects,
        Dependencies.Libraries.commonsDbcp2,
        Dependencies.Libraries.doobieCore,
        Dependencies.Libraries.flywayCore,
        Dependencies.Libraries.http4sBlazeClient,
        Dependencies.Libraries.http4sBlazeServer,
        Dependencies.Libraries.http4sCirce,
        Dependencies.Libraries.http4sDsl,
        "org.apache.phoenix" % "phoenix-core"               % "5.0.0-HBase-2.0",
        "org.apache.phoenix" % "phoenix-queryserver-client" % "5.0.0-HBase-2.0",
        Dependencies.Libraries.pureConfig,
        Dependencies.Libraries.pureConfigCatsEffect,
        Dependencies.Libraries.pureConfigEnumeratum,
        Dependencies.Libraries.circeCore,
        Dependencies.Libraries.circeOptics,
        Dependencies.Libraries.circeParser
      ),
    Compile / mainClass := Some("com.betobeto.events.service.ServerApp")
  )
  .dependsOn(core % "test->test;compile->compile", data % "test->test;compile->compile")
