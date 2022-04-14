package com.github.koshitake2m2.petstorees.auth.domain

import cats.implicits.*
import com.github.koshitake2m2.petstorees.auth.domain.user.UserError.EmailAssertionError
import eu.timepit.refined.api.Refined
import eu.timepit.refined.refineV
import eu.timepit.refined.string.MatchesRegex
import io.estatico.newtype.macros.newtype

package object user {
  // 緩めの正規表現.
  private type EmailRule = MatchesRegex["""[a-zA-Z0-9.-]+@[a-zA-Z0-9.-]+"""]
  @newtype case class Email(value: String Refined EmailRule)
  object Email {
    def apply(rawEmail: String): Either[UserError, Email] =
      refineV[EmailRule](rawEmail).bimap(_ => EmailAssertionError(rawEmail), Email.apply)
  }
}
