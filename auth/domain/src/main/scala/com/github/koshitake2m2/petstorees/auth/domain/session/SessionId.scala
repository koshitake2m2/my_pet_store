package com.github.koshitake2m2.petstorees.auth.domain.session

import cats.implicits.*
import com.github.koshitake2m2.petstorees.auth.domain.session.SessionError.SessionIdAssertionError
import com.github.koshitake2m2.petstorees.shared.pattern.Identifier
import eu.timepit.refined.api.Refined
import eu.timepit.refined.refineV
import eu.timepit.refined.string.Uuid

case class SessionId(value: String Refined Uuid) extends Identifier

object SessionId {
  def apply(rawId: String): Either[SessionError, SessionId] =
    refineV[Uuid](rawId).bimap(_ => SessionIdAssertionError(rawId), SessionId.apply)
}
