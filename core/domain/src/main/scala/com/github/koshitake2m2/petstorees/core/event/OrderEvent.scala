package com.github.koshitake2m2.petstorees.core.event

import com.github.koshitake2m2.petstorees.core.order.OrderId
import com.github.koshitake2m2.petstorees.shared.pattern.{DomainEvent, DomainEventId}

sealed trait OrderEvent extends DomainEvent

object OrderEvent {
  case class ApprovedOrder(
      override val id: DomainEventId,
      orderId: OrderId
  ) extends OrderEvent

  case class CompletedOrder(
      override val id: DomainEventId,
      orderId: OrderId
  ) extends OrderEvent

  case class CanceledOrderBySeller(
      override val id: DomainEventId,
      orderId: OrderId
  ) extends OrderEvent

  case class CanceledOrderByBuyer(
      override val id: DomainEventId,
      orderId: OrderId
  ) extends OrderEvent
}
