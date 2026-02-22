package work.socialhub.kmatrix

import work.socialhub.kmatrix.api.AccountsResource
import work.socialhub.kmatrix.api.LoginResource
import work.socialhub.kmatrix.api.RoomsResource
import kotlin.js.JsExport

@JsExport
interface Matrix {

    fun login(): LoginResource
    fun accounts(): AccountsResource
    fun rooms(): RoomsResource

    fun uri(): String
    fun accessToken(): String
}
