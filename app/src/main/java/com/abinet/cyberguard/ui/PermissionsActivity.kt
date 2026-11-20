package com.abinet.cyberguard.ui

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.abinet.cyberguard.R

class PermissionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)

        val container = findViewById<LinearLayout>(R.id.container_permissions)

        PermissionData.ALL.forEach { p ->
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
            }

            val name = TextView(this).apply {
                text = p.name
                setTextColor(Color.WHITE)
                textSize = 14f
                setTypeface(typeface, android.graphics.Typeface.BOLD)
            }

            val level = TextView(this).apply {
                val (label, color) = when (p.level) {
                    PermissionInfo.Level.NORMAL -> getString(R.string.permissions_level_normal) to "#00FF00"
                    PermissionInfo.Level.DANGEROUS -> getString(R.string.permissions_level_dangerous) to "#FFAA00"
                    PermissionInfo.Level.SPECIAL -> getString(R.string.permissions_level_special) to "#FF0000"
                }
                text = label
                setTextColor(Color.parseColor(color))
                textSize = 11f
                gravity = Gravity.START
            }

            val desc = TextView(this).apply {
                text = p.description
                setTextColor(Color.parseColor("#CCFFFFFF"))
                textSize = 13f
                setPadding(0, 8, 0, 0)
            }

            card.addView(name)
            card.addView(level)
            card.addView(desc)
            container.addView(card)
        }
    }
}