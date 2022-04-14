package com.github.koshitake2m2.petstorees.core.seller

trait SellerError extends Throwable

object SellerError {
  case class SellerIdAssertionError(rawId: String) extends SellerError
  case class SellerNameAssertionError(rawName: String) extends SellerError
}
