package work.socialhub.kmatrix

import work.socialhub.kmatrix.internal.MatrixImpl
import kotlin.js.JsExport

@JsExport
object MatrixFactory {

    /**
     * get request instance
     */
    fun instance(
        uri: String,
        accessToken: String = "",
    ): Matrix {
        return MatrixImpl(uri, accessToken)
    }
}
