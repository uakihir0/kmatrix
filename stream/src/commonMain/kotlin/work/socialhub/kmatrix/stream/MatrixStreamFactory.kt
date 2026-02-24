package work.socialhub.kmatrix.stream

import work.socialhub.kmatrix.stream.internal.MatrixStreamImpl
import kotlin.js.JsExport

@JsExport
object MatrixStreamFactory {

    /**
     * get MatrixStream instance
     */
    fun instance(
        uri: String,
        accessToken: String = "",
    ): MatrixStream {
        return MatrixStreamImpl(uri, accessToken)
    }
}
