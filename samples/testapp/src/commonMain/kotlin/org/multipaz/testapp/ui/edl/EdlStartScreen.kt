package org.multipaz.testapp.ui.edl

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * EDL 风格首页
 * 包含三个核心功能按钮
 */
@Composable
fun EdlStartScreen(
    onNavigateToDocumentManagement: () -> Unit,
    onNavigateToSharing: () -> Unit,
    onNavigateToVerification: () -> Unit
) {
    EdlTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 应用标题
                Text(
                    text = "电子驾照",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = DriverLicenseBlue,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Electronic Driver's License",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    modifier = Modifier.padding(bottom = 48.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                // 证件管理按钮
                MainActionButton(
                    icon = Icons.Default.AccountBox,
                    title = "证件管理",
                    subtitle = "Document Management",
                    onClick = onNavigateToDocumentManagement
                )

                Spacer(modifier = Modifier.height(20.dp))

                // 分享电子驾照按钮
                MainActionButton(
                    icon = Icons.Default.Share,
                    title = "分享电子驾照",
                    subtitle = "Share e-Driving License",
                    onClick = onNavigateToSharing
                )

                Spacer(modifier = Modifier.height(20.dp))

                // 验证电子驾照按钮
                MainActionButton(
                    icon = Icons.Default.Check,
                    title = "验证电子驾照",
                    subtitle = "Verify e-Driving License",
                    onClick = onNavigateToVerification
                )
                
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}
