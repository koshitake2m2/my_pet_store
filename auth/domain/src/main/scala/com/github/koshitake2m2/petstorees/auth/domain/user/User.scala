package com.github.koshitake2m2.petstorees.auth.domain.user

import com.github.koshitake2m2.petstorees.shared.pattern.Aggregate

/** ユーザ. */
case class User(
    id: UserId,
    email: Email,
    hashedPassword: HashedPassword,
    salt: Salt
) extends Aggregate
