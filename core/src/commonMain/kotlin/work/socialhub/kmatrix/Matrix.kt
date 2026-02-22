package work.socialhub.kmatrix

import work.socialhub.kmatrix.api.AccountsResource
import work.socialhub.kmatrix.api.LoginResource
import work.socialhub.kmatrix.api.ProfileResource
import work.socialhub.kmatrix.api.RoomsResource
import work.socialhub.kmatrix.api.SyncResource
import kotlin.js.JsExport

@JsExport
interface Matrix {

    fun login(): LoginResource
    fun accounts(): AccountsResource
    fun profile(): ProfileResource
    fun rooms(): RoomsResource
    fun sync(): SyncResource

    fun uri(): String
    fun accessToken(): String
}
