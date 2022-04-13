package com.github.koshitake2m2.petstorees.core.event.command

import com.github.koshitake2m2.petstorees.shared.pattern.{DomainEvent, DomainEventSubscriber}

class CommandModelSubscriber[F[_]]() extends DomainEventSubscriber {
  // TODO: コマンドモデル用DBを更新する。具体的にはイベントストアにイベントを保存し、N回目でスナップショットの作成を行う。ドメインイベントのパターンマッチでそれぞれのイベントに応じた処理を行う。
  def subscribe(e: DomainEvent): Unit = println("command model subscriber")
}
