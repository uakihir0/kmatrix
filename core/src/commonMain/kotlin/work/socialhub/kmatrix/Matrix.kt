package work.socialhub.kmatrix

import work.socialhub.kmatrix.api.AccountsResource
import work.socialhub.kmatrix.api.DevicesResource
import work.socialhub.kmatrix.api.DirectoryResource
import work.socialhub.kmatrix.api.FilterResource
import work.socialhub.kmatrix.api.LoginResource
import work.socialhub.kmatrix.api.MediaResource
import work.socialhub.kmatrix.api.PresenceResource
import work.socialhub.kmatrix.api.ProfileResource
import work.socialhub.kmatrix.api.RoomsResource
import work.socialhub.kmatrix.api.SyncResource
import work.socialhub.kmatrix.api.TagsResource
import kotlin.js.JsExport

@JsExport
interface Matrix {

    fun login(): LoginResource
    fun accounts(): AccountsResource
    fun profile(): ProfileResource
    fun rooms(): RoomsResource
    fun sync(): SyncResource
    fun filter(): FilterResource
    fun presence(): PresenceResource
    fun media(): MediaResource
    fun devices(): DevicesResource
    fun directory(): DirectoryResource
    fun tags(): TagsResource

    fun uri(): String
    fun accessToken(): String
}
