package com.github.koshitake2m2.petstorees.shared.pattern

/** 永続指向のリポジトリ. 基本的にnextId, findBy, add, removeしかない. */
trait Repository[F[_], A <: Aggregate, Id <: Identifier] {
  // NOTE: saveの中でIdを生成すれば凝集度が高くなるが、リポジトリは完全な集約を受け渡しするようなのでIdの生成方法も公開する。
  def nextId: F[Id]
  def findBy(id: Id): F[Option[A]]
  def save(aggregate: A): F[Unit]
  def remove(id: Id): F[Unit]
}
