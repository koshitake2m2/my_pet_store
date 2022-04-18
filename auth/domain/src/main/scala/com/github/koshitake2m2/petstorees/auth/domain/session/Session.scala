package com.github.koshitake2m2.petstorees.auth.domain.session

import com.github.koshitake2m2.petstorees.auth.domain.user.UserId
import com.github.koshitake2m2.petstorees.shared.pattern.Aggregate

/** セッション. */
case class Session(
    id: SessionId,
    userId: UserId,
    expireAt: ExpireAt
) extends Aggregate
