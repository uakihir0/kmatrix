package work.socialhub.kmatrix.stream.internal

import work.socialhub.kmatrix.stream.MatrixStream

class MatrixStreamImpl(
    private val uri: String,
    private val accessToken: String,
) : MatrixStream {

    override fun uri() = uri
    override fun accessToken() = accessToken
}
