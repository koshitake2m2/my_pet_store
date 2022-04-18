package com.github.koshitake2m2.petstorees.shared.pattern

import cats.implicits.*
import eu.timepit.refined.api.Refined
import eu.timepit.refined.refineV
import eu.timepit.refined.string.Uuid

/** ドメインイベントID. */
case class DomainEventId(value: String Refined Uuid) extends Identifier

object DomainEventId {
  def apply(rawId: String): Either[Throwable, DomainEventId] =
    refineV[Uuid](rawId).bimap(e => new Throwable(e), DomainEventId.apply)
}
