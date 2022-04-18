package com.github.koshitake2m2.petstorees.core.buyer

trait BuyerError extends Throwable

object BuyerError {
  case class BuyerIdAssertionError(rawId: String) extends BuyerError
  case class BuyerNameAssertionError(rawName: String) extends BuyerError
}
