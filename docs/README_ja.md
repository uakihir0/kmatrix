# kmatrix

![Maven metadata URL](https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Frepo.repsy.io%2Fmvn%2Fuakihir0%2Fpublic%2Fwork%2Fsocialhub%2Fkmatrix%2Fcore%2Fmaven-metadata.xml)

![badge][badge-js]
![badge][badge-jvm]
![badge][badge-ios]
![badge][badge-mac]

**このライブラリは [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html) に対応した Matrix クライアントライブラリです。**
[khttpclient] を依存関係に持っており、内部で Ktor Client を使用しています。
そのため、本ライブラリは、Kotlin Multiplatform かつ Ktor Client がサポートしているプラットフォームであれば利用可能です。
各プラットフォームでどのような挙動をするのかについては、[khttpclient] に依存します。

## 使い方

以下は対応するプラットフォームにおいて Gradle を用いて Kotlin で使用する際の使い方になります。
**Apple プラットフォームで使用する場合は、[kmatrix-cocoapods](https://github.com/uakihir0/kmatrix-cocoapods) または [kmatrix-spm](https://github.com/uakihir0/kmatrix-spm) を参照してください。**
**また、JavaScript での使い方については、[kmatrix.js](https://github.com/uakihir0/kmatrix.js) を参照してください。**
テストコードも合わせて確認してください。

```kotlin:build.gradle.kts
repositories {
    mavenCentral()
+   maven { url = uri("https://repo.repsy.io/mvn/uakihir0/public") }
}

dependencies {
+   implementation("work.socialhub.kmatrix:core:0.0.1-SNAPSHOT")
+   implementation("work.socialhub.kmatrix:stream:0.0.1-SNAPSHOT")
}
```

### 通常の Java プロジェクトで使用する場合

上記はすべて通常の Java プロジェクトにも追加して使用できます。依存関係にサフィックス `-jvm` を付けるだけです。

Maven の設定例:

```xml
<dependency>
    <groupId>work.socialhub.kmatrix</groupId>
    <artifactId>core-jvm</artifactId>
    <version>[VERSION]</version>
</dependency>
```

### 認証

まず、サーバーがサポートしているログインフローを取得します。

```kotlin
val matrix = MatrixFactory.instance("https://matrix.example.com")

val response = matrix.login().getLoginFlows()
println(response.data.flows)
```

次に、ユーザー ID とパスワードでログインしてアクセストークンを取得します。

```kotlin
val response = matrix.login().loginWithPassword(
    LoginPasswordRequest().also {
        it.user = "@user:matrix.example.com"
        it.password = "PASSWORD"
    }
)

println(response.data.accessToken)
```

### メッセージ送信

```kotlin
val matrix = MatrixFactory.instance(
    "https://matrix.example.com", "ACCESS_TOKEN"
)

matrix.rooms().sendMessage(
    RoomsSendMessageRequest().also {
        it.roomId = "!roomId:matrix.example.com"
        it.body = "Hello World!"
    }
)
```

### 参加中のルーム取得

```kotlin
val matrix = MatrixFactory.instance(
    "https://matrix.example.com", "ACCESS_TOKEN"
)

val response = matrix.rooms().getJoinedRooms()
println(response.data.joinedRooms)
```

## ライセンス

MIT License

## 作者

[Akihiro Urushihara](https://github.com/uakihir0)

[khttpclient]: https://github.com/uakihir0/khttpclient
[badge-android]: http://img.shields.io/badge/-android-6EDB8D.svg
[badge-android-native]: http://img.shields.io/badge/support-[AndroidNative]-6EDB8D.svg
[badge-wearos]: http://img.shields.io/badge/-wearos-8ECDA0.svg
[badge-jvm]: http://img.shields.io/badge/-jvm-DB413D.svg
[badge-js]: http://img.shields.io/badge/-js-F8DB5D.svg
[badge-js-ir]: https://img.shields.io/badge/support-[IR]-AAC4E0.svg
[badge-nodejs]: https://img.shields.io/badge/-nodejs-68a063.svg
[badge-linux]: http://img.shields.io/badge/-linux-2D3F6C.svg
[badge-windows]: http://img.shields.io/badge/-windows-4D76CD.svg
[badge-wasm]: https://img.shields.io/badge/-wasm-624FE8.svg
[badge-apple-silicon]: http://img.shields.io/badge/support-[AppleSilicon]-43BBFF.svg
[badge-ios]: http://img.shields.io/badge/-ios-CDCDCD.svg
[badge-mac]: http://img.shields.io/badge/-macos-111111.svg
[badge-watchos]: http://img.shields.io/badge/-watchos-C0C0C0.svg
[badge-tvos]: http://img.shields.io/badge/-tvos-808080.svg
