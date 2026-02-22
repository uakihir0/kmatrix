package work.socialhub.kmatrix

import work.socialhub.kmatrix.api.RoomsResource
import kotlin.js.JsExport

@JsExport
interface Matrix {

    fun rooms(): RoomsResource

    fun uri(): String
    fun accessToken(): String
}
