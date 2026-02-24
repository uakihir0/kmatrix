package work.socialhub.kmatrix

import work.socialhub.kmatrix.stream.MatrixStreamFactory
import kotlin.js.JsExport

@JsExport
object KmatrixFactory {

    fun instance(
        uri: String,
        accessToken: String = "",
    ) = MatrixFactory.instance(uri, accessToken)

    fun stream(
        uri: String,
        accessToken: String = "",
    ) = MatrixStreamFactory.instance(uri, accessToken)
}
