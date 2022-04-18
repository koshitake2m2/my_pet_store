package com.github.koshitake2m2.petstorees.auth.repository

import com.github.koshitake2m2.petstorees.auth.domain.user.{User, UserId}
import com.github.koshitake2m2.petstorees.shared.pattern.Repository

case class UserRepository[F[_]]() extends Repository[F, User, UserId] {
  override def nextId: F[UserId] = ???

  override def findBy(id: UserId): F[Option[User]] = ???

  override def save(aggregate: User): F[Unit] = ???

  override def remove(id: UserId): F[Unit] = ???
}
