package org.multipaz.documenttype.knowntypes

import org.multipaz.cbor.toDataItem
import org.multipaz.documenttype.DocumentAttributeType
import org.multipaz.documenttype.DocumentType
import org.multipaz.documenttype.Icon

/**
 * Object containing the metadata of the Age Verification document type.
 * See https://ageverification.dev/ for more details about this document type.
 */
object AgeVerification {
    const val AV_DOCTYPE = "eu.europa.ec.av.1"
    const val AV_NAMESPACE = "eu.europa.ec.av.1"

    /**
     * Build the Age Verification Document Type.
     */
    fun getDocumentType(): DocumentType =
        with(DocumentType.Builder("Age Verification Credential")) {
            addMdocDocumentType(AV_DOCTYPE)

            // Attribute age_over_NN.
            // If we provision all 99 age_over_NN claims the MSO will be 3886 bytes which exceeds the Longfellow-ZK
            // MSO size limit of ~ 2200 bytes. With these 13 claims, the MSO is 764 bytes which is more manageable.
            val ageThresholdsToProvision = listOf(13, 15, 16, 18, 21, 23, 25, 27, 28, 40, 60, 65, 67)
            for (age in IntRange(1, 99)) {
                addMdocAttribute(
                    type = DocumentAttributeType.Boolean,
                    identifier = "age_over_${if (age < 10) "0$age" else "$age"}",
                    displayName = "年满${age}周岁",
                    description = "Indication whether the document holder is as old or older than $age",
                    mandatory = (age == 18),
                    mdocNamespace = AV_NAMESPACE,
                    icon = Icon.TODAY,
                    sampleValue =
                        if (age in ageThresholdsToProvision) {
                            (SampleData.AGE_IN_YEARS >= age).toDataItem()
                        } else {
                            null
                        }
                )
            }
            // Sample requests.
            addSampleRequest(
                id = "age_over_18",
                displayName = "年满18周岁",
                mdocDataElements = mapOf(
                    AV_NAMESPACE to mapOf(
                        "age_over_18" to false,
                    )
                )
            )
            addSampleRequest(
                id = "age_over_18_zkp",
                displayName = "年满18周岁 (ZKP)",
                mdocDataElements = mapOf(
                    AV_NAMESPACE to mapOf(
                        "age_over_18" to false,
                    )
                ),
                mdocUseZkp = true
            )
            addSampleRequest(
                id = "age_over_21",
                displayName = "年满21周岁",
                mdocDataElements = mapOf(
                    AV_NAMESPACE to mapOf(
                        "age_over_21" to false,
                    )
                )
            )
            addSampleRequest(
                id = "age_over_21_zkp",
                displayName = "年满21周岁 (ZKP)",
                mdocDataElements = mapOf(
                    AV_NAMESPACE to mapOf(
                        "age_over_21" to false,
                    )
                ),
                mdocUseZkp = true
            )
            addSampleRequest(
                id = "full",
                displayName = "全部数据项",
                mdocDataElements = mapOf(
                    AV_NAMESPACE to mapOf()
                )
            )
            build()
        }
}

