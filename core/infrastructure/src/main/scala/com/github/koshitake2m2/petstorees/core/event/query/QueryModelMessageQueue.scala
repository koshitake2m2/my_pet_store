package com.github.koshitake2m2.petstorees.core.event.query

import cats.Applicative
import cats.implicits.*
import com.github.koshitake2m2.petstorees.shared.pattern.{DomainEvent, MessageQueue}

import scala.collection.mutable

case class QueryModelMessageQueue[F[_]: Applicative](
    private val queue: mutable.Queue[DomainEvent] // TODO: もっといいデータ構造がcats effectにあると思うので検討する. 例えばQueueやRefなど
) extends MessageQueue[F] {

  override def enqueue(e: DomainEvent): F[Unit] = ???

  override def dequeue: F[Option[DomainEvent]] =
    Either.catchOnly[NoSuchElementException](queue.dequeue()).toOption.pure[F]
}

object QueryModelMessageQueue {
  def empty[F[_]: Applicative]: QueryModelMessageQueue[F] = QueryModelMessageQueue[F](mutable.Queue.empty)
}
