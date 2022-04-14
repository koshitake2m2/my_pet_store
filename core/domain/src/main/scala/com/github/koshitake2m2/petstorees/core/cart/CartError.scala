package com.github.koshitake2m2.petstorees.core.cart

trait CartError extends Throwable

object CartError {
  case class CartIdAssertionError(rawId: String) extends CartError
}
