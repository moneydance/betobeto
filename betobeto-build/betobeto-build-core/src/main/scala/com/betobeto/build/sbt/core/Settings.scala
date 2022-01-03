package com.betobeto.build.sbt.core

import sbt._
import sbt.Keys._

object Settings {
  val core: Seq[Def.Setting[_]] = Def.settings(
    organization := "com.betobeto",
    scalaVersion := "2.12.10",
    resolvers ++= Seq(Resolvers.clojars),
    scalacOptions ++= Seq("-deprecation", "-feature"),
  )

  val unitTests: Seq[Def.Setting[_]] = Def.settings(
    libraryDependencies ++= Seq(
      Dependencies.Libraries.scalaUnitTest
    )
  )

  val integrationTests: Seq[Def.Setting[_]] = Def.settings(
    Defaults.itSettings,
    IntegrationTest / parallelExecution := false,
    libraryDependencies ++= Seq(
      Dependencies.Libraries.scalaIntegrationTest
    )
  )
}
