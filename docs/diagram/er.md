# ER図

注意点
- 各Entityに対しての created_at, updated_at, is_deleted は省略する
- PKやFK,UQの通常の書き方だとmermaidのプレビュー表示が崩れるので`field--PK`みたいな書き方にしている.

## Auth

RDBMS(mysql)を想定

```mermaid
erDiagram

User {
    string user_id--PK
    string email--UQ
    string hashed_password
    string salt
}

Session {
    string session_id--PK
    string user_id--FK
    date expire_at
}

User ||--o{ Session : "1:0..N"

```

## Core

補足

[各オブジェクトの状態](/docs/usecase.md#各オブジェクトの状態)

### ER図

```mermaid
erDiagram

Buyer {
    string buyer_id--PK
    string email--UQ
    string buyer_name
}

Seller {
    string seller_id--PK
    string email--UQ
    string seller_name
}

Pet {
    string pet_id--PK
    string seller_id--FK
    string pet_name
    int price
    string status
    string description
}

Category {
    string category_id--PK
    string category_name
}

RelayPetCategory {
    string pet_id--PKFK
    string category_id--PKFK
}

Order {
    string order_id--PK
    string buyer_id--FK
    string seller_id--FK
    string status
}

RelayOrderPet {
    string order_id--PKFK
    string pet_id--PKFK
}


Seller ||--o{ Pet: "1:0..N"
Pet ||--o{ RelayPetCategory: "1:0..N"
Category ||--o{ RelayPetCategory: "1:0..N"

Buyer ||--o{ Order: "1:0..N"
Order ||--|{ RelayOrderPet: "1:1..N"
Pet ||--o{ RelayOrderPet: "1:0..N"

```

### コマンドモデル

ドキュメント指向DB(mongodb)を想定.

- 販売者, 購入者に関しては単なるCRUDを想定.また, authとの同期処理が必要なので, イベントソーシングではなくドキュメントを直更新する.
- ペット情報, 注文(とカート)に関してはイベントソーシングの想定.イベントとスナップショットを保存する.

NOTE: 集約をそのまま保存するのはスナップショットを保存することと同じことになるが, 実装がかなり楽なってしまうので今回はしない.イベントの取得N回毎にスナップショットを作成する


### クエリモデル

一旦コマンドモデルで利用するmongodbのslaveを想定.

TODO: mysqlで最新状態を表す読み込み用のDBを作る.履歴関連はコマンドモデルで利用しているDBのslaveを使う.計算済みの結果の格納や非正規化しても良さそうではある.ほぼER図と同様のテーブル構成になりそう.
