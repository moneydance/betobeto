package com.betobeto.events.service

import cats.effect._
import doobie.util.ExecutionContexts
import doobie.util.transactor.Transactor
import javax.sql.DataSource
import org.http4s.blaze.server._

object ServerApp extends IOApp {

  def run(args: List[String]): IO[ExitCode] = resources("application.conf").use(bootstrap)

  private def resources(configFile: String): Resource[IO, Resources] =
    for {
      config     <- ConfigLoader.load[IO](configFile)
      ec         <- ExecutionContexts.fixedThreadPool[IO](config.phoenix.threadPoolSize)
      transactor <- PhoenixDatabase.resource[IO](config.phoenix, ec)
    } yield Resources(transactor, config)

  private def bootstrap(resources: Resources): IO[ExitCode] =
    for {
      _ <- IO(println("Resources Initialized!"))
      exitCode <-
        BlazeServerBuilder[IO]
          .bindHttp(resources.config.service.port, resources.config.service.host)
          .withHttpApp(new TestRouter().routes.orNotFound)
          .serve
          .compile
          .lastOrError
    } yield exitCode

  case class Resources(
    transactor: Transactor.Aux[IO, DataSource],
    config: Config
  )

}
