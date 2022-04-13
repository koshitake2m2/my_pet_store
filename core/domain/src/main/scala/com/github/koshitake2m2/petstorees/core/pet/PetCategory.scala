package com.github.koshitake2m2.petstorees.core.pet

import com.github.koshitake2m2.petstorees.shared.pattern.Entity

case class PetCategory(
    override val id: PetCategoryId,
    name: PetCategoryName
) extends Entity
