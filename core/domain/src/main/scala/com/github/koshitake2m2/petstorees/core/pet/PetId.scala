package com.github.koshitake2m2.petstorees.core.pet

import cats.implicits.*
import com.github.koshitake2m2.petstorees.shared.pattern.Identifier
import eu.timepit.refined.api.Refined
import eu.timepit.refined.refineV
import eu.timepit.refined.string.Uuid

case class PetId(value: String Refined Uuid) extends Identifier

object PetId {
  def apply(rawId: String): Either[Throwable, PetId] =
    refineV[Uuid](rawId).bimap(e => new Throwable(e), PetId.apply)
}
