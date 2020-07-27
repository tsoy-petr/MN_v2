package com.android.hootor.quiz1c.domain

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.android.hootor.quiz1c.view.adapter.QuizViewModel

sealed class QuizItem(open val id: String) {

    
    @Entity
    data class Quiz(
        @PrimaryKey
        override val id: String,
        val title: String
    ) : QuizItem(id)

    @Entity(
        foreignKeys = [ForeignKey(
            entity = Quiz::class,
            parentColumns = ["id"],
            childColumns = ["idQuiz"],
            onDelete = CASCADE
        )]
    )
    data class Categories(
        @PrimaryKey
        override val id: String,
        val idQuiz: String,
        val title: String
    ) : QuizItem(id)

    @Entity(
        foreignKeys = [ForeignKey(
            entity = Quiz::class,
            parentColumns = ["id"],
            childColumns = ["idQuiz"],
            onDelete = CASCADE
        )], primaryKeys = ["id", "idQuiz", "idCategories"]
    )
    data class Question(
        override val id: String,
        val idQuiz: String,
        val idCategories: String,
        val title: String,
        val correctAnswers: String,
        @Ignore
        val category: Categories
    ) : QuizItem(id)

    @Entity(
        foreignKeys = [ForeignKey(
            entity = Question::class,
            parentColumns = ["id"],
            childColumns = ["idQuestion"],
            onDelete = CASCADE
        )]
    )
    data class Answer(
        @PrimaryKey
        override val id: String,
        val idQuestion: String,
        val title: String
    ) : QuizItem(id)
}

fun QuizItem.Quiz.toViewModel(): QuizViewModel {
    return QuizViewModel(this.title, this)
}