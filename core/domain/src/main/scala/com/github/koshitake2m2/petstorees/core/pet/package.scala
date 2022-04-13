package com.github.koshitake2m2.petstorees.core

import cats.implicits.*
import eu.timepit.refined.api.Refined
import eu.timepit.refined.collection.NonEmpty
import eu.timepit.refined.refineV
import io.estatico.newtype.macros.newtype

package object pet {
  private type PetRule = NonEmpty
  @newtype case class PetName(value: String Refined PetRule)
  object PetName {
    def apply(rawName: String): Either[Throwable, PetName] =
      refineV[PetRule](rawName).bimap(e => new Throwable(e), PetName.apply)
  }

  private type PetCategoryRule = NonEmpty
  @newtype case class PetCategoryName(value: String Refined PetCategoryRule)
  object PetCategoryName {
    def apply(rawName: String): Either[Throwable, PetCategoryName] =
      refineV[PetCategoryRule](rawName).bimap(e => new Throwable(e), PetCategoryName.apply)
  }
}
