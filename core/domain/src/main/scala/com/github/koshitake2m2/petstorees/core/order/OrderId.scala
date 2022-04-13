package com.github.koshitake2m2.petstorees.core.order

import cats.implicits.*
import com.github.koshitake2m2.petstorees.shared.pattern.Identifier
import eu.timepit.refined.api.Refined
import eu.timepit.refined.refineV
import eu.timepit.refined.string.Uuid

case class OrderId(value: String Refined Uuid) extends Identifier

object OrderId {
  def apply(rawId: String): Either[Throwable, OrderId] =
    refineV[Uuid](rawId).bimap(e => new Throwable(e), OrderId.apply)
}
