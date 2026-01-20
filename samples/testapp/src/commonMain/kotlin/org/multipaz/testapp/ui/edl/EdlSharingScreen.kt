package org.multipaz.testapp.ui.edl

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * 用户端分享驾照页面 (EDL 风格)
 * 包括属性选择、分享方式选择
 * 最终跳转到 Multipaz 的实际分享页面
 */
@Composable
fun EdlSharingScreen(
    onNavigateToMultipazSharing: () -> Unit,
    onBack: () -> Unit
) {
    var currentStep by remember { mutableStateOf(SharingStep.ATTRIBUTE_SELECTION) }
    var selectedAttributes by remember { mutableStateOf(setOf("age_over_18", "points_ok", "valid_period", "vehicle_type")) }

    EdlTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DriverLicenseVeryLightBlue)
        ) {
            when (currentStep) {
                SharingStep.ATTRIBUTE_SELECTION -> {
                    AttributeSelectionContent(
                        selectedAttributes = selectedAttributes,
                        onAttributeToggle = { attribute ->
                            selectedAttributes = if (selectedAttributes.contains(attribute)) {
                                selectedAttributes - attribute
                            } else {
                                selectedAttributes + attribute
                            }
                        },
                        onNext = {
                            if (selectedAttributes.isNotEmpty()) {
                                currentStep = SharingStep.METHOD_SELECTION
                            }
                        },
                        onBack = onBack
                    )
                }
                SharingStep.METHOD_SELECTION -> {
                    MethodSelectionContent(
                        onMethodSelected = { 
                            // 无论选择哪种方式，都跳转到 Multipaz 的实际分享功能
                            onNavigateToMultipazSharing() 
                        },
                        onBack = { currentStep = SharingStep.ATTRIBUTE_SELECTION }
                    )
                }
            }
        }
    }
}

@Composable
private fun AttributeSelectionContent(
    selectedAttributes: Set<String>,
    onAttributeToggle: (String) -> Unit,
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    val scrollState = rememberScrollState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = "选择要分享的属性",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            colors = CardDefaults.cardColors(containerColor = CardBackground)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                AttributeCheckbox(
                    label = "年龄大于18岁",
                    description = "仅分享是否满18岁，不包含具体出生日期",
                    checked = selectedAttributes.contains("age_over_18"),
                    onCheckedChange = { onAttributeToggle("age_over_18") }
                )
                
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = DividerGray)
                
                AttributeCheckbox(
                    label = "累计记分未满12分",
                    description = "分享当前记分是否在正常范围",
                    checked = selectedAttributes.contains("points_ok"),
                    onCheckedChange = { onAttributeToggle("points_ok") }
                )
                
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = DividerGray)
                
                AttributeCheckbox(
                    label = "驾照在有效期内",
                    description = "验证驾照是否仍然有效",
                    checked = selectedAttributes.contains("valid_period"),
                    onCheckedChange = { onAttributeToggle("valid_period") }
                )
                
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = DividerGray)
                
                AttributeCheckbox(
                    label = "准驾车型",
                    description = "分享允许驾驶的车辆类型",
                    checked = selectedAttributes.contains("vehicle_type"),
                    onCheckedChange = { onAttributeToggle("vehicle_type") }
                )
                
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = DividerGray)
                
                AttributeCheckbox(
                    label = "详细个人信息",
                    description = "包括姓名、性别、照片等完整信息",
                    checked = selectedAttributes.contains("full_info"),
                    onCheckedChange = { onAttributeToggle("full_info") }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onNext,
            enabled = selectedAttributes.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = DriverLicenseBlue
            )
        ) {
            Text(
                text = "下一步 (${selectedAttributes.size}个属性已选)",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("返回")
        }
    }
}

@Composable
private fun AttributeCheckbox(
    label: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = DriverLicenseBlue
            )
        )
        
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            Text(
                text = label,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = TextPrimary
            )
            Text(
                text = description,
                fontSize = 13.sp,
                color = TextSecondary,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
private fun MethodSelectionContent(
    onMethodSelected: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "选择分享方式",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            modifier = Modifier.padding(bottom = 40.dp)
        )

        // 二维码方式
        Card(
            onClick = onMethodSelected,
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
                // 使用 Share 作为备选，如果 QrCode 不存在
                Icon(
                    imageVector = Icons.Default.QrCode, // 如果编译失败可能需要改为 Share
                    contentDescription = "QR Code",
                    modifier = Modifier.size(56.dp),
                    tint = TextWhite
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "二维码分享",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // NFC方式
        Card(
            onClick = onMethodSelected,
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
                Text(
                    text = "📱",
                    fontSize = 40.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "NFC感应分享",
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
            Text("返回上一步")
        }
    }
}

private enum class SharingStep {
    ATTRIBUTE_SELECTION,
    METHOD_SELECTION
}
