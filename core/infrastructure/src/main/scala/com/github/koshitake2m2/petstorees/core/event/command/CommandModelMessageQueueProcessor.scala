package com.github.koshitake2m2.petstorees.core.event.command

import cats.effect.IO
import com.github.koshitake2m2.petstorees.core.event.query.QueryModelPublisher

import scala.concurrent.duration.{DurationInt, FiniteDuration}

class CommandModelMessageQueueProcessor(
    queue: CommandModelMessageQueue[IO],
    subscriber: CommandModelSubscriber[IO],
    queryModelPublisher: QueryModelPublisher[IO]
) {
  import CommandModelMessageQueueProcessor.*

  /** dequeueしてサブスクライバに渡してクエリモデルにpublishする */
  private def dequeueAndRun: IO[Unit] =
    queue.dequeue.map {
      case Some(e) =>
        subscriber.subscribe(e)
        queryModelPublisher.publish(e)
      case None =>
        println(s"command queue is empty")
    }

  def apply: IO[Unit] = IO.sleep(interval) >> dequeueAndRun >> apply
}

object CommandModelMessageQueueProcessor {
  val interval: FiniteDuration = 3.seconds
}
