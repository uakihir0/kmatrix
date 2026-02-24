package work.socialhub.kmatrix.stream

import kotlin.js.JsExport

@JsExport
interface MatrixStream {

    fun uri(): String
    fun accessToken(): String
}
