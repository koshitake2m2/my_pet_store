package com.github.koshitake2m2.petstorees.core

import cats.implicits.*
import com.github.koshitake2m2.petstorees.core.pet.PetError.{PetCategoryNameAssertionError, PetNameAssertionError}
import eu.timepit.refined.api.Refined
import eu.timepit.refined.collection.NonEmpty
import eu.timepit.refined.refineV
import io.estatico.newtype.macros.newtype

package object pet {
  private type PetRule = NonEmpty
  @newtype case class PetName(value: String Refined PetRule)
  object PetName {
    def apply(rawName: String): Either[PetError, PetName] =
      refineV[PetRule](rawName).bimap(_ => PetNameAssertionError(rawName), PetName.apply)
  }

  private type PetCategoryRule = NonEmpty
  @newtype case class PetCategoryName(value: String Refined PetCategoryRule)
  object PetCategoryName {
    def apply(rawName: String): Either[PetError, PetCategoryName] =
      refineV[PetCategoryRule](rawName).bimap(_ => PetCategoryNameAssertionError(rawName), PetCategoryName.apply)
  }
}
