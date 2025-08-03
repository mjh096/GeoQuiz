package com.example.geoquiz

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    // Initialise widgets to be used in the activity
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var prevButton: Button
    private lateinit var questionTextView: TextView

    // List of questions
    private val questionBank = listOf(
        Question(R.string.question_01, true),
        Question(R.string.question_02, true),
        Question(R.string.question_03, false),
        Question(R.string.question_04, true),
        Question(R.string.question_05, true)
    )

    // Tracks the current question being shown
    private var currentIndex = 0

    // Controller: Called when Activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() called")
        enableEdgeToEdge()

        // Loads GUI layout
        setContentView(R.layout.activity_main)

        // Connect to UI elements in the view using their IDs
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        prevButton = findViewById(R.id.prev_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)

        // Sets the question text when the activity loads
        updateQuestion()

        // Event listener: Handles True button click
        trueButton.setOnClickListener {checkAnswer(true)}

        // Event listener: Handles False button click
        falseButton.setOnClickListener {checkAnswer(false)}

        // Event listener: Handles Next button click
        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        // Event listener: Handles Previous button click
        prevButton.setOnClickListener {
            currentIndex = if (currentIndex - 1 < 0) questionBank.size - 1
            else currentIndex - 1
            updateQuestion()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    // Sets the text of the question on the view
    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    // Checks the user's answer and shows a toast
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
}