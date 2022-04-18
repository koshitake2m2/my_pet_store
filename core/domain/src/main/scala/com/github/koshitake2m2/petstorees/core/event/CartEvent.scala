package com.github.koshitake2m2.petstorees.core.event

import com.github.koshitake2m2.petstorees.core.cart.CartId
import com.github.koshitake2m2.petstorees.core.pet.PetId
import com.github.koshitake2m2.petstorees.shared.pattern.{DomainEvent, DomainEventId}

sealed trait CartEvent extends DomainEvent

object CartEvent {
  case class AddedPetToCart(
      override val id: DomainEventId,
      petId: PetId,
      cartId: CartId
  ) extends CartEvent

  case class DeletedPetFromCart(
      override val id: DomainEventId,
      petId: PetId,
      cartId: CartId
  ) extends CartEvent

  case class OrderedInCart(override val id: DomainEventId, cartId: CartId) extends CartEvent
}
