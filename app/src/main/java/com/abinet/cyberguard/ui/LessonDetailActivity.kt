package com.abinet.cyberguard.ui

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.abinet.cyberguard.ProgressRepository
import com.abinet.cyberguard.R

class LessonDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_detail)

        val lessonId = intent.getStringExtra(EXTRA_LESSON_ID)
        val lesson = LessonCatalog.ALL.firstOrNull { it.id == lessonId }
        if (lesson == null) { finish(); return }

        findViewById<TextView>(R.id.tv_lesson_title).text = lesson.title
        findViewById<TextView>(R.id.tv_lesson_subtitle).text = lesson.subtitle

        val body = LessonContent.paragraphsFor(lesson.id).joinToString("\n\n")
        findViewById<TextView>(R.id.tv_lesson_body).text = body

        findViewById<Button>(R.id.btn_mark_complete).setOnClickListener {
            ProgressRepository.markLessonComplete(lesson.id)
            Toast.makeText(this, R.string.lesson_complete_toast, Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    companion object {
        const val EXTRA_LESSON_ID = "lesson_id"
    }
}