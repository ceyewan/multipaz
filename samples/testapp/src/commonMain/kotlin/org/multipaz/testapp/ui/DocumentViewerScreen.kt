package org.multipaz.testapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import org.multipaz.testapp.DocumentModel
import org.multipaz.testapp.rememberInhibitNfcObserveMode

@Composable
fun DocumentViewerScreen(
    documentModel: DocumentModel,
    documentId: String,
    showToast: (message: String) -> Unit,
    onViewCredential: (documentId: String, credentialId: String) -> Unit,
) {
    val documentInfo = documentModel.documentInfos[documentId]

    rememberInhibitNfcObserveMode()

    Column(Modifier.padding(8.dp)) {
        if (documentInfo == null) {
            Text("未找到标识符为${documentId}的身份文档")
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.height(200.dp),
                    contentScale = ContentScale.FillHeight,
                    bitmap = documentInfo.cardArt,
                    contentDescription = null,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = documentInfo.document.metadata.typeDisplayName ?: "(未设置类型显示名称)",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                KeyValuePairText(
                    keyText = "已配置",
                    valueText = if (documentInfo.document.metadata.provisioned) "是" else "否"
                )
                KeyValuePairText(
                    keyText = "身份文件类型",
                    valueText = documentInfo.document.metadata.typeDisplayName ?: "(未设置类型显示名称)"
                )
                KeyValuePairText(
                    keyText = "身份文件名称",
                    valueText = documentInfo.document.metadata.displayName ?: "(未设置显示名称)"
                )
                Text(
                    text = "凭据",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )
                val domains = mutableSetOf<String>()
                for (credentialInfo in documentInfo.credentialInfos) {
                    domains.add(credentialInfo.credential.domain)
                }
                for (domain in domains.sorted()) {
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = "$domain 域",
                        style = MaterialTheme.typography.bodyMedium,
                        fontStyle = FontStyle.Italic
                    )
                    for (credentialInfo in documentInfo.credentialInfos) {
                        if (credentialInfo.credential.domain != domain) {
                            continue
                        }
                        KeyValuePairText(
                            modifier = Modifier
                                .padding(start = 24.dp)
                                .clickable {
                                    onViewCredential(
                                        documentInfo.document.identifier,
                                        credentialInfo.credential.identifier
                                    )
                                },
                            keyText = credentialInfo.credential.credentialType,
                            valueText = buildAnnotatedString {
                                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.secondary)) {
                                    append("使用次数：${credentialInfo.credential.usageCount}，点击查看详情")
                                }
                            }
                        )
                    }
                }
            }

        }
    }
}
