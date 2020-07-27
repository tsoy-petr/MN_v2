package com.android.hootor.quiz1c.helpers

import com.android.hootor.quiz1c.domain.QuizItem
import com.android.hootor.quiz1c.view.adapter.AnswerViewModel
import com.android.hootor.quiz1c.view.adapter.QuestionViewModel
import com.android.hootor.quiz1c.view.model.AnswerModel
import com.android.hootor.quiz1c.view.model.QuestionModel

fun List<QuizItem.Answer>.toAnswerModel() : List<AnswerModel> = map { it.toModel() }


fun List<AnswerModel>.toAnswerViewModel() : List<AnswerViewModel> = map { it.toViewModel() }


fun QuizItem.Answer.toModel() = AnswerModel(id = this.id, title = this.title)

fun AnswerModel.toViewModel() = AnswerViewModel(title = this.title, payload = this)

fun List<QuizItem.Question>.toModel() = map { it.toModel() }


fun List<QuestionModel>.toViewModel() = map { it.toViewModel() }


fun QuizItem.Question.toModel(): QuestionModel = QuestionModel(id = this.id, title = this.title, idCorrectAnswer = this.correctAnswers)


fun QuestionModel.toViewModel(): QuestionViewModel = QuestionViewModel(title = this.title, payload = this)
