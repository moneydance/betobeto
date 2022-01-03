package com.betobeto.build.sbt.core

import sbt._

object Dependencies {

  object Versions {
    val apacheCommons = "2.9.0"
    val cats = "2.6.1"
    val catsEffect = "3.1.1"
    val circe = "0.13.0"
    val doobie = "1.0.0-RC1"
    val enumeratum = "1.5.15"
    val enumeratumCats = "1.5.16"
    val enumeratumDoobie = "1.5.17"
    val http4s = "1.0.0-M29"
    val scalaTest = "3.0.5"
    val flyway = "7.5.2"
    val pureConfig = "0.16.0"
  }

  object Libraries {
    val catsCore = "org.typelevel" %% "cats-core" % Versions.cats
    val catsEffects = "org.typelevel"  %% "cats-effect" % Versions.catsEffect
    val circeCore = "io.circe" %% "circe-core" % Versions.circe
    val circeOptics = "io.circe" %% "circe-optics" % Versions.circe
    val circeParser = "io.circe" %% "circe-parser" % Versions.circe
    val commonsDbcp2 = "org.apache.commons" % "commons-dbcp2" % Versions.apacheCommons
    val doobieCore = "org.tpolecat" %% "doobie-core" % Versions.doobie
    val doobiePostgres = "org.tpolecat" %% "doobie-postgres"  % Versions.doobie
    val enumeratum = "com.beachape" %% "enumeratum" % Versions.enumeratum
    val enumeratumCats = "com.beachape" %% "enumeratum-cats" % Versions.enumeratumCats
    val enumeratumDoobie = "com.beachape" %% "enumeratum-doobie" % Versions.enumeratumDoobie
    val flywayCore = "org.flywaydb" % "flyway-core" % Versions.flyway
    val http4sBlazeClient = "org.http4s" %% "http4s-blaze-client" % Versions.http4s
    val http4sBlazeServer = "org.http4s" %% "http4s-blaze-server" % Versions.http4s
    val http4sCirce = "org.http4s" %% "http4s-circe" % Versions.http4s
    val http4sDsl = "org.http4s" %% "http4s-dsl" % Versions.http4s
    val pureConfig = "com.github.pureconfig" %% "pureconfig" % Versions.pureConfig
    val pureConfigEnumeratum = "com.github.pureconfig" %% "pureconfig-enumeratum" % Versions.pureConfig
    val pureConfigCatsEffect = "com.github.pureconfig" %% "pureconfig-cats-effect" % Versions.pureConfig
    val scalaIntegrationTest = "org.scalatest" %% "scalatest" % Versions.scalaTest % IntegrationTest
    val scalaUnitTest = "org.scalatest" %% "scalatest" % Versions.scalaTest % Test
  }
}
