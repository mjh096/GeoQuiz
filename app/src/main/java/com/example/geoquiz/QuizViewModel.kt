package com.example.geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    // List of questions
    private val questionBank = listOf(
        Question(R.string.question_01, true),
        Question(R.string.question_02, true),
        Question(R.string.question_03, false),
        Question(R.string.question_04, true),
        Question(R.string.question_05, true)
    )

    var currentIndex: Int
        get() = savedStateHandle["currentIndex"] ?: 0
        set(value) {
            savedStateHandle["currentIndex"] = value
        }

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionTextResId: Int
        get() = questionBank[currentIndex].textResId
}