package com.github.koshitake2m2.petstorees.core.cart

import cats.data.NonEmptyList
import com.github.koshitake2m2.petstorees.core.pet.PetId
import com.github.koshitake2m2.petstorees.shared.pattern.Aggregate

/** カート(買い物カゴ)
  *
  * NOTE: ドメインで気にすることではないが, DB的にはカートと注文は同じドキュメント(テーブル)であり, ステータスによってカートか注文か区別される.
  */
case class Cart(
    id: CartId,
    petIds: NonEmptyList[PetId]
) extends Aggregate
