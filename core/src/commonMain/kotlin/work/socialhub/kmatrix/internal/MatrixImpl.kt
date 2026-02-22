package work.socialhub.kmatrix.internal

import work.socialhub.kmatrix.Matrix
import work.socialhub.kmatrix.api.AccountsResource
import work.socialhub.kmatrix.api.LoginResource
import work.socialhub.kmatrix.api.RoomsResource

class MatrixImpl(
    private val uri: String,
    private val accessToken: String,
) : Matrix {

    private val login: LoginResource = LoginResourceImpl(uri)
    private val accounts: AccountsResource = AccountsResourceImpl(uri, accessToken)
    private val rooms: RoomsResource = RoomsResourceImpl(uri, accessToken)

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
    override fun rooms() = rooms
}
