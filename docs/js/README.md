> [日本語](./README_ja.md)

# kmatrix (npm)

This repository is the npm repository for [kmatrix]. [kmatrix] is a Matrix protocol client library created using Kotlin Multiplatform.
Therefore, it can be used in web applications and similar contexts.
Additionally, this repository is automatically committed by GitHub Actions of [kmatrix]. Please submit issues or pull requests to [kmatrix].

## Usage

### Installation

If you're managing with npm, you can add it to your application using the following command.
There are no versions in this repository, but there are branches corresponding to [kmatrix] versions.
Please check the branches on the [branch list](https://github.com/uakihir0/kmatrix.js/branches) to find the branch corresponding to the version you want to use.

```shell
npm add uakihir0/kmatrix.js
or
npm add uakihir0/kmatrix.js#{{BRANCH_NAME}}
```

### Basic Usage

TypeScript type information is included, so it's recommended to write in TypeScript.
Please also refer to the README of [kmatrix] for detailed usage.

#### Authentication

First, get the login flows supported by the server.

```typescript
import kmatrix from "kmatrix-js";
const MatrixFactory = kmatrix.work.socialhub.kmatrix.MatrixFactory;

const matrix = MatrixFactory.instance("https://matrix.example.com");

const response = await matrix.login().getLoginFlows();
console.log(response.data.flows);
```

Then, log in with a user ID and password to obtain an access token.

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

#### Send Message

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

#### Get Joined Rooms

```typescript
import kmatrix from "kmatrix-js";
const MatrixFactory = kmatrix.work.socialhub.kmatrix.MatrixFactory;

const matrix = MatrixFactory.instance("https://matrix.example.com", "ACCESS_TOKEN");

const response = await matrix.rooms().getJoinedRooms();
console.log(response.data.joinedRooms);
```

## License

MIT License

## Author

[Akihiro Urushihara](https://github.com/uakihir0)

[kmatrix]: https://github.com/uakihir0/kmatrix
