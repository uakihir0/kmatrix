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
import work.socialhub.kmatrix.api.PushRulesResource
import work.socialhub.kmatrix.api.SearchResource
import work.socialhub.kmatrix.api.UserDirectoryResource
import work.socialhub.kmatrix.api.VersionsResource
import work.socialhub.kmatrix.api.CapabilitiesResource
import work.socialhub.kmatrix.api.AccountDataResource
import work.socialhub.kmatrix.api.NotificationsResource
import work.socialhub.kmatrix.api.EventsResource
import work.socialhub.kmatrix.api.RelationsResource
import work.socialhub.kmatrix.api.VoIPResource
import work.socialhub.kmatrix.api.PushersResource
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
    fun pushRules(): PushRulesResource
    fun search(): SearchResource
    fun userDirectory(): UserDirectoryResource
    fun versions(): VersionsResource
    fun capabilities(): CapabilitiesResource
    fun accountData(): AccountDataResource
    fun notifications(): NotificationsResource
    fun events(): EventsResource
    fun relations(): RelationsResource
    fun voip(): VoIPResource
    fun pushers(): PushersResource

    fun uri(): String
    fun accessToken(): String
}
