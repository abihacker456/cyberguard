package com.abinet.cyberguard.ui

object LessonContent {

    private val map: Map<String, List<String>> = mapOf(

        "malware_101" to listOf(
            "Malware — short for malicious software — is any program designed to harm, steal from, or spy on a user. The term covers many families: viruses, worms, trojans, ransomware, spyware, and adware.",
            "On Android, malware typically arrives through third-party app stores, sideloaded APKs, or disguised 'mods' for popular apps. Legitimate apps rarely ask you to disable security features or install 'certificates' from a browser.",
            "Google Play Protect scans installed apps and sideloaded APKs against Google's threat database. It is not perfect, but it catches the majority of known threats. Keeping it enabled is the single most effective baseline defense.",
            "Core defense basics: only install from trusted sources, review permission requests before granting them, keep the operating system and apps updated, and be extremely wary of 'cracked' or 'modded' apps promising impossible features.",
            "If you suspect an app is malicious, uninstall it, revoke any Accessibility permissions it may hold, and change passwords for any accounts you accessed on the device."
        ),

        "banking_trojan" to listOf(
            "Banking trojans are a family of malware that target financial apps directly. On Android, they typically pose as unrelated apps (games, utilities, 'system updates') and then overlay a fake login screen on top of a real banking app.",
            "The overlay technique requires the SYSTEM_ALERT_WINDOW permission. When the victim opens their real bank app, the malware detects the foreground package and draws a nearly identical fake UI over it. Credentials entered are captured and sent to the attacker.",
            "Modern banking trojans often use Accessibility Services to read screen content and simulate taps, allowing them to automate fraud without any direct user interaction.",
            "Real banks never ask for your full password in an app, never send SMS with login links, and never ask you to install 'security updates' from outside the Play Store. Treat any of these signals as a red flag.",
            "If you suspect a banking trojan: disable Accessibility for any non-essential apps, run Play Protect, and use a different device to change bank credentials until the phone is cleaned."
        ),

        "ransomware" to listOf(
            "Ransomware encrypts your files and demands payment for the decryption key. On Android it is less common than on Windows, but it exists — typically disguised as a photo gallery, PDF reader, or adult-content app.",
            "Android's sandbox model limits what ransomware can reach. Without storage permissions, a malicious app can only encrypt files inside its own private directory, which is harmless. With storage access, the impact is much greater.",
            "The best defense is prevention: back up important data to cloud storage or a separate device, avoid granting broad storage permissions to apps that do not need them, and never open unexpected attachments.",
            "Ransomware does not stop at files. Some variants lock the screen, change the wallpaper, or disable input to simulate a complete takeover. Safe Mode can often interrupt this behavior long enough to uninstall.",
            "If you are hit: do not pay. Connect the phone to Wi-Fi, back up anything not yet encrypted, factory reset, and change all account passwords from a clean device."
        ),

        "phishing" to listOf(
            "Phishing is the practice of tricking a user into revealing credentials or installing malware by impersonating a trusted entity. On mobile it arrives via email, SMS (smishing), WhatsApp, or a fake notification.",
            "Common signals: urgency ('your account will be closed'), generic greetings, mismatched sender domain, shortened URLs, requests to pay a small 'fee', or an unexpected prompt to log in through a link.",
            "Legitimate organizations almost never request that you log in through a link in an unexpected message. Open the official app or type the domain directly into the browser instead.",
            "If a message looks real, verify through a second channel — call the bank using the number on the back of your card, or log in through the app you already have installed.",
            "Report phishing messages to your provider. It helps protect other users and often leads to the attacker's infrastructure being taken down."
        ),

        "apk_anatomy" to listOf(
            "An APK is a zip archive containing the app's compiled code (DEX files), resources (res/), native libraries (.so files), and a manifest (AndroidManifest.xml) describing permissions, components, and metadata.",
            "When an analyst inspects an APK, the first stop is the manifest. It reveals requested permissions, exported components, intent filters, and whether the app is debuggable — an instant red flag when present in a release build.",
            "The DEX files contain Dalvik bytecode. Tools like jadx or apktool can decompile them into readable Java or smali. Analysts look for hardcoded URLs, encryption routines, dynamic code loading (DexClassLoader), and reflection.",
            "Native libraries (.so files) hide C/C++ code. Malware authors often push critical logic here to make reverse engineering harder. Strings in .so files are still extractable with simple tools.",
            "This lab's Pattern Analysis feature runs a simplified version of that process. It is a teaching tool, not a substitute for real analysis, and it will miss most genuine threats."
        ),

        "permissions" to listOf(
            "Android permissions fall into three categories: normal (granted automatically), dangerous (require user approval), and special (require the user to visit Settings). The category tells you how much trust the app is asking for.",
            "Dangerous permissions cover sensitive data: location, contacts, camera, microphone, SMS, call logs, and external storage. A flashlight app requesting contacts is suspicious. A messaging app requesting SMS is reasonable.",
            "Special permissions are far more powerful: SYSTEM_ALERT_WINDOW lets an app draw over other apps, BIND_ACCESSIBILITY_SERVICE lets it read and control everything on screen, and DEVICE_ADMIN can lock or wipe the device.",
            "The most dangerous combo to watch for is SYSTEM_ALERT_WINDOW plus BIND_ACCESSIBILITY_SERVICE. That combination enables overlay attacks and automated fraud. Very few legitimate apps need both.",
            "Before granting a permission, ask: does the app's core function genuinely require this? If you cannot think of a reason, deny it. You can grant permissions later if you change your mind."
        ),

        "yara_rules" to listOf(
            "Signature-based detection matches known patterns against a file. When the same pattern appears in multiple samples of the same malware family, a rule can reliably identify new samples of that family.",
            "YARA is a widely used rule language for describing malware. A rule has a name, a set of strings to search for, and a condition that combines them — 'any' means at least one match, 'all' means every string must be present.",
            "This app uses a simplified, educational version of that idea. A rule is a list of keywords with an any/all condition. It is powerful enough to illustrate the concept but far too naive for real defense.",
            "In practice, real rules are far more sophisticated: they use hexadecimal sequences, wildcards, regular expressions, offset constraints, and boolean logic. They are curated by threat intelligence teams and updated constantly.",
            "Try the Playground to see how a rule matches text. Notice how easy it is to trigger a false positive with a short string — that is the core challenge of signature detection, and why rules must be carefully written."
        )
    )

    fun paragraphsFor(lessonId: String): List<String> = map[lessonId].orEmpty()
}