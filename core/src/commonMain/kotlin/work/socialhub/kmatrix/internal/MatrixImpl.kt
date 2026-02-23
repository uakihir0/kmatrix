package work.socialhub.kmatrix.internal

import work.socialhub.kmatrix.Matrix
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

class MatrixImpl(
    private val uri: String,
    private val accessToken: String,
) : Matrix {

    private val login: LoginResource = LoginResourceImpl(uri)
    private val accounts: AccountsResource = AccountsResourceImpl(uri, accessToken)
    private val profile: ProfileResource = ProfileResourceImpl(uri, accessToken)
    private val rooms: RoomsResource = RoomsResourceImpl(uri, accessToken)
    private val sync: SyncResource = SyncResourceImpl(uri, accessToken)
    private val filter: FilterResource = FilterResourceImpl(uri, accessToken)
    private val presence: PresenceResource = PresenceResourceImpl(uri, accessToken)
    private val media: MediaResource = MediaResourceImpl(uri, accessToken)
    private val devices: DevicesResource = DevicesResourceImpl(uri, accessToken)
    private val directory: DirectoryResource = DirectoryResourceImpl(uri, accessToken)
    private val tags: TagsResource = TagsResourceImpl(uri, accessToken)
    private val pushRules: PushRulesResource = PushRulesResourceImpl(uri, accessToken)

    /**
     * get uri
     */
    override fun uri() = uri

    /**
     * get access token
     */
    override fun accessToken() = accessToken

    override fun login() = login
    override fun accounts() = accounts
    override fun profile() = profile
    override fun rooms() = rooms
    override fun sync() = sync
    override fun filter() = filter
    override fun presence() = presence
    override fun media() = media
    override fun devices() = devices
    override fun directory() = directory
    override fun tags() = tags
    override fun pushRules() = pushRules
}
