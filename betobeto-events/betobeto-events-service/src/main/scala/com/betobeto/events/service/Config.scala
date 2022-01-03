package com.betobeto.events.service

import cats.effect.kernel.Async
import cats.effect.Resource
import com.typesafe.config.ConfigFactory
import pureconfig._
import pureconfig.generic.auto._
import pureconfig.module.catseffect.syntax._

sealed case class PhoenixConfig(
  ip: String,
  port: Int,
  threadPoolSize: Int
)

sealed case class ServiceConfig(
  port: Int,
  host: String
)

sealed case class Config(
  phoenix: PhoenixConfig,
  service: ServiceConfig
)

object ConfigLoader {

  def load[M[_] : Async](configFile: String = "application.conf"): Resource[M, Config] = Resource
    .liftK(ConfigSource.fromConfig(ConfigFactory.load(configFile)).loadF[M, Config])

}
