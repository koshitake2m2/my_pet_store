package com.github.koshitake2m2.petstorees.core.event.command

import cats.effect.Temporal
import cats.implicits.*
import com.github.koshitake2m2.petstorees.core.event.query.QueryModelPublisher

import scala.concurrent.duration.{DurationInt, FiniteDuration}

class CommandModelMessageQueueProcessor[F[_]: Temporal](
    queue: CommandModelMessageQueue[F],
    subscriber: CommandModelSubscriber[F],
    queryModelPublisher: QueryModelPublisher[F]
) {
  import CommandModelMessageQueueProcessor.*

  /** dequeueしてサブスクライバに渡してクエリモデルにpublishする */
  private def dequeueAndRun: F[Unit] =
    queue.dequeue.map {
      case Some(e) =>
        subscriber.subscribe(e)
        queryModelPublisher.publish(e)
      case None =>
        println(s"command queue is empty")
    }

  def apply: F[Unit] = Temporal[F].sleep(interval) >> dequeueAndRun >> apply
}

object CommandModelMessageQueueProcessor {
  val interval: FiniteDuration = 3.seconds
}
