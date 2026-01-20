package org.multipaz.testapp

import org.multipaz.cbor.DataItem
import org.multipaz.cbor.buildCborMap
import org.multipaz.cbor.CborMap
import org.multipaz.cbor.Tstr
import org.multipaz.cbor.Nint
import org.multipaz.cbor.Uint

data class ShowResponseMetadata(
    val engagementType: String,
    val transferProtocol: String,
    val requestSize: Long,
    val responseSize: Long,
    val durationMsecNfcTapToEngagement: Long?,
    val durationMsecEngagementReceivedToRequestSent: Long?,
    val durationMsecRequestSentToResponseReceived: Long
) {
    fun toDataItem(): DataItem {
        return buildCborMap {
            put("engagementType", engagementType)
            put("transferProtocol", transferProtocol)
            put("requestSize", requestSize)
            put("responseSize", responseSize)
            if (durationMsecNfcTapToEngagement != null) {
                put("durationMsecNfcTapToEngagement", durationMsecNfcTapToEngagement)
            }
            if (durationMsecEngagementReceivedToRequestSent != null) {
                put("durationMsecEngagementReceivedToRequestSent", durationMsecEngagementReceivedToRequestSent)
            }
            put("durationMsecRequestSentToResponseReceived", durationMsecRequestSentToResponseReceived)
        }
    }

    companion object {
        fun fromDataItem(dataItem: DataItem): ShowResponseMetadata {
            require(dataItem is CborMap)
            return ShowResponseMetadata(
                engagementType = (dataItem["engagementType"] as Tstr).value,
                transferProtocol = (dataItem["transferProtocol"] as Tstr).value,
                requestSize = dataItem["requestSize"].asNumber,
                responseSize = dataItem["responseSize"].asNumber,
                durationMsecNfcTapToEngagement = dataItem["durationMsecNfcTapToEngagement"]?.asNumber,
                durationMsecEngagementReceivedToRequestSent = dataItem["durationMsecEngagementReceivedToRequestSent"]?.asNumber,
                durationMsecRequestSentToResponseReceived = dataItem["durationMsecRequestSentToResponseReceived"].asNumber
            )
        }
    }
}
