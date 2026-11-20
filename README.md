# CyberGuard Academy

![Android](https://img.shields.io/badge/Android-API%2036-green)
![Kotlin](https://img.shields.io/badge/Kotlin-2.1.0-blue)
![Status](https://img.shields.io/badge/Status-Closed%20Testing-orange)

An offline educational Android app for learning cybersecurity fundamentals. 

> **⚠️ IMPORTANT DISCLAIMER:** 
> CyberGuard Academy is an **educational app**. It does **NOT** scan, monitor, clean, or protect your device. Any analysis displayed is for learning purposes only. A "match" reported by the app is not proof of malice, and a "clean" result is not proof of safety.

## 📱 About the App

CyberGuard Academy was originally conceptualized as a mobile antivirus, but was intentionally pivoted into an educational tool to comply with Google Play's strict security and deception policies. It teaches users how malware families work, how phishing attacks are structured, and how simple heuristic analysis functions.

**Key constraints of the app:**
*   **Zero Permissions:** The manifest requests no permissions.
*   **100% Offline:** No network access, no telemetry, no analytics.
*   **No Accounts:** Data is stored locally via `SharedPreferences`.

## ✨ Features

*   **7 Lessons:** Plain-English explanations of Malware 101, Banking Trojans, Ransomware, Phishing, APK Anatomy, Permissions, and Pattern Rules.
*   **APK Analysis Lab:** Pick an APK from storage to see how simple heuristics work. Demonstrates why a "clean" result is not a guarantee of safety.
*   **Phishing Quiz:** 5 real-world-style scenarios with immediate feedback and explanations.
*   **YARA Playground:** Interact with simplified, YARA-inspired pattern rules to understand signature-based detection and false positives.
*   **Threat Encyclopedia:** Reference cards for 6 major malware families.
*   **Permissions Explainer:** Static reference guide for Android's NORMAL, DANGEROUS, and SPECIAL permissions.
*   **Progress Tracking:** Completed lessons and quiz scores persist locally.

## 🛠 Tech Stack

*   **Language:** Kotlin
*   **UI:** XML Views, Material Design 3
*   **Architecture:** Activity-based, `SharedPreferences` for local state
*   **Target SDK:** 36 (Android 16)
*   **Min SDK:** 26 (Android 8.0)
*   **Build System:** Gradle 8.11.1 (Kotlin DSL)

## 🚀 Building from Source

1. Clone the repository:
   ```bash
   git clone https://github.com/abihacker456/cyberguard.git
2. Open the project in Android Studio (Ladybug or newer).

3. Allow Gradle to sync and download dependencies.

4. Build the debug APK:

bash
./gradlew :app:assembleDebug
🔒 Privacy
CyberGuard Academy collects no personal data and requires no permissions. Read the full Privacy Policy.

Built by Abinet Endale