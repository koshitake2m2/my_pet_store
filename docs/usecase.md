# ユースケース

## アクター
- admin: アプリ運営者
- seller: 販売者
- buyer: 購入者

## アプリの概要
このアプリはオンラインペットショップであり、購入者がアプリでペットを予約して、販売者の元まで受け取りに行くためのサポートをする。

以下はざっくりしたペット販売から購入までの流れである。すべてをシステム化しているわけではなく購入の予約までをアプリで行い、オフラインでペットを受け取る想定である。

1. 販売者がペット情報を掲載する
2. 購入者がペット情報をカートに追加していく
3. 購入者が注文する
4. 販売者が購入者の注文を承認する。システムが購入者宛にメール送信する
5. オフライン: 購入者はメールを受け取ったら販売者の元へペットを受け取りに行く
6. 販売者はペット情報のステータスを売り切れにする

## 各オブジェクトの状態

- Pet: ペット情報
  - status
    - stop: 販売停止中
    - selling: 販売中
    - pending: 承認待ち. 以降、ペット情報の更新・削除ができなくなる.
    - dealing: 取引中.
    - sold_out: 売り切れ.
- Order: 注文
  - status
    - selecting: ペット情報選択中
    - pending: 承認待ち. 以降、注文の追加・削除ができなくなる
    - dealing: 取引中
    - completed: 完了済み
    - canceled: キャンセル済み

## アクター毎のユースケース

ユースケースはクエリとコマンドを明確に分ける。

以下のような表記でユースケースを表す。 また、クエリはQ、コマンドはCと表記する。
また、コマンドに関しては実行後にユースケースと似たような名前のドメインイベントが発行される想定である。

```markdown
### アクター
- コンテキスト
  - パッケージ
    - C or Q: ユースケース英名: ユースケース和名
      - 処理の補足
      - ちなみにイベント名はユースケースの動詞の部分が過去形のもの
```

### 共通
authはCQRSではない

- auth
  - validateSession: セッション有効判定
  - registerUser: ユーザ登録(coreから呼ばれる)
  - login: ログイン
  - logout: ログアウト

※やらないこと・低優先
- auth
  - ユーザ削除
  - セッション切れ削除バッチ
  - csrf周り

### admin
※今回は作らない


### seller
- core
  - seller
    - 販売者新規登録(auth.registerを利用する)
      - SellerRepository.save()
      - auth.UserRepository.save()
  - pet management: ペット情報管理(イベントソーシング)
    - Q: findPets: ペット情報一覧取得
    - Q: findDetailPet: ペット情報詳細取得
    - Q: findUpdatePetHistory: ペット情報更新履歴取得
    - C: registerPet: ペット情報を登録する
    - C: updatePet: ペット情報を更新する
    - C: rollbackPet: 過去の履歴からロールバックする(ペット情報がstop,selling中のみ)
    - C: deletePet: ペット情報を削除する
  - order management: 注文管理(イベントソーシング)
    - Q: findOrders: 注文一覧取得
    - Q: findDetailOrder: 注文詳細取得
    - C: approveOrder: 注文を承認する
      - 該当のペット情報をdealingにする
      - 該当の注文をdealingにする
    - C: completeOrder: 注文を完了する
      - 該当のペット情報はsold_outになる
    - C: cancelOrder: 注文をキャンセルする
      - 他のbuyerでも承認待ちがあるペット情報はpendingになる
      - 他のbuyerには承認待ちがないペット情報はsellingになる

※やらないこと・低優先
- core
  - seller
    - 販売者退会
    - 販売者プロフィール更新
  - pet management
    - ペット情報絞り込み検索
  - order management
    - 注文絞り込み検索
    - 注文承認時のメールの実配信. モックで対応する
    - 完了済み・キャンセル済みの注文のステータス変更
  - その他
    - カテゴリ自体の追加・編集・削除


### buyer
- core
  - buyer
    - 購入者新規登録(auth.registerを利用する)
      - BuyerRepository.save()
      - auth.UserRepository.save()
  - browse pet
    - Q: findPets: ペット情報一覧取得
    - Q: findDetailPet: ペット情報詳細取得
  - cart management(イベントソーシング)
    - Q: findCart: カートを取得する
    - C: addPetToCart: ペット情報をカートに追加する
      - 販売者に対してのカートが存在しない場合は作成する
    - C: deletePetFromCart: ペット情報をカートから削除する
      - カートが空になったらカートを削除する
    - C: orderInCart: カートで注文を確定する(注文は販売者毎)
      - 注文をpendingにする
      - カートに入ってたペット情報をpendingにする
  - order management(イベントソーシング)
    - Q: findOrders: 注文一覧取得
    - Q: findDetailOrder: 注文詳細取得
    - C: cancelOrder: 注文をキャンセルする
      - order.status=canceled
      - 他のbuyerでも承認待ちがあるペット情報はpendingになる
      - 他のbuyerには承認待ちがないペット情報はsellingになる

※やらないこと・低優先
- core
  - buyer
    - 購入者退会
    - 購入者プロフィール更新
  - browse pet
    - ペット情報絞り込み検索
    - ペット情報お気に入り登録・閲覧・削除
  - cart management
    - カート注文前の確認をする
  - order management
    - 注文絞り込み検索
