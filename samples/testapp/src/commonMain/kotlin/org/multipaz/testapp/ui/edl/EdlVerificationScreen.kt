package org.multipaz.testapp.ui.edl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

/**
 * 验证端读取驾照页面 (EDL 风格)
 * 直接跳转到 Multipaz 的实际读取页面（二维码方式）
 */
@Composable
fun EdlVerificationScreen(
    onNavigateToMultipazReading: () -> Unit,
    onBack: () -> Unit
) {
    // 直接跳转到验证功能
    LaunchedEffect(Unit) {
        onNavigateToMultipazReading()
    }
}
