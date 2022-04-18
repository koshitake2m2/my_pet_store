package com.github.koshitake2m2.petstorees.auth.domain.user

/** パスワードの暗号化に必要なソルト */
case class Salt(override val toString: String) extends AnyVal
