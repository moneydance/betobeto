package com.betobeto.build.sbt.core

import sbt._

object Resolvers {
  val clojars: Resolver = MavenRepository("clojars", "https://repo.clojars.org");
}
