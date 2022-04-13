# プロジェクト構成

- root
    - lib (自作ライブラリ)
    - shared (共有カーネル)
        - application
        - domain
        - infrastructure
    - auth (認証コンテキスト)
        - application
        - domain
        - infrastructure
    - core (コアコンテキスト)
        - application
        - domain
        - infrastructure

## アーキテクチャ
ヘキサゴナルアーキテクチャ.

各コンテキストにおけるレイヤーについて
- infrastructure: アダプターとポート. http通信, DB, 暗号化処理, ファイル読み込み
- application: ユースケース. 基本的にトランザクション単位
- domain: ドメインモデル

## 依存関係

### コンテキスト間
- core -> lib, shared, auth
- auth -> lib, shared
- shared -> lib

### レイヤー間
- infrastructure -> domain, application
- application -> domain
