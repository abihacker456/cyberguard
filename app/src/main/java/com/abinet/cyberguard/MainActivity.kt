package com.abinet.cyberguard.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abinet.cyberguard.ProgressRepository
import com.abinet.cyberguard.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var tvProgressSummary: TextView
    private lateinit var rvLessons: RecyclerView

    private val adapter = LessonAdapter()

    private val openApk = registerForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri ->
        if (uri != null) {
            val intent = Intent(this, ApkScanActivity::class.java).apply {
                data = uri
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvProgressSummary = findViewById(R.id.tv_progress_summary)
        rvLessons = findViewById(R.id.rv_lessons)

        rvLessons.layoutManager = LinearLayoutManager(this)
        rvLessons.adapter = adapter

        findViewById<View>(R.id.btn_open_apk_lab).setOnClickListener {
            openApk.launch(arrayOf("application/vnd.android.package-archive"))
        }
        findViewById<View>(R.id.btn_open_quiz).setOnClickListener {
            startActivity(Intent(this, PhishingQuizActivity::class.java))
        }
        findViewById<View>(R.id.btn_open_yara_playground).setOnClickListener {
            startActivity(Intent(this, YaraPlaygroundActivity::class.java))
        }
        findViewById<View>(R.id.btn_open_permissions).setOnClickListener {
            startActivity(Intent(this, PermissionsActivity::class.java))
        }
        findViewById<View>(R.id.btn_open_encyclopedia).setOnClickListener {
            startActivity(Intent(this, EncyclopediaActivity::class.java))
        }
        findViewById<View>(R.id.btn_settings).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        observeProgress()
    }

    private fun observeProgress() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                ProgressRepository.progress.collectLatest { p ->
                    tvProgressSummary.text = getString(
                        R.string.progress_summary,
                        p.lessonCount,
                        LessonCatalog.ALL.size
                    )
                    adapter.submit(p.completedLessons)
                }
            }
        }
    }

    inner class LessonAdapter : RecyclerView.Adapter<LessonAdapter.VH>() {
        private var completed: Set<String> = emptySet()

        fun submit(completedLessons: Set<String>) {
            completed = completedLessons
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_lesson, parent, false)
            return VH(v)
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            val lesson = LessonCatalog.ALL[position]
            val done = lesson.id in completed
            holder.title.text = lesson.title
            holder.subtitle.text = lesson.subtitle
            holder.status.text = if (done) "✓ COMPLETED" else "○ NOT STARTED"
            holder.status.setTextColor(if (done) 0xFF00FF00.toInt() else 0xFFFFAA00.toInt())
            holder.itemView.setOnClickListener {
                val intent = Intent(this@MainActivity, LessonDetailActivity::class.java).apply {
                    putExtra(LessonDetailActivity.EXTRA_LESSON_ID, lesson.id)
                }
                startActivity(intent)
            }
        }

        override fun getItemCount() = LessonCatalog.ALL.size

        inner class VH(v: View) : RecyclerView.ViewHolder(v) {
            val title: TextView = v.findViewById(R.id.tv_lesson_title)
            val subtitle: TextView = v.findViewById(R.id.tv_lesson_subtitle)
            val status: TextView = v.findViewById(R.id.tv_lesson_status)
        }
    }
}