package com.github.koshitake2m2.petstorees.auth.repository

import com.github.koshitake2m2.petstorees.auth.domain.session.{Session, SessionId}
import com.github.koshitake2m2.petstorees.shared.pattern.Repository

case class SessionRepository[F[_]]() extends Repository[F, Session, SessionId] {
  override def nextId: F[SessionId] = ???

  override def findBy(id: SessionId): F[Option[Session]] = ???

  override def save(aggregate: Session): F[Unit] = ???

  override def remove(id: SessionId): F[Unit] = ???
}
