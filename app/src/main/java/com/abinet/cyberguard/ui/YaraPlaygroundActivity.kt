package com.abinet.cyberguard.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.abinet.cyberguard.R
import com.abinet.cyberguard.engine.YaraRuleEngine

class YaraPlaygroundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yara_playground)

        val rules = YaraRuleEngine.allRules()
        if (rules.isEmpty()) { finish(); return }

        val spinner = findViewById<Spinner>(R.id.spinner_rule)
        val sampleInput = findViewById<EditText>(R.id.et_sample)
        val ruleInfo = findViewById<TextView>(R.id.tv_rule_info)
        val resultView = findViewById<TextView>(R.id.tv_result)
        val runBtn = findViewById<Button>(R.id.btn_run_rule)

        val names = rules.map { it.name }
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, names)

        fun loadRuleInfo() {
            val rule = rules[spinner.selectedItemPosition]
            ruleInfo.text = buildString {
                appendLine("Rule: ${rule.name}")
                appendLine("Condition: ${rule.condition}")
                appendLine("Strings:")
                rule.strings.forEach { appendLine("  • $it") }
            }
            sampleInput.setText(rule.strings.joinToString(" "))
        }

        spinner.setOnItemSelectedListener(object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: android.view.View?, position: Int, id: Long) = loadRuleInfo()
            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {}
        })

        loadRuleInfo()

        runBtn.setOnClickListener {
            val text = sampleInput.text?.toString().orEmpty()
            val matches = YaraRuleEngine.scan(listOf(text))
            resultView.text = if (matches.isEmpty()) {
                getString(R.string.yara_no_match_result)
            } else {
                val (name, matchedStrings) = matches.first()
                getString(R.string.yara_match_result, name, matchedStrings)
            }
        }
    }
}