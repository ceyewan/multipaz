package org.multipaz.testapp

import kotlinx.io.bytestring.ByteString
import kotlinx.serialization.json.JsonObject
import org.multipaz.cbor.DataItem
import org.multipaz.crypto.EcPrivateKey
import org.multipaz.util.UUID

data class ShowResponsePayload(
    val vpToken: JsonObject?,
    val deviceResponse: DataItem?,
    val sessionTranscript: DataItem,
    val nonce: ByteString?,
    val eReaderKey: EcPrivateKey?,
    val metadata: ShowResponseMetadata,
)

object ShowResponsePayloadStore {
    private const val MAX_ENTRIES = 4
    private val payloads = LinkedHashMap<String, ShowResponsePayload>()

    fun put(payload: ShowResponsePayload): String {
        val id = UUID.randomUUID().toString()
        payloads[id] = payload
        trimToSize()
        return id
    }

    fun get(id: String): ShowResponsePayload? = payloads[id]

    private fun trimToSize() {
        while (payloads.size > MAX_ENTRIES) {
            val oldestKey = payloads.entries.first().key
            payloads.remove(oldestKey)
        }
    }
}
