# Agent Documentation

## Overview

This repository is a Matrix Client-Server API client library for Kotlin Multiplatform. It provides REST API bindings for Matrix homeservers and a streaming module for real-time synchronization.

## Key Concepts

### Matrix Client-Server API Structure

Most REST API calls are made to versioned endpoints such as `/_matrix/client/v3/*` and use JSON request/response bodies.

Common endpoint categories:

- `joined_rooms` - List joined rooms
- `rooms/*` - Room operations (creation, state, membership)
- `rooms/*/send/*` - Sending messages and events
- `rooms/*/state/*` - Room state management
- `sync` - Event synchronization (long-polling)
- `login` - Authentication
- `register` - Account registration
- `profile/*` - User profile operations
- `media/*` - Media upload/download
- `search` - Search messages and rooms
- `keys/*` - End-to-end encryption key management

### Authentication

Matrix uses bearer tokens:

1. Authenticate via `POST /_matrix/client/v3/login` (password, SSO, or OAuth2)
2. Receive an `access_token` in the response
3. Use the access token in the `Authorization: Bearer <token>` header

### Sync API

Real-time synchronization uses the `/sync` endpoint with long-polling:

- `GET /_matrix/client/v3/sync` (query parameters include `since`, `timeout`, `filter`)

The sync response includes:

- `rooms.join` - Joined room updates (timeline events, state, ephemeral)
- `rooms.invite` - Room invitations
- `rooms.leave` - Left rooms
- `presence` - Presence updates
- `account_data` - Account data changes

Matrix specification: https://spec.matrix.org/latest/client-server-api/

## Directory Structure

- **`core/`**: REST API client library
  - `api/` - Resource interfaces
    - `request/` - Request objects
    - `response/` - Response objects
  - `entity/` - Data models (Room, etc.)
  - `internal/` - Internal implementations
  - `util/` - Utilities (serialization, helpers)
- **`stream/`**: Sync/Streaming API (future: `/sync` long-polling)
- **`all/`**: Package containing all modules (for platform distribution)
- **`plugins/`**: Gradle build configuration
- **`tool/`**: Auxiliary tooling

## Testing

Run all core tests:

```shell
./gradlew :core:jvmTest
```

Run specific tests:

```shell
./gradlew :core:jvmTest --tests "work.socialhub.kmatrix.rooms.GetJoinedRoomsTest"
```

If network access is not available, verify successful build:

```shell
./gradlew jvmJar
```

If authentication credentials are required for tests, create `secrets.json` based on `secrets.json.default`.

## Implementation Guidelines

### Endpoint and Package Mapping

API endpoints correspond to resource interfaces and request/response objects:

- `GET /_matrix/client/v3/joined_rooms` → `RoomsResource` + `RoomsGetJoinedRoomsRequest`
- `POST /_matrix/client/v3/createRoom` → `RoomsResource` + `RoomsCreateRoomRequest`
- `GET /_matrix/client/v3/sync` → (future) `SyncResource` + `SyncRequest`

When implementing or updating APIs, refer to the official Matrix specification: https://spec.matrix.org/latest/client-server-api/

### Steps to Add a New API

1. Add or update request/response models in `core/src/commonMain/kotlin/work/socialhub/kmatrix/api/request/` and `.../response/`.
2. Add the method to the appropriate resource interface in `api/`.
3. Update internal implementations under `internal/`.
4. Register the resource in `MatrixImpl` and expose it via the `Matrix` interface.
5. Add or update tests in `core/src/jvmTest/kotlin/`.

### Steps to Add a New Resource Group

1. Create `{Resource}Resource.kt` interface in `api/` with `suspend` and `Blocking` method pairs.
2. Create `{Resource}ResourceImpl.kt` in `internal/` extending `AbstractAuthResourceImpl`.
3. Create request/response DTOs in `api/request/{resource}/` and `api/response/{resource}/`.
4. Add the resource field and accessor to `MatrixImpl` and `Matrix` interface.

### Naming Conventions

| Type     | Naming Pattern         | Example                        |
| -------- | ---------------------- | ------------------------------ |
| Request  | `{ActionName}Request`  | `RoomsGetJoinedRoomsRequest`   |
| Response | `{ActionName}Response` | `RoomsGetJoinedRoomsResponse`  |
| Resource | `{Category}Resource`   | `RoomsResource`                |
| Entity   | Singular form          | `Room`                         |

### Serialization

All models use `kotlinx.serialization`. Keep request and response field names aligned with Matrix API specifications (snake_case in JSON, camelCase in Kotlin with `@SerialName`).

## Key File References

| Purpose                 | File Path                                                                            |
| ----------------------- | ------------------------------------------------------------------------------------ |
| Main client interface   | `core/src/commonMain/kotlin/work/socialhub/kmatrix/Matrix.kt`                        |
| Factory                 | `core/src/commonMain/kotlin/work/socialhub/kmatrix/MatrixFactory.kt`                 |
| Resource interfaces     | `core/src/commonMain/kotlin/work/socialhub/kmatrix/api/`                             |
| Request/response models | `core/src/commonMain/kotlin/work/socialhub/kmatrix/api/request/` and `.../response/` |
| Streaming/Sync API      | `stream/src/commonMain/kotlin/work/socialhub/kmatrix/stream/`                        |
