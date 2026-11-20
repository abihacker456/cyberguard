package com.abinet.cyberguard.ui

import android.app.AlertDialog
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.abinet.cyberguard.R
import com.abinet.cyberguard.engine.MalwareScanner
import kotlinx.coroutines.*
import java.io.File
import java.io.FileOutputStream

class ApkScanActivity : AppCompatActivity() {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uri = intent?.data ?: run {
            Toast.makeText(this, R.string.apk_lab_no_file, Toast.LENGTH_SHORT).show()
            finish(); return
        }
        Toast.makeText(this, R.string.apk_lab_analyzing, Toast.LENGTH_SHORT).show()

        scope.launch {
            val result = analyze(uri)
            withContext(Dispatchers.Main) {
                showResult(result)
            }
        }
    }

    private suspend fun analyze(uri: Uri): String = withContext(Dispatchers.IO) {
        val temp = File(cacheDir, "lab_sample.apk")
        try {
            contentResolver.openInputStream(uri)?.use { input ->
                FileOutputStream(temp).use { out -> input.copyTo(out) }
            } ?: return@withContext getString(R.string.apk_lab_copy_failed)

            val pkgInfo = packageManager.getPackageArchiveInfo(
                temp.absolutePath, android.content.pm.PackageManager.GET_PERMISSIONS
            )
            val hits = MalwareScanner.scanApk(temp, pkgInfo)
            if (hits.isEmpty()) {
                getString(R.string.apk_lab_no_signals)
            } else {
                buildString {
                    appendLine(getString(R.string.apk_lab_signals_header))
                    appendLine()
                    hits.forEach { (sig, desc) ->
                        appendLine("• ${sig.id}  ${sig.description}")
                        appendLine("  ${getString(R.string.apk_lab_severity_label)} ${sig.severity}/10")
                        appendLine()
                    }
                    appendLine(getString(R.string.apk_lab_disclaimer))
                }
            }
        } catch (_: Exception) {
            getString(R.string.apk_lab_error)
        } finally {
            temp.delete()
        }
    }

    private fun showResult(body: String) {
        AlertDialog.Builder(this)
            .setTitle(R.string.apk_lab_result_title)
            .setMessage(body)
            .setPositiveButton(android.R.string.ok) { _, _ -> finish() }
            .setOnDismissListener { finish() }
            .show()
    }

    override fun onDestroy() {
        scope.cancel()
        super.onDestroy()
    }
}