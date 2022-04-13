package com.github.koshitake2m2.petstorees.core.event

import com.github.koshitake2m2.petstorees.core.pet.{Pet, PetId}
import com.github.koshitake2m2.petstorees.shared.pattern.{DomainEvent, DomainEventId}

sealed trait PetEvent extends DomainEvent

object PetEvent {

  case class RegisteredPet(
      override val id: DomainEventId,
      pet: Pet
  ) extends PetEvent

  // TODO: もしかしたら、UpdatedPetName, AddedCategory, RemovedCategoryに分けたほうがいいかもしれない。集約をそのまま渡すと集約そのままを保存したくなってしまい、リプレイヤー&スナップショットの機能が必要なくなってしまうから。
  case class UpdatedPet(
      override val id: DomainEventId,
      pet: Pet
  ) extends PetEvent

  case class RollbackedPet(
      override val id: DomainEventId,
      rollbackId: DomainEventId // TODO: 要検討. ロールバック済みのPetを渡すのもあり
  ) extends PetEvent

  case class DeletedPet(
      override val id: DomainEventId,
      petId: PetId
  ) extends PetEvent
}
