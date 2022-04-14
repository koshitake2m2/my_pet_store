package com.github.koshitake2m2.petstorees.core.web

import cats.effect.*
import cats.implicits.*
import com.github.koshitake2m2.petstorees.core.config.MysqlConfig
import com.github.koshitake2m2.petstorees.core.event.command.{
  CommandModelMessageQueue,
  CommandModelMessageQueueProcessor,
  CommandModelSubscriber
}
import com.github.koshitake2m2.petstorees.core.event.query.{
  QueryModelMessageQueue,
  QueryModelMessageQueueProcessor,
  QueryModelPublisher,
  QueryModelSubscriber
}
import doobie.hikari.HikariTransactor
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.server.{Router, Server}
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger

import scala.collection.mutable
import scala.concurrent.ExecutionContext.global
import scala.concurrent.duration.{DurationInt, FiniteDuration}

// TODO: このファイル全体的に整理する
object WebServer extends IOApp {

  lazy val port = 8080
  lazy val host = "localhost"
  implicit def unsafeLogger[F[_]: Sync]: Logger[F] = Slf4jLogger.getLogger[F]

  // TODO: 削除
  val dequeueInterval: FiniteDuration = 3.seconds
  val q: mutable.Queue[String] = mutable.Queue.empty
  def dequeueAndRun: IO[Unit] = IO.sleep(dequeueInterval) >> IO {
    Either.catchOnly[NoSuchElementException](q.dequeue()) match {
      case Right(str) => println(s"dequeue: $str")
      case Left(_)    => println(s"queue is empty.")
    }
  } >> dequeueAndRun

  /** メッセージキュー関連 */
  val querySubscriber = new QueryModelSubscriber[IO]
  val queryQueue: QueryModelMessageQueue[IO] = QueryModelMessageQueue.empty[IO]
  val queryProcessor = new QueryModelMessageQueueProcessor(queryQueue, querySubscriber)
  val queryModelPublisher = new QueryModelPublisher[IO](queryQueue)
  val commandSubscriber = new CommandModelSubscriber[IO]
  val commandQueue: CommandModelMessageQueue[IO] = CommandModelMessageQueue.empty[IO]
  val commandProcessor = new CommandModelMessageQueueProcessor[IO](
    commandQueue,
    commandSubscriber,
    queryModelPublisher
  )

  def run(args: List[String]): IO[ExitCode] =
    dependencies[IO]
      .use { _ =>
        (dequeueAndRun, commandProcessor.apply, queryProcessor.apply).parTupled // *> IO.never
      }
      .as(ExitCode.Success)

  def transactorResourceBy[F[_]: Async](path: String): Resource[F, HikariTransactor[F]] =
    MysqlConfig.resourceBy(path).flatMap(_.transactor[F])

  def dependencies[F[_]: Async: MonadCancelThrow]: Resource[F, Server] = for {
    authTransactor <- transactorResourceBy("root.auth.mysql")
    coreTransactor <- transactorResourceBy("root.core.mysql") // TODO: リードモデル作成時に利用
    sampleRoutes = new SampleRoutes[F](q)

    httpApp = Router(
      "/api/samples" -> sampleRoutes.of // TODO: そのうち削除
    ).orNotFound
    server <- BlazeServerBuilder[F]
      .withExecutionContext(global)
      .bindHttp(port, host)
      .withHttpApp(httpApp)
      .resource
  } yield server
}
