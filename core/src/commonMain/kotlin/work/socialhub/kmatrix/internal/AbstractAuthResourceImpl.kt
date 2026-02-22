package work.socialhub.kmatrix.internal

abstract class AbstractAuthResourceImpl(
    uri: String,
    val accessToken: String,
) : AbstractResourceImpl(uri) {

    fun bearerToken(): String {
        return "Bearer $accessToken"
    }
}
