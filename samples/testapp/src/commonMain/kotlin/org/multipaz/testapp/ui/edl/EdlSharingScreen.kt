package org.multipaz.testapp.ui.edl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

/**
 * 用户端分享驾照页面 (EDL 风格)
 * 直接跳转到 Multipaz 的实际分享页面（二维码方式）
 */
@Composable
fun EdlSharingScreen(
    onNavigateToMultipazSharing: () -> Unit,
    onBack: () -> Unit
) {
    // 直接跳转到分享功能
    LaunchedEffect(Unit) {
        onNavigateToMultipazSharing()
    }
}
