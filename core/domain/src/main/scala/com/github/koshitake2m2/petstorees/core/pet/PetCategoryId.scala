package com.github.koshitake2m2.petstorees.core.pet

import cats.implicits.*
import com.github.koshitake2m2.petstorees.core.pet.PetError.PetCategoryIdAssertionError
import com.github.koshitake2m2.petstorees.shared.pattern.Identifier
import eu.timepit.refined.api.Refined
import eu.timepit.refined.refineV
import eu.timepit.refined.string.Uuid

case class PetCategoryId(value: String Refined Uuid) extends Identifier

object PetCategoryId {
  def apply(rawId: String): Either[PetError, PetCategoryId] =
    refineV[Uuid](rawId).bimap(_ => PetCategoryIdAssertionError(rawId), PetCategoryId.apply)
}
