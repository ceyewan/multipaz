package org.multipaz.testapp.ui.edl

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// 主色调 - 蓝色系
val DriverLicenseBlue = Color(0xFF1976D2)
val DriverLicenseLightBlue = Color(0xFF2196F3)
val DriverLicenseVeryLightBlue = Color(0xFFE3F2FD)
val DriverLicenseBackgroundBlue = Color(0xFFBBDEFB)

// 状态颜色
val DriverLicenseGreen = Color(0xFF4CAF50)  // 正常状态
val DriverLicenseRed = Color(0xFFFF5722)    // 警告/记分
val DriverLicenseGray = Color(0xFF757575)   // 灰色按钮

// 文本颜色
val TextPrimary = Color(0xFF333333)
val TextSecondary = Color(0xFF666666)
val TextTertiary = Color(0xFF999999)
val TextWhite = Color(0xFFFFFFFF)

// 背景和边框
val CardBackground = Color(0xFFFFFFFF)
val DividerGray = Color(0xFFE0E0E0)

private val EdlLightColorScheme = lightColorScheme(
    primary = DriverLicenseBlue,
    onPrimary = TextWhite,
    primaryContainer = DriverLicenseVeryLightBlue,
    onPrimaryContainer = TextPrimary,
    secondary = DriverLicenseLightBlue,
    onSecondary = TextWhite,
    background = DriverLicenseVeryLightBlue,
    onBackground = TextPrimary,
    surface = CardBackground,
    onSurface = TextPrimary,
    error = DriverLicenseRed,
    onError = TextWhite,
)

@Composable
fun EdlTheme(
    content: @Composable () -> Unit
) {
    // 强制使用浅色主题，保持 EDL 风格一致性
    MaterialTheme(
        colorScheme = EdlLightColorScheme,
        content = content
    )
}
