# pet_store_es

このアプリはオンラインペットショップであり、購入者がアプリでペットを予約して、販売者の元まで受け取りに行くためのサポートをすることを想定したものである。

DDDの戦術的設計やイベントソーシングの学習目的で開発している。

## 各種ドキュメント
- [ユースケース](./docs/usecase.md)
- [プロジェクト構成](./docs/project_structure.md)
- [命名・用語説明](./docs/naming.md)
- [イベントソーシングの方針](./docs/event_sourcing.md)
- 図
  - [ER図](./docs/diagram/er.md)
  - シーケンス図
    - [認証関連](./docs/diagram/sequence/auth.md)
- [参考文献](./docs/refference.md)

## 技術選定

- scala 2.13.8
- cats
- cats effect
- http4s
- refined
- newtype
- circe
- mysql
- doobie
- mongodb

## 今後の展望
- scala3 移行
- eff
- coreとauthのコンテキスト間の通信をgRPC等に移行し、coreはauthのコードを見れないようにする
- mongodbのchange eventsをwatchしてリードモデルを構築する
- メッセージキューに排他制御を導入する
- Github Actionsでテスト実行&フォーマットチェック
- web serverの起動もdockerで行うようにする

## 実行

```bash
# mysqlとmongodbを起動する
# NOTE: m1 apple silicon での動作を想定しているため, それ以外の規格のCPUでの動作確認はしていない
docker-compose up -d

# jarを生成する
sbt assembly

# web server起動
java -jar ./core/infrastructure/target/scala-2.13/coreInfrastructure-assembly-0.1.0.jar
```
