package com.android.hootor.quiz1c.view.model

data class QuestionModel(
    val id: String,
    val title: String,
    val idCorrectAnswer: String
)

data class AnswerModel(
    val id: String,
    val title: String
)