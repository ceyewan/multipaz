package org.multipaz.testapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import org.multipaz.cbor.Cbor
import org.multipaz.cose.Cose
import org.multipaz.cose.CoseNumberLabel
import org.multipaz.credential.SecureAreaBoundCredential
import org.multipaz.mdoc.credential.MdocCredential
import org.multipaz.sdjwt.credential.SdJwtVcCredential
import org.multipaz.testapp.DocumentModel
import org.multipaz.util.toBase64Url
import kotlinx.coroutines.launch
import org.multipaz.compose.datetime.formattedDateTime

@Composable
fun CredentialViewerScreen(
    documentModel: DocumentModel,
    documentId: String,
    credentialId: String,
    showToast: (message: String) -> Unit,
    onViewCertificateChain: (encodedCertificateData: String) -> Unit,
    onViewCredentialClaims: (documentId: String, credentialId: String) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val documentInfo = documentModel.documentInfos[documentId]
    val credentialInfo = documentInfo?.credentialInfos?.find { it.credential.identifier == credentialId  }

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        if (credentialInfo == null) {
            Text("未找到对应凭证：身份文件ID：$documentId，凭据ID：$credentialId")
        } else {
            KeyValuePairText("类型", credentialInfo.credential::class.simpleName.toString())
            KeyValuePairText("标识符", credentialInfo.credential.identifier)
            KeyValuePairText("域", credentialInfo.credential.domain)
            KeyValuePairText("生效时间", formattedDateTime(credentialInfo.credential.validFrom))
            KeyValuePairText("失效时间", formattedDateTime(credentialInfo.credential.validUntil))
            KeyValuePairText("已认证", if (credentialInfo.credential.isCertified) "是" else "否")
            KeyValuePairText("发行方数据", "${credentialInfo.credential.issuerProvidedData.size} 字节")
            KeyValuePairText("使用次数", credentialInfo.credential.usageCount.toString())
            when (credentialInfo.credential) {
                is MdocCredential -> {
                    val issuerSigned = Cbor.decode(credentialInfo.credential.issuerProvidedData)
                    val issuerAuth = issuerSigned.get("issuerAuth").asCoseSign1
                    val msoBytes = issuerAuth.payload!!
                    KeyValuePairText("MSO 大小", "${msoBytes.size} 字节")
                    KeyValuePairText("ISO mdoc 文档类型", credentialInfo.credential.docType)
                    KeyValuePairText(
                        keyText = "ISO mdoc 签名证书",
                        valueText = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.secondary)) {
                                append("点击查看详情")
                            }
                        },
                        modifier = Modifier.clickable {
                            coroutineScope.launch {
                                val issuerSigned = Cbor.decode(credentialInfo.credential.issuerProvidedData)
                                val issuerAuthCoseSign1 = issuerSigned["issuerAuth"].asCoseSign1
                                val certChain =
                                    issuerAuthCoseSign1.unprotectedHeaders[
                                        CoseNumberLabel(Cose.COSE_LABEL_X5CHAIN)
                                    ]!!.asX509CertChain
                                onViewCertificateChain(Cbor.encode(certChain.toDataItem()).toBase64Url())
                            }
                        }
                    )
                }
                is SdJwtVcCredential -> {
                    KeyValuePairText("可验证凭据类型", credentialInfo.credential.vct)
                    // TODO: Show cert chain for key used to sign issuer-signed data. Involves
                    //  getting this over the network as specified in section 5 "JWT VC Issuer Metadata"
                    //  of https://datatracker.ietf.org/doc/draft-ietf-oauth-sd-jwt-vc/ ... how annoying
                }
            }

            if (credentialInfo.credential is SecureAreaBoundCredential) {
                KeyValuePairText("安全区域", credentialInfo.credential.secureArea.displayName)
                KeyValuePairText("安全区域标识", credentialInfo.credential.secureArea.identifier)
                KeyValuePairText("设备密钥算法", credentialInfo.keyInfo!!.algorithm.description)
                KeyValuePairText("设备密钥已失效",
                    buildAnnotatedString {
                        if (credentialInfo.keyInvalidated) {
                            withStyle(style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.error
                            )) {
                                append("是")
                            }
                        } else {
                            append("否")
                        }
                    })
                KeyValuePairText(
                    keyText = "设备密钥证明",
                    valueText = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.secondary)) {
                            append("点击查看详情")
                        }
                    },
                    modifier = Modifier.clickable {
                        coroutineScope.launch {
                            val attestation = credentialInfo.credential.getAttestation()
                            if (attestation.certChain != null) {
                                onViewCertificateChain(Cbor.encode(attestation.certChain!!.toDataItem()).toBase64Url())
                            } else {
                                showToast("未找到设备密钥证明")
                            }
                        }
                    }
                )
            } else {
                KeyValuePairText("安全区域", "不适用")
            }

            KeyValuePairText(
                keyText = "声明",
                valueText = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.secondary)) {
                        append("点击查看详情")
                    }
                },
                modifier = Modifier.clickable {
                    coroutineScope.launch {
                        onViewCredentialClaims(documentId, credentialId)
                    }
                }
            )

        }
    }
}
