# 命名・用語説明

- RequestBody: リクエストで受け取る値を詰め込んだ構造体. jsonから変換される.
- ResponseBody: レスポンスで返す値を詰め込んだ構造体. jsonに変換される.
- Dao: DBから該当のテーブルにアクセスするオブジェクト. ほぼテーブル単位に存在する
    - Record: Daoから取得したレコード
- Aggregate: 集約. DDDの概念では基本getterはなくコマンドメソッドのみだが、集約=エンティティとなる場合もあり実装を楽にするため今回はgetterもありとする. コマンドメソッドは変更後の集約とドメインイベントを返す.
- Entity: エンティティ. 一意に識別可能で変更管理が必要なオブジェクト.
- Repository: 集約単位にある. 基本nextId, findBy, add, removeしかない. 基本コマンドモデルからしか呼ばれない.
- CommandModel: コマンドモデル(ライトモデル).
- QueryModel: クエリモデル(リードモデル)
- DomainEvent: ドメインイベント
- EventStore: ドメインイベントを保持するストア. 今回はmongodbがそれにあたる
- MessageQueue: メッセージキュー. ドメインイベントをPublisherから受け取り、順次、Subscriberに渡す
- Publisher: イベントを発信する. 集約やエンティティに保持されている. メッセージキューにenqueueする
- Subscriber: メッセージキューからdequeueする. イベントストアにドメインイベントを保存する. イベントN回毎にスナップショットを作成する
