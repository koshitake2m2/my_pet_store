package com.github.koshitake2m2.petstorees.core.order

trait OrderError extends Throwable

object OrderError {
  case class OrderIdAssertionError(rawId: String) extends OrderError
}
