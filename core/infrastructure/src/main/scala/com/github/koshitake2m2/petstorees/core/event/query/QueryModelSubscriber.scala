package com.github.koshitake2m2.petstorees.core.event.query

import com.github.koshitake2m2.petstorees.shared.pattern.{DomainEvent, DomainEventSubscriber}

class QueryModelSubscriber[F[_]]() extends DomainEventSubscriber {
  // TODO: ドメインイベントのパターンマッチで, それぞれのイベントでクエリモデル用DBを更新する. 具体的にはDaoを呼び出しinsertやupdateをする.
  def subscribe(e: DomainEvent): Unit = println("query model subscriber")
}
