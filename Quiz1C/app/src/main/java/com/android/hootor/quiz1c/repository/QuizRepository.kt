package com.android.hootor.quiz1c.repository

import com.android.hootor.quiz1c.domain.QuizItem
import kotlin.random.Random

class QuizRepository {

    fun getQuizItems(): List<QuizItem.Quiz> {
        return listOf(
            QuizItem.Quiz(
                id = "1q", title = "Управление торговлей, редакция 11"
            ),
            QuizItem.Quiz(
                id = "2q", title = "Бухгалтерия предприятия, редакция 3.0"
            ),
            QuizItem.Quiz(
                id = "3q", title = "1С:ERP Управление предприятием 2"
            ),
            QuizItem.Quiz(
                id = "4q", title = "Документооборот КОРП"
            ),
            QuizItem.Quiz(
                id = "5q", title = "Зарплата и Управление Персоналом, редакция 3"
            ),
            QuizItem.Quiz(
                id = "6q", title = "Комплексная автоматизация, редакция 2"
            ),
            QuizItem.Quiz(
                id = "7q", title = "Розница, редакция 2.3"
            ),
            QuizItem.Quiz(
                id = "8q", title = "Управление нашей фирмой базовая, редакция 1.6"
            )
        )
    }

    fun getCategories(idQuiz: String): List<QuizItem.Categories> = (0 until 14).map {
        QuizItem.Categories(
            id = "${it}-${idQuiz}",
            idQuiz = idQuiz,
            title = "Раздел-${it}"
        )
    }


    fun getQuestions(idQuiz: String): List<QuizItem.Question> {

        val category = getCategories(idQuiz)
        val questions = mutableListOf<QuizItem.Question>()
        for (cat in category) {
            questions.add(randomQuestions(idQuiz, cat))
        }

        return questions
    }

    fun getAnswers(idQuestion: String): List<QuizItem.Answer> {
        return randomAnswers(idQuestion)
    }

    private fun randomQuestions(idQuiz: String, category: QuizItem.Categories): QuizItem.Question =

        when (idQuiz) {
            "1q" -> {

                QuizItem.Question(
                    id = idQuiz + "-${Random.nextInt(9)}",
                    idQuiz = idQuiz,
                    title = category.toString() + "-УТ",
                    correctAnswers = Random.nextInt(5).toString(),
                    idCategories = category.id,
                    category = category
                )

            }
            "2q" -> {

                QuizItem.Question(
                    id = idQuiz + "-${Random.nextInt(9)}",
                    idQuiz = idQuiz,
                    title = category.toString() + "-БП",
                    correctAnswers = Random.nextInt(5).toString(),
                    idCategories = category.id,
                    category = category
                )

            }
            "3q" -> {

                QuizItem.Question(
                    id = idQuiz + "-${Random.nextInt(9)}",
                    idQuiz = idQuiz,
                    title = category.toString() + "-ERP",
                    correctAnswers = Random.nextInt(5).toString(),
                    idCategories = category.id,
                    category = category
                )

            }
            "4q" -> {

                QuizItem.Question(
                    id = idQuiz + "-${Random.nextInt(9)}",
                    idQuiz = idQuiz,
                    title = category.toString() + "-ДО",
                    correctAnswers = Random.nextInt(5).toString(),
                    idCategories = category.id,
                    category = category
                )

            }
            "5q" -> {

                QuizItem.Question(
                    id = idQuiz + "-${Random.nextInt(9)}",
                    idQuiz = idQuiz,
                    title = category.toString() + "-ЗУП",
                    correctAnswers = Random.nextInt(5).toString(),
                    idCategories = category.id,
                    category = category
                )

            }
            "6q" -> {

                QuizItem.Question(
                    id = idQuiz + "-${Random.nextInt(9)}",
                    idQuiz = idQuiz,
                    title = category.toString() + "-КА2",
                    correctAnswers = Random.nextInt(5).toString(),
                    idCategories = category.id,
                    category = category
                )

            }
            "7q" -> {

                QuizItem.Question(
                    id = idQuiz + "-${Random.nextInt(9)}",
                    idQuiz = idQuiz,
                    title = category.toString() + "-РР",
                    correctAnswers = Random.nextInt(5).toString(),
                    idCategories = category.id,
                    category = category
                )
            }
            "8q" -> {

                QuizItem.Question(
                    id = idQuiz + "-${Random.nextInt(9)}",
                    idQuiz = idQuiz,
                    title = category.toString() + "-УНФ",
                    correctAnswers = Random.nextInt(5).toString(),
                    idCategories = category.id,
                    category = category
                )

            }
            else -> QuizItem.Question(
                id = idQuiz + "-${Random.nextInt(9)}",
                idQuiz = idQuiz,
                title = category.toString() + "-неопределено",
                correctAnswers = Random.nextInt(5).toString(),
                idCategories = category.id,
                category = category
            )
        }

    private fun randomAnswers(idQuestion: String): List<QuizItem.Answer> {

        return (0 until 6).map {
            QuizItem.Answer(
                id = idQuestion + "-${Random.nextInt(9)}",
                title = "Ответ ${it + 1}",
                idQuestion = idQuestion
            )
        }

    }
}