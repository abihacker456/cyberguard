package com.abinet.cyberguard.ui

data class PermissionInfo(
    val name: String,
    val level: Level,
    val description: String
) {
    enum class Level { NORMAL, DANGEROUS, SPECIAL }
}

object PermissionData {

    val ALL = listOf(
        PermissionInfo(
            "INTERNET",
            PermissionInfo.Level.NORMAL,
            "Standard network access. Granted automatically. Not sensitive by itself, but combined with other permissions it enables data collection."
        ),
        PermissionInfo(
            "ACCESS_FINE_LOCATION",
            PermissionInfo.Level.DANGEROUS,
            "Precise GPS location. Reveals where you live, work, and travel. Deny unless the app genuinely needs maps, rideshare, or weather."
        ),
        PermissionInfo(
            "READ_CONTACTS",
            PermissionInfo.Level.DANGEROUS,
            "Reads your address book. Often abused by messaging and social apps to upload your social graph. Rarely essential."
        ),
        PermissionInfo(
            "CAMERA",
            PermissionInfo.Level.DANGEROUS,
            "Access to camera hardware. Necessary for camera apps, QR scanners, video calls. Suspicious in apps that have no visual function."
        ),
        PermissionInfo(
            "RECORD_AUDIO",
            PermissionInfo.Level.DANGEROUS,
            "Microphone access. Required for voice calls, voice memos, voice assistants. Any other app requesting it should raise questions."
        ),
        PermissionInfo(
            "READ_SMS",
            PermissionInfo.Level.DANGEROUS,
            "Reads incoming SMS. Needed by SMS backup tools and some banking apps for OTP auto-fill. Extremely sensitive — malware uses it to steal one-time codes."
        ),
        PermissionInfo(
            "SYSTEM_ALERT_WINDOW",
            PermissionInfo.Level.SPECIAL,
            "Draws over other apps. Essential for screen recorders, floating widgets, and accessibility overlays. Enables banking overlay attacks. Few legitimate apps need it."
        ),
        PermissionInfo(
            "BIND_ACCESSIBILITY_SERVICE",
            PermissionInfo.Level.SPECIAL,
            "Reads the entire screen and simulates taps. Extremely powerful. Legitimate uses: screen readers, automation tools. Malicious uses: keylogging, overlay fraud, remote control."
        ),
        PermissionInfo(
            "DEVICE_ADMIN",
            PermissionInfo.Level.SPECIAL,
            "Locks or wipes the device, enforces passwords. Required for enterprise device management. Rarely appropriate for consumer apps."
        )
    )
}