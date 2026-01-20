package org.multipaz.testapp.ui.edl

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * 验证端读取驾照页面 (EDL 风格)
 * 提供扫描二维码和NFC读取的入口
 * 最终跳转到 Multipaz 的实际读取页面
 */
@Composable
fun EdlVerificationScreen(
    onNavigateToMultipazReading: () -> Unit,
    onBack: () -> Unit
) {
    EdlTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DriverLicenseVeryLightBlue)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "选择验证方式",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                modifier = Modifier.padding(bottom = 40.dp)
            )

            // 扫描二维码
            Card(
                onClick = onNavigateToMultipazReading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = DriverLicenseBlue)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.QrCodeScanner,
                        contentDescription = "QR Scanner",
                        modifier = Modifier.size(56.dp),
                        tint = TextWhite
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "扫描二维码",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // NFC感应
            Card(
                onClick = onNavigateToMultipazReading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = DriverLicenseLightBlue)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "📱", fontSize = 40.sp)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "NFC感应读取",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(40.dp))

            OutlinedButton(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("返回")
            }
        }
    }
}
