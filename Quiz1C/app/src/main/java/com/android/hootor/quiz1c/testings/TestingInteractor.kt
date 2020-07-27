package com.android.hootor.quiz1c.testings

import com.android.hootor.quiz1c.domain.QuizItem
import com.android.hootor.quiz1c.repository.QuizRepository

class TestingInteractor(
    val quizRepository: QuizRepository = QuizRepository()
) {

    fun getQuestions(idQuiz: String): List<QuizItem.Question> = quizRepository.getQuestions(idQuiz)

    fun getAnswers(idQuestion: String) : List<QuizItem.Answer> = quizRepository.getAnswers(idQuestion)

}