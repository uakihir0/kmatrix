package work.socialhub.kmatrix

import kotlinx.serialization.json.Json
import java.io.File
import kotlin.test.BeforeTest

open class AbstractTest {

    companion object {
        var HOST: String? = null
        var ACCESS_TOKEN: String? = null
    }

    protected val json = Json {
        ignoreUnknownKeys = true
    }

    fun matrix(): Matrix {
        return MatrixFactory.instance(
            HOST!!, ACCESS_TOKEN!!
        )
    }

    fun matrixNoAuth(): Matrix {
        return MatrixFactory.instance(
            HOST ?: "https://matrix.org"
        )
    }

    @BeforeTest
    fun setupTest() {

        try {
            // Get credentials from environment variables.
            HOST = System.getenv("MATRIX_HOST")
                ?: System.getProperty("MATRIX_HOST")
            ACCESS_TOKEN = System.getenv("MATRIX_ACCESS_TOKEN")
                ?: System.getProperty("MATRIX_ACCESS_TOKEN")
        } catch (_: Exception) {
        }

        if (HOST == null || ACCESS_TOKEN == null) {
            try {
                // Get credentials from secrets.json file.
                readTestProps()?.get("matrix")?.let {
                    HOST = it["MATRIX_HOST"]
                    ACCESS_TOKEN = it["MATRIX_ACCESS_TOKEN"]
                }
            } catch (_: Exception) {
            }
        }
    }

    /**
     * Read Test Properties
     */
    private fun readTestProps(): Map<String, Map<String, String>>? {
        return try {
            val jsonStr = File("../secrets.json").readText()
            json.decodeFromString<Map<String, Map<String, String>>>(jsonStr)
        } catch (e: Exception) {
            null
        }
    }
}
