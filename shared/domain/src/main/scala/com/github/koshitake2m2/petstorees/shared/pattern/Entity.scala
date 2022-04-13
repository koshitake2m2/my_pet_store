package com.github.koshitake2m2.petstorees.shared.pattern

/** エンティティ. 一意に識別可能で変更管理が必要なオブジェクト. */
trait Entity {
  val id: Identifier
}
