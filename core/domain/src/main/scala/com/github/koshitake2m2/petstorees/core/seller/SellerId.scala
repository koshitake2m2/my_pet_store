package com.github.koshitake2m2.petstorees.core.seller

import cats.implicits.*
import com.github.koshitake2m2.petstorees.shared.pattern.Identifier
import eu.timepit.refined.api.Refined
import eu.timepit.refined.refineV
import eu.timepit.refined.string.Uuid

case class SellerId(value: String Refined Uuid) extends Identifier

object SellerId {
  def apply(rawId: String): Either[Throwable, SellerId] =
    refineV[Uuid](rawId).bimap(e => new Throwable(e), SellerId.apply)
}
