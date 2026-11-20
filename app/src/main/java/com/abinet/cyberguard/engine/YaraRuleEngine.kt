package com.abinet.cyberguard.engine

import android.content.Context
import com.abinet.cyberguard.R
import java.io.BufferedReader
import java.io.InputStreamReader

data class YaraRule(
    val name: String,
    val condition: String, // "any" or "all"
    val strings: MutableList<String> = mutableListOf()
)

object YaraRuleEngine {

    private val rules = mutableListOf<YaraRule>()

    fun loadRules(context: Context) {
        rules.clear()
        try {
            context.resources.openRawResource(R.raw.yara_rules).use { input ->
                BufferedReader(InputStreamReader(input)).use { reader ->
                    var currentRule: YaraRule? = null
                    reader.forEachLine { line ->
                        val trimmed = line.trim()
                        if (trimmed.isEmpty() || trimmed.startsWith("#")) return@forEachLine
                        if (trimmed.startsWith("rule ")) {
                            val body = trimmed.removePrefix("rule ").trim()
                            val idx = body.lastIndexOf(':')
                            if (idx > 0) {
                                val name = body.substring(0, idx).trim()
                                val condition = body.substring(idx + 1).trim().lowercase()
                                currentRule = YaraRule(name, condition)
                                rules.add(currentRule!!)
                            }
                        } else if (currentRule != null) {
                            currentRule!!.strings.add(trimmed.lowercase())
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun scan(strings: List<String>): List<Pair<String, String>> {
        val matches = mutableListOf<Pair<String, String>>()
        val lowerStrings = strings.map { it.lowercase() }
        for (rule in rules) {
            val found = rule.strings.filter { pattern -> lowerStrings.any { it.contains(pattern) } }
            val matched = when (rule.condition) {
                "any" -> found.isNotEmpty()
                "all" -> found.size == rule.strings.size
                else -> false
            }
            if (matched) matches.add(rule.name to found.joinToString(", "))
        }
        return matches
    }

    fun allRules(): List<YaraRule> = rules.toList()
}