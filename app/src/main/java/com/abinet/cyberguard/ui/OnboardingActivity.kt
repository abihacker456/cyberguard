package com.abinet.cyberguard.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.abinet.cyberguard.R

class OnboardingActivity : AppCompatActivity() {

    companion object {
        private const val PREFS_NAME = "cyberguard_prefs"
        private const val KEY_ONBOARDING_DONE = "onboarding_done"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        if (prefs.getBoolean(KEY_ONBOARDING_DONE, false)) {
            startMainActivity()
            return
        }

        setContentView(R.layout.activity_onboarding)

        findViewById<Button>(R.id.btn_start_learning).setOnClickListener {
            prefs.edit().putBoolean(KEY_ONBOARDING_DONE, true).apply()
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}