package com.github.koshitake2m2.petstorees.core.config

import cats.effect.{Async, MonadCancelThrow, Resource}
import cats.implicits.catsSyntaxApplicativeId
import doobie.hikari.HikariTransactor
import doobie.util.ExecutionContexts
import io.circe.Decoder
import io.circe.config.parser
import io.circe.generic.semiauto.deriveDecoder

case class MysqlConfig(
    driver: String,
    url: String,
    user: String,
    password: String,
    connectionPoolSize: Int
) {
  def transactor[F[_]: Async]: Resource[F, HikariTransactor[F]] =
    for {
      connectEC <- ExecutionContexts.fixedThreadPool[F](connectionPoolSize)
      transactor <-
        HikariTransactor
          .newHikariTransactor[F](
            driver,
            url,
            user,
            password,
            connectEC
          )
    } yield transactor
}

object MysqlConfig {

  implicit val dataBaseDecoder: Decoder[MysqlConfig] = deriveDecoder
  def resourceBy[F[_]: MonadCancelThrow](path: String): Resource[F, MysqlConfig] =
    Resource.make(parser.decodePathF[F, MysqlConfig](path))(_ => ().pure[F])

}
