package com.github.koshitake2m2.petstorees.core.order

import cats.data.NonEmptyList
import com.github.koshitake2m2.petstorees.core.pet.PetId
import com.github.koshitake2m2.petstorees.shared.pattern.Aggregate

/** 注文 */
case class Order(
    id: OrderId,
    petIds: NonEmptyList[PetId]
) extends Aggregate
