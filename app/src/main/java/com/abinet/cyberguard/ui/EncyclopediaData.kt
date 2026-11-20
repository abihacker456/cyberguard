package com.abinet.cyberguard.ui

data class ThreatFamily(
    val name: String,
    val aka: String,
    val description: String,
    val indicators: String,
    val defense: String
)

object EncyclopediaData {

    val ALL = listOf(
        ThreatFamily(
            name = "Banking Trojan",
            aka = "BankBot, Anubis, Cerberus, Ermac",
            description = "Malware that overlays fake login screens on top of real banking apps to steal credentials. Modern variants also abuse Accessibility Services to automate fraudulent transfers.",
            indicators = "SYSTEM_ALERT_WINDOW + BIND_ACCESSIBILITY_SERVICE permissions; fake 'system update' prompts; overlays drawn on top of banking apps.",
            defense = "Disable Accessibility for any app you do not actively use. Verify the real banking app by checking its package name in Settings → Apps."
        ),

        ThreatFamily(
            name = "Spyware / Stalkerware",
            aka = "Cerberus, MobileSpy, commercial surveillance apps",
            description = "Secretly monitors calls, messages, location, and app activity. Often installed by someone with physical device access — a partner, family member, or employer.",
            indicators = "Unknown device admin apps; high battery drain from background services; unexplained data usage; apps with no icon.",
            defense = "Review Device Admin apps in Settings. Check 'Special app access' for Accessibility, Overlay, and Notification access. Reset the device if you suspect physical tampering."
        ),

        ThreatFamily(
            name = "Ransomware",
            aka = "FileCoders, Android locker variants",
            description = "Encrypts files or locks the screen, then demands payment for restoration. On Android, sandboxing limits reach; severe damage typically requires broad storage permissions.",
            indicators = "Files renamed with unfamiliar extensions; wallpaper suddenly changed; screen locked with a message demanding payment.",
            defense = "Back up to cloud or external storage regularly. Deny broad storage permissions. Boot into Safe Mode and uninstall the source app."
        ),

        ThreatFamily(
            name = "Dropper",
            aka = "Stage-1 loader, installer",
            description = "A harmless-looking app whose only job is to download and install the real malicious payload after installation. The real payload is often not detectable at first install.",
            indicators = "App requests INSTALL_PACKAGES or REQUEST_INSTALL_PACKAGES; contacts an unknown server shortly after install; short lifespan before uninstall.",
            defense = "Only install from trusted sources. Watch for apps that immediately prompt for unusual permissions or request installation of 'updates' from outside the Play Store."
        ),

        ThreatFamily(
            name = "Adware / Clicker",
            aka = "HiddenAds, click fraud families",
            description = "Hides in the launcher or runs invisibly, generating fraudulent ad clicks and revenue. Causes battery drain, data usage, and slow performance without obvious visible activity.",
            indicators = "Unexplained data usage; high battery drain; ads appearing in apps that do not normally show them; icon disappearing after install.",
            defense = "Audit installed apps monthly. Check 'Special app access' for overlay and notification access. Watch battery usage stats for unknown apps."
        ),

        ThreatFamily(
            name = "Phishing / Smishing",
            aka = "Fake login pages, message-based attacks",
            description = "Not malware itself, but the most common delivery mechanism for credential theft. Attacks impersonate banks, delivery services, employers, and family members via email, SMS, or chat apps.",
            indicators = "Urgency, generic greetings, mismatched sender domains, shortened URLs, requests for payment or login.",
            defense = "Verify through a second channel. Never log in through a link in an unexpected message. Use a password manager that refuses to autofill on unfamiliar domains."
        )
    )
}