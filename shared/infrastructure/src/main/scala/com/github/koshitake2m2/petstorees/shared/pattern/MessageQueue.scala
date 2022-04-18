package com.github.koshitake2m2.petstorees.shared.pattern

trait MessageQueue[F[_]] {
  def enqueue(e: DomainEvent): F[Unit]
  def dequeue: F[Option[DomainEvent]]
}
