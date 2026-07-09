package work.socialhub.kmatrix.internal

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

object AnySerializer : KSerializer<Any> {

    var additionalSerializer: (Encoder, Any) -> Boolean = { _, _ -> false }

    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("Any")

    override fun deserialize(decoder: Decoder): Any {
        require(decoder is JsonDecoder)
        val element = decoder.decodeJsonElement()
        // A top-level null is handled by the nullable wrapper before reaching
        // here; guard defensively so the return type stays non-null.
        return element.toAny() ?: throw IllegalStateException("Unexpected null value")
    }

    private fun JsonElement.toAny(): Any? {
        when (this) {
            // A JSON null nested inside an object/array (e.g. `avatar_url: null`
            // in an m.room.member event) — keep it as null instead of throwing.
            is JsonNull -> return null

            is JsonPrimitive -> {
                return when {
                    this.isString -> this.content
                    this.intOrNull != null -> this.int
                    this.longOrNull != null -> this.long
                    this.floatOrNull != null -> this.float
                    this.doubleOrNull != null -> this.double
                    this.booleanOrNull != null -> this.boolean
                    else -> throw IllegalStateException("Can't deserialize unknown type: $this")
                }
            }

            is JsonArray -> {
                return this.map { it.toAny() }
            }

            is JsonObject -> {
                return this.map { it.key to it.value.toAny() }.toMap()
            }
        }
    }

    override fun serialize(encoder: Encoder, value: Any) {
        when (value) {
            is Int -> encoder.encodeInt(value)
            is Long -> encoder.encodeLong(value)
            is Short -> encoder.encodeShort(value)
            is Float -> encoder.encodeFloat(value)
            is Double -> encoder.encodeDouble(value)
            is String -> encoder.encodeString(value)
            is Boolean -> encoder.encodeBoolean(value)
            is Enum<*> -> encoder.encodeString(this.toString())

            else -> {
                if (!additionalSerializer(encoder, value)) {
                    println("Can't serialize unknown type: ${value::class}")
                }
            }
        }
    }
}
