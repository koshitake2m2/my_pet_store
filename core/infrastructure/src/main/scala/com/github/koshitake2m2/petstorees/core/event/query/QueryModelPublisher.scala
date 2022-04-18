package com.github.koshitake2m2.petstorees.core.event.query

import com.github.koshitake2m2.petstorees.shared.pattern.{DomainEvent, DomainEventPublisher}

class QueryModelPublisher[F[_]](
    queue: QueryModelMessageQueue[F]
) extends DomainEventPublisher {
  // TODO: MessageQueueにenqueueする
  def publish(e: DomainEvent): Unit = println("query model publish")
}
