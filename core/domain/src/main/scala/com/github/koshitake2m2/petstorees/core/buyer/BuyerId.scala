package com.github.koshitake2m2.petstorees.core.buyer

import cats.implicits.*
import com.github.koshitake2m2.petstorees.core.buyer.BuyerError.BuyerIdAssertionError
import com.github.koshitake2m2.petstorees.shared.pattern.Identifier
import eu.timepit.refined.api.Refined
import eu.timepit.refined.refineV
import eu.timepit.refined.string.Uuid

case class BuyerId(value: String Refined Uuid) extends Identifier

object BuyerId {
  def apply(rawId: String): Either[Throwable, BuyerId] =
    refineV[Uuid](rawId).bimap(_ => BuyerIdAssertionError(rawId), BuyerId.apply)
}
