> [English](./README.md)

# kmatrix (npm)

本レポジトリは、[kmatrix] の npm レポジトリです。[kmatrix] は Kotlin Multiplatform を用いて作成された Matrix プロトコルクライアントライブラリです。
そのため、Web アプリケーション等でも使用していただくことができます。
また、このレポジトリは [kmatrix] の GitHub Actions によって自動コミットされています。issue や pull request は [kmatrix] にお願いします。

## 使用方法

### 追加方法

npm で管理している場合、以下のコマンドでアプリケーションに追加することができます。
本レポジトリにはバージョンは存在せず、[kmatrix] のバージョンと一致するブランチが存在します。
どのバージョンの [kmatrix] を使用するかは、本レポジトリのブランチを指定することで決定します。
[ブランチ一覧](https://github.com/uakihir0/kmatrix.js/branches) からバージョンに対応するブランチを確認してください。

```shell
npm add uakihir0/kmatrix.js
or
npm add uakihir0/kmatrix.js#{{BRANCH_NAME}}
```

### 基本的な使い方

TypeScript の型情報も含まれており、TypeScript での記述をオススメします。
詳しい使い方については、[kmatrix] の README も合わせて確認してください。

#### 認証

まず、サーバーがサポートするログインフローを取得します。

```typescript
import kmatrix from "kmatrix-js";
const MatrixFactory = kmatrix.work.socialhub.kmatrix.MatrixFactory;

const matrix = MatrixFactory.instance("https://matrix.example.com");

const response = await matrix.login().getLoginFlows();
console.log(response.data.flows);
```

次に、ユーザー ID とパスワードでログインし、アクセストークンを取得します。

```typescript
import kmatrix from "kmatrix-js";
const MatrixFactory = kmatrix.work.socialhub.kmatrix.MatrixFactory;
const LoginPasswordRequest = kmatrix.work.socialhub.kmatrix.api.request.login.LoginPasswordRequest;

const matrix = MatrixFactory.instance("https://matrix.example.com");

const request = new LoginPasswordRequest();
request.user = "@user:matrix.example.com";
request.password = "PASSWORD";

const response = await matrix.login().loginWithPassword(request);
console.log(response.data.accessToken);
```

#### メッセージ送信

```typescript
import kmatrix from "kmatrix-js";
const MatrixFactory = kmatrix.work.socialhub.kmatrix.MatrixFactory;
const RoomsSendMessageRequest = kmatrix.work.socialhub.kmatrix.api.request.rooms.RoomsSendMessageRequest;

const matrix = MatrixFactory.instance("https://matrix.example.com", "ACCESS_TOKEN");

const request = new RoomsSendMessageRequest();
request.roomId = "!roomId:matrix.example.com";
request.body = "Hello World!";

await matrix.rooms().sendMessage(request);
```

#### 参加済みルーム一覧の取得

```typescript
import kmatrix from "kmatrix-js";
const MatrixFactory = kmatrix.work.socialhub.kmatrix.MatrixFactory;

const matrix = MatrixFactory.instance("https://matrix.example.com", "ACCESS_TOKEN");

const response = await matrix.rooms().getJoinedRooms();
console.log(response.data.joinedRooms);
```

## ライセンス

MIT License

## 作者

[Akihiro Urushihara](https://github.com/uakihir0)

[kmatrix]: https://github.com/uakihir0/kmatrix
