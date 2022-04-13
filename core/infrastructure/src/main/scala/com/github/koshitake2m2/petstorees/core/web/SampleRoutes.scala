package com.github.koshitake2m2.petstorees.core.web

import cats.MonadThrow
import cats.effect.*
import io.circe.generic.auto.*
import io.circe.syntax.*
import org.http4s.HttpRoutes
import org.http4s.circe.*
import org.http4s.dsl.Http4sDsl
import org.typelevel.log4cats.Logger

class SampleRoutes[F[_]: MonadThrow: Concurrent: Logger](
    queue: scala.collection.mutable.Queue[String]
) extends Http4sDsl[F] {

  def of: HttpRoutes[F] = HttpRoutes
    .of[F] {

      /** queueにメッセージを詰め込む.
        *
        * {{{
        *   curl -X GET localhost:8080/api/samples/hello
        * }}}
        */
      case GET -> Root / str =>
        case class Hello(input: String)
        queue.enqueue(str)
        val responseBody = Hello(str)
        Ok(responseBody.asJson)
    }
}
