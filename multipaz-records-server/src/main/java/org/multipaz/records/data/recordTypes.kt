package org.multipaz.records.data

import org.multipaz.documenttype.DocumentAttributeType
import org.multipaz.documenttype.Icon
import org.multipaz.documenttype.StringOption
import org.multipaz.documenttype.knowntypes.Options

/**
 * Describes schema of the data stored in [Identity]. Record type with the identifier "core"
 * describes core data, other record types describe possible records.
 */
val recordTypes = RecordType.buildMap {
    addComplex("core") {
        displayName = "Core personal information"
        addString(
            identifier = "family_name",
            displayName = "姓",
            description = "Last name, surname, or primary identifier, of the person.",
            icon = Icon.PERSON,
        )
        addString(
            identifier = "given_name",
            displayName = "名",
            description = "First name(s), other name(s), or secondary identifier, of the person",
            icon = Icon.PERSON,
        )
        addDate(
            identifier = "birth_date",
            displayName = "出生日期",
            description = "Day, month and year on which the person was born. If unknown, approximate date of birth",
            icon = Icon.TODAY,
        )
        addPicture(
            identifier = "portrait",
            displayName = "人像",
            description = "A reproduction of the person’s portrait.",
            icon = Icon.FACE,
        )
        addDate(
            identifier = "portrait_capture_date",
            displayName = "人像拍摄时间",
            description = "Date when portrait was taken",
            icon = Icon.TODAY,
        )
        addPrimitive(
            type = DocumentAttributeType.IntegerOptions(Options.SEX_ISO_IEC_5218),
            identifier = "sex",
            displayName ="性别",
            description = "Person’s sex",
            icon = Icon.EMERGENCY,
        )
        addString(
            identifier = "birth_place",
            displayName = "出生地",
            description = "Country and municipality or state/province where the person was born",
            icon = Icon.PLACE,
        )
        addComplex("address") {
            displayName = "住址"
            description = "Address"
            icon = Icon.PLACE
            addString(
                identifier = "formatted",
                displayName = "完整格式",
                description = "Full address as text",
                icon = Icon.PLACE,
            )
            addPrimitive(
                type = DocumentAttributeType.StringOptions(Options.COUNTRY_ISO_3166_1_ALPHA_2),
                identifier = "country",
                displayName = "国家/地区",
                description = "Country",
                icon = Icon.FLAG,
            )
            addString(
                identifier = "region",
                displayName = "省/州/地区",
                description = "Administrative unit in the country",
                icon = Icon.PLACE,
            )
            addString(
                identifier = "locality",
                displayName = "市",
                description = "City/Town/Village",
                icon = Icon.LOCATION_CITY,
            )
            addString(
                identifier = "postal_code",
                displayName = "邮政编码",
                description = "National postal code",
                icon = Icon.NUMBERS,
            )
            addString(
                identifier = "street",
                displayName = "街道",
                description = "Name of the street",
                icon = Icon.DIRECTIONS,
            )
            addString(
                identifier = "house_number",
                displayName = "门牌号",
                description = "House Number",
                icon = Icon.HOUSE,
            )
            addString(
                identifier = "unit",
                displayName = "单元号",
                description = "Apartment/Unit Number",
                icon = Icon.APARTMENT,
            )
        }
        addString(
            identifier = "family_name_national_character",
            displayName = "姓 (本地)",
            description = "The family name spelled in national alphabet",
            icon = Icon.LANGUAGE_JAPANESE_KANA,
        )
        addString(
            identifier = "given_name_national_character",
            displayName = "名 (本地)",
            description = "The given name spelled in national alphabet",
            icon = Icon.LANGUAGE_JAPANESE_KANA,
        )
        addPrimitiveList(
            type = DocumentAttributeType.StringOptions(Options.COUNTRY_ISO_3166_1_ALPHA_2),
            identifier = "nationality",
            displayName = "国籍",
            description = "List of nationalities",
            icon = Icon.FLAG,
        )
        addPicture(
            identifier = "signature_usual_mark",
            displayName = "签名",
            description = "Image of the signature or usual mark of the mDL holder,",
            icon = Icon.SIGNATURE,
        )
        addString(
            identifier = "utopia_id_number",
            displayName = "身份编号",
            description = "Unique and immutable number assigned to everyone by Utopia Registry",
            icon = Icon.ACCOUNT_BOX,
        )
    }
    addComplex("mDL") {
        displayName = "驾驶证"
        addDate(
            identifier = "issue_date",
            displayName = "签发日期",
            description = "Date when mDL was issued",
            icon = Icon.TODAY,
        )
        addDate(
            identifier = "expiry_date",
            displayName = "失效日期",
            description = "Date when mDL expires",
            icon = Icon.TODAY,
        )
        addPrimitive(
            type = DocumentAttributeType.StringOptions(Options.COUNTRY_ISO_3166_1_ALPHA_2),
            identifier = "issuing_country",
            displayName = "签发国家",
            description = "Alpha-2 country code, as defined in ISO 3166-1, of the issuing authority’s country or territory",
            icon = Icon.ACCOUNT_BALANCE,
        )
        addString(
            identifier = "issuing_authority",
            displayName = "签发机构",
            description = "Issuing authority name.",
            icon = Icon.ACCOUNT_BALANCE,
        )
        addString(
            identifier = "document_number",
            displayName = "驾驶证编号",
            description = "The number assigned or calculated by the issuing authority.",
            icon = Icon.NUMBERS,
        )
        addComplexList(
            identifier ="driving_privileges",
            displayName = "驾驶权限",
            description = "Driving privileges of the mDL holder",
            icon = Icon.DIRECTIONS_CAR
        ) {
            addPrimitive(
                type = DocumentAttributeType.StringOptions(Options.VEHICLE_CATEGORY_CODE_ISO_IEC_18013_1_ANNEX_B),
                "vehicle_category_code",
                displayName = "车辆类型",
                description = "Vehicle type that mDL holder is licensed to drive"
            )
            addDate(
                identifier = "issue_date",
                displayName = "Date of Issue",
                description = "Date when mDL holder was licensed for this type of vehicle",
            )
            addDate(
                identifier = "expiry_date",
                displayName = "Date of Expiry",
                description = "Date until mDL holder is licensed for this type of vehicle",
            )
        }
        addPrimitive(
            type = DocumentAttributeType.StringOptions(Options.DISTINGUISHING_SIGN_ISO_IEC_18013_1_ANNEX_F),
            identifier = "un_distinguishing_sign",
            displayName ="签发国别标识",
            description = "Distinguishing sign of the issuing country",
            icon = Icon.LANGUAGE,
        )
        addString(
            identifier = "administrative_number",
            displayName = "行政编号",
            description = "An audit control number assigned by the issuing authority",
            icon = Icon.NUMBERS
        )
        addNumber(
            identifier = "height",
            displayName = "身高",
            description = "mDL holder’s height in centimetres",
            icon = Icon.EMERGENCY,
        )
        addNumber(
            identifier = "weight",
            displayName ="体重",
            description = "mDL holder’s weight in kilograms",
            icon = Icon.EMERGENCY
        )
        addPrimitive(
            type = DocumentAttributeType.StringOptions(
        listOf(
                    StringOption(null, "(未设置)"),
                    StringOption("black", "黑色"),
                    StringOption("blue", "蓝色"),
                    StringOption("brown", "棕色"),
                    StringOption("dichromatic", "双色"),
                    StringOption("grey", "灰色"),
                    StringOption("green", "绿色"),
                    StringOption("hazel", "淡褐色"),
                    StringOption("maroon", "褐红色"),
                    StringOption("pink", "粉色"),
                    StringOption("unknown", "未知")
                )
            ),
            identifier = "eye_colour",
            displayName = "眼睛颜色",
            description = "mDL holder’s eye color",
            icon = Icon.PERSON,
        )
        addPrimitive(
            type = DocumentAttributeType.StringOptions(
                listOf(
                    StringOption(null, "(未设置)"),
                    StringOption("bald", "光头"),
                    StringOption("black", "黑色"),
                    StringOption("blond", "金色"),
                    StringOption("brown", "棕色"),
                    StringOption("grey", "灰色"),
                    StringOption("red", "红色"),
                    StringOption("auburn", "赤褐色"),
                    StringOption("sandy", "沙色"),
                    StringOption("white", "白色"),
                    StringOption("unknown", "未知"),
                )
            ),
            identifier = "hair_colour",
            displayName = "头发颜色",
            description = "mDL holder’s hair color",
            icon = Icon.PERSON,
        )
    }
    addComplex("naturalization") {
        displayName = "国籍"
        addDate(
            identifier = "naturalization_date",
            displayName = "入籍日期",
            description = "Date when the person was granted Utopia citizenship",
            icon = Icon.TODAY,
        )
    }
    addComplex("movie") {
        displayName = "电影票"
        addString(
            identifier = "movie_title",
            displayName = "影片名称",
            description = "Title of the movie for which the ticket is valid",
            icon = Icon.PANORAMA_WIDE_ANGLE,
        )
        addDateTime(
            identifier = "show_date_time",
            displayName = "放映时间",
            description = "Date and time when the movie starts",
            icon = Icon.TODAY,
        )
        addString(
            identifier = "ticket_id",
            displayName = "Ticket Number",
            description = "Ticket identification/reference number issued at the purchase time.",
            icon = Icon.NUMBERS,
        )
        addString(
            identifier = "cinema",
            displayName = "影院",
            description = "Cinema theater name, and/or address/location of the admission.",
            icon = Icon.PLACE,
        )
        addPrimitive(
            type = DocumentAttributeType.StringOptions(
                listOf(
                    StringOption("NR", "NR - 未分级"),
                    StringOption("G", "G – 大众级"),
                    StringOption("PG", "PG – 建议家长陪同"),
                    StringOption("PG-13", "PG-13 – 家长需特别注意"),
                    StringOption("R", "R – 限制级"),
                    StringOption("NC-17", "NC-17 – 仅限成人"),
                )
            ),
            identifier = "movie_rating",
            displayName = "年龄分级代码",
            description = "Movie rating code for age restrictions.",
            icon = Icon.TODAY,
        )
        addString(
            identifier = "theater_id",
            displayName = "影厅",
            description = "Name or number of the theater in a multi-theater cinema building.",
            Icon.TODAY,
        )
        addString(
            identifier = "seat_id",
            displayName = "座位",
            description = "Seat number or code (e.g. row/seat).",
            Icon.NUMBERS,
        )
        addPrimitive(
            type = DocumentAttributeType.Boolean,
            identifier = "parking_option",
            displayName = "停车权益",
            description = "Flag if car parking is prepaid with the ticket purchase.",
            Icon.DIRECTIONS_CAR,
        )
        addPicture(
            identifier = "poster",
            displayName = "影片海报",
            description = "Poster for the movie",
            icon = Icon.IMAGE,
        )
    }
    addComplex("wholesale") {
        displayName = "会员身份凭据"
        addString(
            identifier = "membership_number",
            displayName = "会员编号",
            description = "Person identifier of the Loyalty ID holder",
            icon = Icon.NUMBERS,
        )
        addString(
            identifier = "tier",
            displayName = "会员等级",
            description = "Membership tier (basic, silver, gold, platinum, elite)",
            icon = Icon.STARS,
        )
        addDate(
            identifier = "issue_date",
            displayName = "签发日期",
            description = "Date when the Loyalty ID was issued",
            icon = Icon.TODAY,
        )
        addDate(
            identifier = "expiry_date",
            displayName = "失效日期",
            description = "Date when the Loyalty ID expires",
            icon = Icon.TODAY,
        )

    }
}