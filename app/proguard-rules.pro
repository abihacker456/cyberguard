# Preserve Application class (referenced by manifest)
-keep class com.abinet.cyberguard.CyberGuardApp { *; }

# Keep annotations and generic signatures for Kotlin
-keepattributes Signature
-keepattributes *Annotation*

# Coroutines
-keepclassmembers class kotlinx.coroutines.** { volatile <fields>; }