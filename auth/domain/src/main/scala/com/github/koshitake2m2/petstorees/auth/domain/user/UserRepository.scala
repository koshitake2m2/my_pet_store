package com.github.koshitake2m2.petstorees.auth.domain.user

import com.github.koshitake2m2.petstorees.shared.pattern.Repository

trait UserRepository[F[_]] extends Repository[F, User, UserId]
