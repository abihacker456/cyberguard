package com.abinet.cyberguard.ui

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.abinet.cyberguard.R

class EncyclopediaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encyclopedia)

        val container = findViewById<LinearLayout>(R.id.container_encyclopedia)

        EncyclopediaData.ALL.forEach { t ->
            val card = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(24, 24, 24, 24)
                setBackgroundResource(R.drawable.threat_card_bg)
                val lp = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                lp.bottomMargin = 12
                layoutParams = lp
                isClickable = true
                setOnClickListener { showDetail(t) }
            }

            val name = TextView(this).apply {
                text = t.name
                setTextColor(Color.WHITE)
                textSize = 16f
                setTypeface(typeface, android.graphics.Typeface.BOLD)
            }

            val aka = TextView(this).apply {
                text = t.aka
                setTextColor(Color.parseColor("#FFAA00"))
                textSize = 11f
                gravity = Gravity.START
            }

            card.addView(name)
            card.addView(aka)
            container.addView(card)
        }
    }

    private fun showDetail(t: ThreatFamily) {
        val body = buildString {
            appendLine(t.description)
            appendLine()
            appendLine(getString(R.string.encyclopedia_indicators))
            appendLine(t.indicators)
            appendLine()
            appendLine(getString(R.string.encyclopedia_defense))
            appendLine(t.defense)
        }
        AlertDialog.Builder(this)
            .setTitle(t.name)
            .setMessage(body)
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }
}