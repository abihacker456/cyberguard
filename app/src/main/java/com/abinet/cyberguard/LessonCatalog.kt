package com.abinet.cyberguard.ui

data class Lesson(
    val id: String,
    val title: String,
    val subtitle: String
)

object LessonCatalog {
    val ALL = listOf(
        Lesson("malware_101", "Malware 101", "What malware is and how it spreads"),
        Lesson("banking_trojan", "Banking Trojans", "Overlays, fake logins, and theft"),
        Lesson("ransomware", "Ransomware", "Encryption attacks and defense"),
        Lesson("phishing", "Phishing", "Fake emails, SMS, and notifications"),
        Lesson("apk_anatomy", "APK Anatomy", "What's inside an Android app"),
        Lesson("permissions", "Dangerous Permissions", "What apps can request and why"),
        Lesson("yara_rules", "Pattern Rules", "How signature detection works")
    )
}