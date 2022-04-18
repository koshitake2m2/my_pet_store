package com.github.koshitake2m2.petstorees.auth.domain.session

import com.github.koshitake2m2.petstorees.shared.pattern.Repository

trait SessionRepository[F[_]] extends Repository[F, Session, SessionId]
