package com.github.koshitake2m2.petstorees.shared.pattern

/** ドメインイベント. */
trait DomainEvent {
  val id: DomainEventId
}
