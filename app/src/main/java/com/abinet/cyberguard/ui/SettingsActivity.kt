package com.abinet.cyberguard.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.abinet.cyberguard.BuildConfig
import com.abinet.cyberguard.ProgressRepository
import com.abinet.cyberguard.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        findViewById<TextView>(R.id.tv_version).text =
            "CyberGuard Academy v${BuildConfig.VERSION_NAME}"

        findViewById<Button>(R.id.btn_reset_progress).setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(R.string.reset_title)
                .setMessage(R.string.reset_message)
                .setPositiveButton(R.string.reset_confirm) { _, _ ->
                    ProgressRepository.reset()
                }
                .setNegativeButton(android.R.string.cancel, null)
                .show()
        }

        findViewById<Button>(R.id.btn_contact_developer).setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:${getString(R.string.dev_email)}")
                putExtra(Intent.EXTRA_SUBJECT, "CyberGuard Academy feedback")
            }
            try { startActivity(intent) } catch (_: Exception) { }
        }

        findViewById<Button>(R.id.btn_privacy_policy).setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(R.string.privacy_title)
                .setMessage(R.string.privacy_body)
                .setPositiveButton(android.R.string.ok, null)
                .show()
        }
    }
}