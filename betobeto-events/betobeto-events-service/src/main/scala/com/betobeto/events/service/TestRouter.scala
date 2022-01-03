package com.betobeto.events.service

import cats.effect.IO
import org.http4s.dsl.Http4sDsl
import org.http4s.HttpRoutes

class TestRouter() extends Http4sDsl[IO] {

  val routes = HttpRoutes.of[IO] { case GET -> Root / "test" =>
    Ok("hi")
  }

}
