package work.socialhub.kmatrix.internal

import work.socialhub.kmatrix.Matrix
import work.socialhub.kmatrix.api.RoomsResource

class MatrixImpl(
    private val uri: String,
    private val accessToken: String,
) : Matrix {

    private val rooms: RoomsResource = RoomsResourceImpl(uri, accessToken)

    /**
     * get uri
     */
    override fun uri() = uri

    /**
     * get access token
     */
    override fun accessToken() = accessToken

    override fun rooms() = rooms
}
