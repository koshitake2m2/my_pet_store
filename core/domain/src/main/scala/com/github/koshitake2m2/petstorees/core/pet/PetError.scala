package com.github.koshitake2m2.petstorees.core.pet

trait PetError extends Throwable

object PetError {
  case class PetIdAssertionError(rawId: String) extends PetError
  case class PetNameAssertionError(rawName: String) extends PetError
  case class PetCategoryIdAssertionError(rawId: String) extends PetError
  case class PetCategoryNameAssertionError(rawName: String) extends PetError
}
