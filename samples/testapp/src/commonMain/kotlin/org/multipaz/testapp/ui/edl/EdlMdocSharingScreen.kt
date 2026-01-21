package org.multipaz.testapp.ui.edl

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.ImageLoader
import org.jetbrains.compose.resources.painterResource
import org.multipaz.testapp.platformAppIcon
import org.multipaz.compose.presentment.MdocProximityQrPresentment
import org.multipaz.compose.presentment.MdocProximityQrSettings
import org.multipaz.compose.qrcode.generateQrCode
import org.multipaz.documenttype.DocumentTypeRepository
import org.multipaz.mdoc.connectionmethod.MdocConnectionMethod
import org.multipaz.mdoc.connectionmethod.MdocConnectionMethodBle
import org.multipaz.mdoc.connectionmethod.MdocConnectionMethodNfc
import org.multipaz.mdoc.transport.MdocTransportOptions
import org.multipaz.presentment.model.PresentmentModel
import org.multipaz.presentment.model.PresentmentSource
import org.multipaz.prompt.PromptModel
import org.multipaz.testapp.TestAppSettingsModel
import org.multipaz.testapp.ui.localizeDisplayName
import org.multipaz.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EdlMdocSharingScreen(
    presentmentSource: PresentmentSource,
    presentmentModel: PresentmentModel,
    settingsModel: TestAppSettingsModel,
    promptModel: PromptModel,
    documentTypeRepository: DocumentTypeRepository,
    imageLoader: ImageLoader,
    onBack: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope { promptModel }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 顶部返回和标题
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            EdlBackButton(onClick = onBack)
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "分享电子驾照",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = DriverLicenseBlue
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        MdocProximityQrPresentment(
            modifier = Modifier,
            appName = "电子驾照",
            appIconPainter = painterResource(platformAppIcon),
            presentmentModel = presentmentModel,
            presentmentSource = presentmentSource,
            promptModel = promptModel,
            documentTypeRepository = documentTypeRepository,
            imageLoader = imageLoader,
            allowMultipleRequests = settingsModel.presentmentAllowMultipleRequests.value,
            showQrButton = { onQrButtonClicked ->
                EdlButton(
                    text = "展示二维码",
                    onClick = {
                        val connectionMethods = mutableListOf<MdocConnectionMethod>()
                        val bleUuid = UUID.randomUUID()
                        if (settingsModel.presentmentBleCentralClientModeEnabled.value) {
                            connectionMethods.add(
                                MdocConnectionMethodBle(
                                    supportsPeripheralServerMode = false,
                                    supportsCentralClientMode = true,
                                    peripheralServerModeUuid = null,
                                    centralClientModeUuid = bleUuid,
                                )
                            )
                        }
                        if (settingsModel.presentmentBlePeripheralServerModeEnabled.value) {
                            connectionMethods.add(
                                MdocConnectionMethodBle(
                                    supportsPeripheralServerMode = true,
                                    supportsCentralClientMode = false,
                                    peripheralServerModeUuid = bleUuid,
                                    centralClientModeUuid = null,
                                )
                            )
                        }
                        if (settingsModel.presentmentNfcDataTransferEnabled.value) {
                            connectionMethods.add(
                                MdocConnectionMethodNfc(
                                    commandDataFieldMaxLength = 0xffff,
                                    responseDataFieldMaxLength = 0x10000
                                )
                            )
                        }
                        onQrButtonClicked(
                            MdocProximityQrSettings(
                                availableConnectionMethods = connectionMethods,
                                createTransportOptions = MdocTransportOptions(
                                    bleUseL2CAP = settingsModel.presentmentBleL2CapEnabled.value,
                                    bleUseL2CAPInEngagement = settingsModel.presentmentBleL2CapInEngagementEnabled.value
                                )
                            )
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            },
            showQrCode = { uri ->
                val qrCodeBitmap = remember { generateQrCode(uri) }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "请将二维码展示给验证方扫描",
                        fontSize = 16.sp,
                        color = DriverLicenseBlue,
                        fontWeight = FontWeight.Medium
                    )
                    
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            bitmap = qrCodeBitmap,
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth
                        )
                    }
                    
                    EdlButton(
                        text = "取消",
                        onClick = { presentmentModel.reset() },
                        modifier = Modifier.fillMaxWidth(),
                        containerColor = DriverLicenseGray
                    )
                }
            },
            claimDisplayNameLocalizer = ::localizeDisplayName
        )
    }
}
