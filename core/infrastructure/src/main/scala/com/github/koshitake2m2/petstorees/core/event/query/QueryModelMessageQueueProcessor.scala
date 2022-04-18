package com.github.koshitake2m2.petstorees.core.event.query

import cats.effect.IO

import scala.concurrent.duration.{DurationInt, FiniteDuration}

class QueryModelMessageQueueProcessor(
    queue: QueryModelMessageQueue[IO],
    subscriber: QueryModelSubscriber[IO]
) {
  import QueryModelMessageQueueProcessor.*

  /** dequeueしてクエリモデル用DBを更新する. */
  private def dequeueAndRun: IO[Unit] =
    queue.dequeue.map {
      case Some(e) =>
        println(s"update query model DB: $e")
        subscriber.subscribe(e)
      case None =>
        println(s"query queue is empty")
    }

  def apply: IO[Unit] = IO.sleep(interval) >> dequeueAndRun >> apply
}

object QueryModelMessageQueueProcessor {
  val interval: FiniteDuration = 3.seconds
}
