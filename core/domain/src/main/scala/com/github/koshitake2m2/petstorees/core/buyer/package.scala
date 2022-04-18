package com.github.koshitake2m2.petstorees.core

import cats.implicits.*
import eu.timepit.refined.api.Refined
import eu.timepit.refined.boolean.And
import eu.timepit.refined.collection.{MaxSize, NonEmpty}
import eu.timepit.refined.refineV
import io.estatico.newtype.macros.newtype

package object buyer {
  private type BuyerNameRule = NonEmpty And MaxSize[100]
  @newtype case class BuyerName(value: String Refined BuyerNameRule)
  object BuyerName {
    def apply(rawName: String): Either[Throwable, BuyerName] =
      refineV[BuyerNameRule](rawName).bimap(e => new Throwable(e), BuyerName.apply)
  }
}
