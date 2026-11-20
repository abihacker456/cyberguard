package com.abinet.cyberguard.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.abinet.cyberguard.ProgressRepository
import com.abinet.cyberguard.R

class PhishingQuizActivity : AppCompatActivity() {

    private lateinit var counter: TextView
    private lateinit var scenario: TextView
    private lateinit var feedback: TextView
    private lateinit var next: Button
    private lateinit var answerButtons: List<Button>

    private var index = 0
    private var correctCount = 0
    private var answered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phishing_quiz)

        counter = findViewById(R.id.tv_quiz_counter)
        scenario = findViewById(R.id.tv_quiz_scenario)
        feedback = findViewById(R.id.tv_quiz_feedback)
        next = findViewById(R.id.btn_quiz_next)
        answerButtons = listOf(
            findViewById(R.id.btn_answer_1),
            findViewById(R.id.btn_answer_2),
            findViewById(R.id.btn_answer_3),
            findViewById(R.id.btn_answer_4)
        )

        next.setOnClickListener {
            index++
            if (index < QuizData.QUESTIONS.size) render() else showResult()
        }

        render()
    }

    private fun render() {
        answered = false
        val q = QuizData.QUESTIONS[index]
        counter.text = getString(R.string.quiz_question_counter, index + 1, QuizData.QUESTIONS.size)
        scenario.text = q.scenario
        feedback.visibility = View.GONE
        next.visibility = View.GONE

        answerButtons.forEachIndexed { i, btn ->
            if (i < q.options.size) {
                btn.visibility = View.VISIBLE
                btn.text = q.options[i]
                btn.isEnabled = true
                btn.setBackgroundColor(Color.parseColor("#444444"))
                btn.setOnClickListener { onAnswer(i, q) }
            } else {
                btn.visibility = View.GONE
            }
        }
    }

    private fun onAnswer(selected: Int, q: QuizQuestion) {
        if (answered) return
        answered = true
        val correct = selected == q.correctIndex
        if (correct) correctCount++
        ProgressRepository.recordQuizAnswer(correct)

        answerButtons.forEachIndexed { i, btn ->
            btn.isEnabled = false
            when (i) {
                q.correctIndex -> btn.setBackgroundColor(Color.parseColor("#1B5E20"))
                selected -> btn.setBackgroundColor(Color.parseColor("#B71C1C"))
            }
        }

        feedback.text = (if (correct) getString(R.string.quiz_correct) else getString(R.string.quiz_wrong)) +
                "\n\n" + q.explanation
        feedback.visibility = View.VISIBLE
        next.visibility = View.VISIBLE
        next.text = if (index == QuizData.QUESTIONS.size - 1)
            getString(R.string.quiz_see_result) else getString(R.string.quiz_next)
    }

    private fun showResult() {
        val total = QuizData.QUESTIONS.size
        AlertDialog.Builder(this)
            .setTitle(R.string.quiz_result_title)
            .setMessage(getString(R.string.quiz_result_score, correctCount, total) + "\n\n" + getString(R.string.quiz_result_message))
            .setPositiveButton(R.string.quiz_back_to_dashboard) { _, _ -> finish() }
            .setNegativeButton(R.string.quiz_try_again) { _, _ ->
                index = 0; correctCount = 0; render()
            }
            .setCancelable(false)
            .show()
    }
}