> [日本語](./docs/README_ja.md)

# kmatrix

![Maven metadata URL](https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Frepo.repsy.io%2Fmvn%2Fuakihir0%2Fpublic%2Fwork%2Fsocialhub%2Fkmatrix%2Fcore%2Fmaven-metadata.xml)

![badge][badge-js]
![badge][badge-jvm]
![badge][badge-ios]
![badge][badge-mac]

**This library is a Matrix client library that supports [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html).**
It depends on [khttpclient] and internally uses Ktor Client.
Therefore, this library is available on Kotlin Multiplatform and platforms supported by Ktor Client.
The behavior on each platform depends on [khttpclient].

## Usage

Below is how to use it in Kotlin with Gradle on supported platforms.
**If you want to use it on Apple platforms, please refer to [kmatrix-cocoapods](https://github.com/uakihir0/kmatrix-cocoapods) or [kmatrix-spm](https://github.com/uakihir0/kmatrix-spm).**
**Also, for usage in JavaScript, please refer to [kmatrix.js](https://github.com/uakihir0/kmatrix.js).**
Please refer to the test code for how to use each API.

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

### Using as part of a regular Java project

All of the above can be added to and used in regular Java projects, too. All you have to do is to use the suffix `-jvm` when listing the dependency.

Here is a sample Maven configuration:

```xml
<dependency>
    <groupId>work.socialhub.kmatrix</groupId>
    <artifactId>core-jvm</artifactId>
    <version>[VERSION]</version>
</dependency>
```

### Authentication

First, get the login flows supported by the server.

```kotlin
val matrix = MatrixFactory.instance("https://matrix.example.com")

val response = matrix.login().getLoginFlows()
println(response.data.flows)
```

Then, log in with a user ID and password to obtain an access token.

```kotlin
val response = matrix.login().loginWithPassword(
    LoginPasswordRequest().also {
        it.user = "@user:matrix.example.com"
        it.password = "PASSWORD"
    }
)

println(response.data.accessToken)
```

### Send Message

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

### Get Joined Rooms

```kotlin
val matrix = MatrixFactory.instance(
    "https://matrix.example.com", "ACCESS_TOKEN"
)

val response = matrix.rooms().getJoinedRooms()
println(response.data.joinedRooms)
```

## License

MIT License

## Author

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
