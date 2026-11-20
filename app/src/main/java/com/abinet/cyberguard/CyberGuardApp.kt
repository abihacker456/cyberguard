package com.abinet.cyberguard

import android.app.Application
import com.abinet.cyberguard.engine.MalwareScanner

class CyberGuardApp : Application() {
    override fun onCreate() {
        super.onCreate()
        MalwareScanner.init(this)
        ProgressRepository.init(this)
    }
}