package com.abinet.cyberguard

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object ProgressRepository {

    private const val PREFS = "cyberguard_progress"
    private const val KEY_COMPLETED = "completed_lessons"
    private const val KEY_QUIZ_CORRECT = "quiz_correct"
    private const val KEY_QUIZ_TOTAL = "quiz_total"

    data class Progress(
        val completedLessons: Set<String> = emptySet(),
        val quizCorrect: Int = 0,
        val quizTotal: Int = 0
    ) {
        val lessonCount: Int get() = completedLessons.size
    }

    private val _progress = MutableStateFlow(Progress())
    val progress: StateFlow<Progress> = _progress.asStateFlow()

    private var prefs: SharedPreferences? = null

    fun init(context: Context) {
        prefs = context.applicationContext.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        load()
    }

    private fun load() {
        val p = prefs ?: return
        val completed = p.getStringSet(KEY_COMPLETED, emptySet()) ?: emptySet()
        val correct = p.getInt(KEY_QUIZ_CORRECT, 0)
        val total = p.getInt(KEY_QUIZ_TOTAL, 0)
        _progress.value = Progress(completed, correct, total)
    }

    fun markLessonComplete(id: String) {
        val updated = _progress.value.copy(
            completedLessons = _progress.value.completedLessons + id
        )
        _progress.value = updated
        prefs?.edit()?.putStringSet(KEY_COMPLETED, updated.completedLessons)?.apply()
    }

    fun recordQuizAnswer(correct: Boolean) {
        val updated = _progress.value.copy(
            quizCorrect = _progress.value.quizCorrect + if (correct) 1 else 0,
            quizTotal = _progress.value.quizTotal + 1
        )
        _progress.value = updated
        prefs?.edit()
            ?.putInt(KEY_QUIZ_CORRECT, updated.quizCorrect)
            ?.putInt(KEY_QUIZ_TOTAL, updated.quizTotal)
            ?.apply()
    }

    fun reset() {
        _progress.value = Progress()
        prefs?.edit()?.clear()?.apply()
    }
}