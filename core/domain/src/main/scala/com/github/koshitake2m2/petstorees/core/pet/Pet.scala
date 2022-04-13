package com.github.koshitake2m2.petstorees.core.pet

import com.github.koshitake2m2.petstorees.shared.pattern.Aggregate

/** ペット情報 */
case class Pet(
    petId: PetId,
    name: PetName,
    categories: List[PetCategory]
) extends Aggregate
