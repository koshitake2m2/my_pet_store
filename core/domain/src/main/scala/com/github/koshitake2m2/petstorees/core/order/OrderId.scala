package com.github.koshitake2m2.petstorees.core.order

import cats.implicits.*
import com.github.koshitake2m2.petstorees.core.order.OrderError.OrderIdAssertionError
import com.github.koshitake2m2.petstorees.shared.pattern.Identifier
import eu.timepit.refined.api.Refined
import eu.timepit.refined.refineV
import eu.timepit.refined.string.Uuid

case class OrderId(value: String Refined Uuid) extends Identifier

object OrderId {
  def apply(rawId: String): Either[OrderError, OrderId] =
    refineV[Uuid](rawId).bimap(_ => OrderIdAssertionError(rawId), OrderId.apply)
}
