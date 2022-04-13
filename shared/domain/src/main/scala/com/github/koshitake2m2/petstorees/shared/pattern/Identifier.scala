package com.github.koshitake2m2.petstorees.shared.pattern

/** 識別子.
  *
  * NOTE: これを継承した値オブジェクトは@newtypeやAnyValを利用できないのでアンボクシングのオーバーヘッドがあるが、他のEntityやRepository等の抽象化のために許容するものとする。
  */
trait Identifier
