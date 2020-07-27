package com.android.hootor.quiz1c.domain

import com.android.hootor.quiz1c.repository.QuizRepository

class QuizInteractor(
    val repository: QuizRepository = QuizRepository()
) {

    fun getQuizItems() : List<QuizItem.Quiz> {
        return repository.getQuizItems()
    }
}