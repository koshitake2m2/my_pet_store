# シーケンス図(認証周りのユースケース)

簡略化のため最低限の正常系を書いている。

## 登録

- Client: Coreの販売者や購入者を登録するユースケース等のクライアント
- Register: Authのユースケース
- AuthDB: 認証情報を保持したDB

```mermaid
sequenceDiagram

Client->>Register: register, IN { email, password }
Register->>AuthDB: find user by email
AuthDB->>Register: maybeUser
alt user is found
    Register->>Client: error response
end
Register->>Register: hash input password
Register->>AuthDB: save user
Register->>Client: response

```


## ログイン

- Client: フロントエンド
- Login: Authのユースケース
- AuthDB: 認証情報を保持したDB

```mermaid
sequenceDiagram

Client->>Login: login, IN { email, password }
Login->>AuthDB: find user by email
AuthDB->>Login: maybeUser
Login->>Login: hash input password and validate
alt not found user or invalid password
    Login->>Client: error response
end
Login->>AuthDB: delete old session id
Login->>Login: create new session id
Login->>AuthDB: add old session id
Login->>Client: cookie with encrypted session id

```

## 認証が必要なユースケース

- Client: フロントエンド
- DoSomething: Coreのユースケース
- ValidateSession: Authのユースケース
- AuthDB: 認証情報を保持したDB

```mermaid
sequenceDiagram

Client->>DoSomething: doSomething, IN: { cookie.session_id }
DoSomething->>ValidateSession: validate session, IN: { session_id }
ValidateSession->>ValidateSession: decrypt session id
ValidateSession->>AuthDB: find session by id
AuthDB->>ValidateSession: maybeSessionAndUserEmail
ValidateSession->>ValidateSession: validate session
alt invalid session
    ValidateSession->>DoSomething: error response
    DoSomething->>Client: error response
end
ValidateSession->>DoSomething: userEmail
DoSomething->>DoSomething: do something!!!
DoSomething->>Client: response
```


## ログアウト

- Client: フロントエンド
- Logout: Authのユースケース
- AuthDB: 認証情報を保持したDB

```mermaid
sequenceDiagram

Client->>Logout: logout, IN: { cookie.session_id }
Logout->>Logout: decrypt session id
Logout->>AuthDB: find session by id
AuthDB->>Logout: maybeSession
Logout->>Logout: validate session
alt invalid session
    Logout->>Client: error response
end
Logout->>AuthDB: delete session id
Logout->>Client: response
Client->>Client: delete cookie with hashed session id
```
