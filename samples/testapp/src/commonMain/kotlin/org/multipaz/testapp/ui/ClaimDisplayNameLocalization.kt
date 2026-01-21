package org.multipaz.testapp.ui

import org.multipaz.claim.Claim

/**
 * 属性显示名称的中文翻译映射表
 *
 * 核心库中的 displayName 保持英文（用于测试和国际化）， 在 UI 层通过此映射表进行本地化显示。
 */
private val displayNameTranslations =
        mapOf(
                // ========== 文档类型名称 ==========
                "Driving License" to "驾驶证",
                "Driving Licence" to "驾驶证",
                "Mobile Driving License" to "电子驾照",
                "mDL" to "电子驾照",
                "EU Personal ID" to "欧盟个人身份证",
                "Personal ID" to "个人身份证",
                "Photo ID" to "证件照",
                "Age Verification" to "年龄验证",
                "Age Verification Credential" to "年龄验证凭据",
                "Certificate of Residence" to "居住证明",
                "EU Certificate of Residency" to "欧盟居住证明",
                "Naturalization Certificate" to "入籍证明",
                "Utopia Naturalization Certificate" to "乌托邦入籍证明",
                "Movie Ticket" to "电影票",
                "Loyalty" to "会员卡",
                "Loyalty Card" to "会员卡",
                "German Personal ID" to "德国身份证",
                "Google Wallet ID pass" to "Google 钱包身份证",

                // ========== 请求类型名称 ==========
                "US Transportation" to "交通信息",
                "Mandatory Data Elements" to "必填数据项",
                "All Data Elements" to "所有数据项",
                "Age Over 18 (ZKP)" to "年满18周岁（ZKP）",
                "Age Over 21 (ZKP)" to "年满21周岁（ZKP）",
                "Age Over 18 + Portrait" to "年满18周岁 + 人像",
                "Age Over 21 + Portrait" to "年满21周岁 + 人像",
                "Name and Address (Partially Stored)" to "姓名和地址（部分存储）",
                "Name and Address (All Stored)" to "姓名和地址（全部存储）",
                "Date and time of the show" to "放映日期和时间",
                "Prepaid Parking" to "预付停车费",
                "Ticket Number" to "票号",

                // ========== EUPersonalID / PID 相关 ==========
                "Family Name" to "姓",
                "Given Names" to "名",
                "Given Name" to "名",
                "Date of Birth" to "出生日期",
                "Age in Years" to "年龄",
                "Year of Birth" to "出生年份",
                "Older Than Age Attestations" to "年龄达标证明",
                "Older Than 18" to "年满18周岁",
                "Older Than 21" to "年满21周岁",
                "Family Name at Birth" to "出生时的姓",
                "First Name at Birth" to "出生时的名",
                "Place of Birth" to "出生地",
                "Country of Birth" to "出生国家",
                "State of Birth" to "出生省份/州",
                "City of Birth" to "出生城市",
                "Birth City" to "出生城市",
                "Birth State" to "出生省份",
                "Birth Country" to "出生国家",
                "Address" to "地址",
                "Resident Address" to "居住地址",
                "Resident Street" to "居住街道",
                "Resident City" to "居住城市",
                "Resident Postal Code" to "居住邮编",
                "Resident State" to "居住省份",
                "Resident Country" to "居住国家",
                "Resident House Number" to "居住门牌号",
                "Gender" to "性别",
                "Sex" to "性别",
                "Nationality" to "国籍",
                "Nationalities" to "国籍",
                "Issuance Date" to "签发日期",
                "Date of Issue" to "签发日期",
                "Expiry Date" to "有效期至",
                "Date of Expiry" to "有效期至",
                "Issuing Authority" to "签发机构",
                "Issuing Country" to "签发国家",
                "Issuing Jurisdiction" to "签发辖区",
                "Document Number" to "证件号码",
                "Administrative Number" to "行政号码",
                "Personal Administrative Number" to "个人行政号码",
                "Personal Identifier" to "个人标识符",
                "Portrait" to "人像照片",
                "Photo of Holder" to "持有人照片",
                "Signature / Usual Mark" to "签名/惯用标记",
                "Signature" to "签名",
                "Email Address of Holder" to "持有人电子邮箱",
                "Mobile Phone of Holder" to "持有人手机号",

                // ========== Driving License / MDL 相关 ==========
                "License Number" to "驾照号码",
                "Driving Privileges" to "准驾车型",
                "Domestic Driving Privileges" to "国内准驾车型",
                "Vehicle Category Code" to "车辆类别代码",
                "Issue Date" to "签发日期",
                "Codes" to "代码",
                "UN Distinguishing Sign" to "UN区分标志",
                "Height" to "身高",
                "Weight" to "体重",
                "Weight Range" to "体重范围",
                "Eye Colour" to "眼睛颜色",
                "Eye Color" to "眼睛颜色",
                "Hair Colour" to "头发颜色",
                "Hair Color" to "头发颜色",
                "Resident Domestic Address" to "居住地址",
                "Resident County" to "居住县",
                "Portrait Capture Date" to "人像拍摄日期",
                "Portrait Image Timestamp" to "人像拍摄时间戳",
                "Age Over 18" to "年满18周岁",
                "Age Over 21" to "年满21周岁",
                "Age Over 25" to "年满25周岁",
                "Age Over 65" to "年满65周岁",
                "Older Than 13 Years" to "年满13周岁",
                "Name Suffix" to "姓名后缀",
                "Organ Donor" to "器官捐献者",
                "Veteran" to "退伍军人",
                "Family Name Truncation" to "姓氏截断",
                "Given Name Truncation" to "名字截断",
                "Alias / AKA Family Name" to "别名/曾用姓",
                "Alias / AKA Given Name" to "别名/曾用名",
                "Alias / AKA Suffix Name" to "别名/曾用名后缀",
                "Race / Ethnicity" to "种族/民族",
                "Compliance Type" to "合规类型",
                "Limited Duration Document Indicator" to "有限期限文件指示器",
                "EDL Indicator" to "增强驾照指示器",
                "HAZMAT Endorsement Expiration Date" to "危险品认可过期日期",
                "Biometric Template Face" to "面部生物特征模板",
                "Biometric Template Finger" to "指纹生物特征模板",
                "Biometric Template Fingerprint" to "指纹生物特征模板",
                "Biometric Template Signature or Usual Mark" to "签名生物特征模板",
                "Biometric Template Signature/Sign" to "签名生物特征模板",
                "Biometric Template Iris" to "虹膜生物特征模板",
                "Audit Information" to "审计信息",
                "AAMVA Version Number" to "AAMVA版本号",

                // ========== Age Verification ==========
                "Older Than 14 Years" to "年满14周岁",
                "Older Than 16 Years" to "年满16周岁",
                "Older Than 18 Years" to "年满18周岁",
                "Older Than 21 Years" to "年满21周岁",
                "Older Than 25 Years" to "年满25周岁",
                "Older Than 60 Years" to "年满60周岁",
                "Older Than 62 Years" to "年满62周岁",
                "Older Than 65 Years" to "年满65周岁",
                "Older Than 68 Years" to "年满68周岁",

                // ========== PhotoID 相关 ==========
                "First Name" to "名",
                "Last Name" to "姓",
                "Birth Date" to "出生日期",
                "Photo" to "照片",

                // ========== 通用 ==========
                "True" to "是",
                "False" to "否",
                "Yes" to "是",
                "No" to "否",
        )

/**
 * 请求判别类型标签映射表
 *
 * 用于在 UI 中显示每个请求涉及的数据判别类型
 *
 * 判别类型说明：
 * - 大小：数值/日期比较（如 age_in_years > 18、expiry_date > 当前日期）
 * - 等式：字符串/枚举等式（如 document_number = "XXX123"、issuing_country = "DE"）
 * - 布尔：布尔值判别（如 age_over_18 = true/false）
 * - 有效性：凭证有效性（如 expiry_date、issuing_authority、签名验证）
 */
private val requestVerificationTypeLabels = mapOf(
    // ========== Driving License 请求 ==========
    "US Transportation" to "大小/等式/布尔/有效性",
    "Age Over 18" to "布尔",
    "Age Over 21" to "布尔",
    "Age Over 18 (ZKP)" to "布尔",
    "Age Over 21 (ZKP)" to "布尔",
    "Age Over 18 + Portrait" to "布尔",
    "Age Over 21 + Portrait" to "布尔",
    "Mandatory Data Elements" to "大小/等式/布尔/有效性",
    "All Data Elements" to "大小/等式/布尔/有效性",
    "Name and Address (Partially Stored)" to "等式",
    "Name and Address (All Stored)" to "等式",

    // ========== Age Verification 请求 ==========
    "Age Over 18" to "布尔",
    "Age Over 21" to "布尔",
    "Age Over 18 (ZKP)" to "布尔",
    "Age Over 21 (ZKP)" to "布尔",
    "All Data Elements" to "布尔",

    // ========== EUPersonalID / GermanPersonalID 请求 ==========
    "Age Over 18" to "布尔",
    "Age Over 18 (ZKP)" to "布尔",
    "Age Over 18 + Portrait" to "布尔",
    "Mandatory Data Elements" to "等式/有效性",
    "All Data Elements" to "大小/等式/布尔/有效性",

    // ========== IDPass 请求 ==========
    "Age Over 18" to "布尔",
    "Age Over 21" to "布尔",
    "Age Over 18 (ZKP)" to "布尔",
    "Age Over 21 (ZKP)" to "布尔",
    "Age Over 18 + Portrait" to "布尔",
    "Age Over 21 + Portrait" to "布尔",
    "Mandatory Data Elements" to "等式/有效性",
    "All Data Elements" to "大小/等式/布尔/有效性",

    // ========== PhotoID 请求 ==========
    "Age Over 18" to "布尔",
    "Age Over 18 (ZKP)" to "布尔",
    "Age Over 18 + Portrait" to "布尔",
    "Mandatory Data Elements" to "等式",
    "All Data Elements" to "大小/等式/布尔",

    // ========== EUCertificateOfResidence 请求 ==========
    "Age Over 18" to "布尔",
    "Mandatory Data Elements" to "等式/有效性",
    "All Data Elements" to "大小/等式/布尔/有效性",

    // ========== Loyalty 请求 ==========
    "Mandatory Data Elements" to "等式",
    "All Data Elements" to "等式",

    // ========== UtopiaMovieTicket 请求 ==========
    "Prepaid Parking" to "布尔",
    "Ticket Number" to "等式",
    "All Data Elements" to "等式/布尔",

    // ========== UtopiaNaturalization 请求 ==========
    "All Data Elements" to "等式",
)

/** 获取请求的判别类型标签 */
fun getRequestVerificationTypeLabel(requestDisplayName: String): String =
        requestVerificationTypeLabels[requestDisplayName] ?: ""

/** 获取 Claim 的本地化显示名称 如果有中文翻译则返回中文，否则返回原始英文名称 */
val Claim.localizedDisplayName: String
    get() = displayNameTranslations[displayName] ?: displayName

/** 根据英文 displayName 获取中文翻译 如果没有翻译则返回原始名称 */
fun localizeDisplayName(displayName: String): String =
        displayNameTranslations[displayName] ?: displayName
