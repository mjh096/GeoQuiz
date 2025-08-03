package com.example.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CheatActivity : AppCompatActivity() {

    private lateinit var answerTextView: TextView
    private lateinit var showAnswerButton: Button

    private var answerIsTrue = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        setSupportActionBar(findViewById(R.id.top_app_bar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)

        showAnswerButton.setOnClickListener {
            val answerText = if (answerIsTrue) {
                R.string.true_button
            } else {
                R.string.false_button
            }
            answerTextView.setText(answerText)
            setAnswerShownResult(true)
        }

        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            finish() // Return to MainActivity
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish() // Close CheatActivity and return to MainActivity
        return true
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }

    companion object {
        private const val EXTRA_ANSWER_IS_TRUE = "com.example.geoquiz.answer_is_true"
        const val EXTRA_ANSWER_SHOWN = "com.example.geoquiz.answer_shown"

        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}