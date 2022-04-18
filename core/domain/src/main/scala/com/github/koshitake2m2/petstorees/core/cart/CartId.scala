package com.github.koshitake2m2.petstorees.core.cart

import cats.implicits.*
import com.github.koshitake2m2.petstorees.core.cart.CartError.CartIdAssertionError
import com.github.koshitake2m2.petstorees.shared.pattern.Identifier
import eu.timepit.refined.api.Refined
import eu.timepit.refined.refineV
import eu.timepit.refined.string.Uuid

case class CartId(value: String Refined Uuid) extends Identifier

object CartId {
  def apply(rawId: String): Either[Throwable, CartId] =
    refineV[Uuid](rawId).bimap(_ => CartIdAssertionError(rawId), CartId.apply)
}
