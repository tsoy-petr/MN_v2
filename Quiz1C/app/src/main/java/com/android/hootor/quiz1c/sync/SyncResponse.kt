package com.android.hootor.quiz1c.sync

import com.android.hootor.quiz1c.domain.QuizItem
import com.android.hootor.quiz1c.remote.core.BaseResponse

class SyncResponse(
    success: Int,
    message: String,
    val data: List<QuizSync>
) : BaseResponse(success, message) {
}

data class QuizSync(
    val id: String,
    val title: String
)

fun QuizSync.toModel(): QuizItem.Quiz {
    return QuizItem.Quiz(id = this.id, title = this.title)
}

fun List<QuizSync>.toModelQuizItem() = map {
    it.toModel()
}
