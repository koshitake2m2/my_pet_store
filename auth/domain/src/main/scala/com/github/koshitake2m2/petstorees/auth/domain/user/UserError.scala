package com.github.koshitake2m2.petstorees.auth.domain.user

trait UserError extends Throwable

object UserError {
  case class UserIdAssertionError(rawId: String) extends UserError
  case class EmailAssertionError(rawEmail: String) extends UserError
}
