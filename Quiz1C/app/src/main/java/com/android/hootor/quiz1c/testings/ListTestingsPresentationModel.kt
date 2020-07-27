package com.android.hootor.quiz1c.testings

import com.android.hootor.quiz1c.base.BaseViewModel
import com.android.hootor.quiz1c.domain.QuizInteractor
import com.android.hootor.quiz1c.domain.QuizItem
import com.android.hootor.quiz1c.domain.toViewModel
import com.example.delegateadapter.delegate.diff.IComparableItem

class ListTestingsPresentationModel(
    val interactor: QuizInteractor = QuizInteractor()
) : BaseViewModel<QuizListViewState, QuizListAction, QuizListEvent>() {

    init {

        viewState = QuizListViewState(
            status = QuizListStatus.Loading,
            items = emptyList()
        )

        val newListItems = interactor.getQuizItems().map {
            it.toViewModel()
        }
        viewState = viewState.copy(
            items = newListItems
        )

    }

    override fun obtainEvent(viewEvent: QuizListEvent) {
        when (viewEvent) {
            is QuizListEvent.UpdateList -> {
                viewState = QuizListViewState(
                    status = QuizListStatus.Loading,
                    items = emptyList()
                )
                val newListItems = interactor.getQuizItems().map {
                    it.toViewModel()
                }
                viewState = viewState.copy(
                    items = newListItems
                )
            }
            is QuizListEvent.OnClickItemList -> {
                viewAction = QuizListAction.OpenTestingFragment(viewEvent.itemQuiz)
            }
        }
    }

}


data class QuizListViewState(
    val status: QuizListStatus,
    val items: List<IComparableItem>
)

sealed class QuizListStatus() {
    object Loading : QuizListStatus()
    data class ShowError(val error: String) : QuizListStatus()
}

sealed class QuizListAction() {
    data class ShowSnackbar(val message: String) : QuizListAction()
    data class OpenTestingFragment(val itemQuiz: QuizItem.Quiz) : QuizListAction()
}

sealed class QuizListEvent() {
    object UpdateList : QuizListEvent()
    data class OnClickItemList(val itemQuiz: QuizItem.Quiz) : QuizListEvent()
}