package com.github.koshitake2m2.petstorees.auth.domain.session

import java.time.ZonedDateTime

case class ExpireAt(toZonedDateTime: ZonedDateTime) extends AnyVal
