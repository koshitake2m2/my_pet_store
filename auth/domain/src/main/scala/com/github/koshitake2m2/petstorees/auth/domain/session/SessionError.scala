package com.github.koshitake2m2.petstorees.auth.domain.session

trait SessionError extends Throwable

object SessionError {
  case class SessionIdAssertionError(rawId: String) extends SessionError
}
