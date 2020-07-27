package com.android.hootor.quiz1c.testings

import androidx.lifecycle.viewModelScope
import com.android.hootor.quiz1c.base.BaseViewModel
import com.android.hootor.quiz1c.helpers.toAnswerModel
import com.android.hootor.quiz1c.helpers.toAnswerViewModel
import com.android.hootor.quiz1c.helpers.toModel
import com.android.hootor.quiz1c.helpers.toViewModel
import com.android.hootor.quiz1c.view.adapter.AnswerViewModel
import com.android.hootor.quiz1c.view.model.AnswerModel
import com.android.hootor.quiz1c.view.model.QuestionModel
import com.example.delegateadapter.delegate.diff.IComparableItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TestingPresentationModel(
    private val interactor: TestingInteractor = TestingInteractor()
) : BaseViewModel<TestingViewState, TestingAction, TestingEvent>() {

    private val listQuestions = listOf<QuestionModel>()
    private var idQuiz: String? = null
    private var question: QuestionModel? = null

    init {
        viewState = TestingViewState(
            TestingViewStatus.Loading
        )
    }

    override fun obtainEvent(viewEvent: TestingEvent) {
        when (viewEvent) {
            is TestingEvent.CurrentQuiz -> {
                idQuiz = viewEvent.idQuiz

                viewModelScope.launch(Dispatchers.IO) {
                    val items = interactor.getQuestions(viewEvent.idQuiz).toModel().toViewModel()
                    viewState = TestingViewState(TestingViewStatus.Success(questions = items))
                }
            }
            is TestingEvent.OnClickQuestion -> {

                viewModelScope.launch(Dispatchers.IO) {
                    question = viewEvent.question
                    val answers = interactor.getAnswers(viewEvent.question.id).toAnswerModel().toAnswerViewModel()
                    viewState = TestingViewState(
                        TestingViewStatus.ShowNewQuestion(
                            question = question!!,
                            answers = answers
                        )
                    )
                }
            }
            is TestingEvent.OnClickAnswer -> {

            }
        }
    }
}

data class TestingViewState(
    val status: TestingViewStatus
)

sealed class TestingViewStatus() {

    object Loading : TestingViewStatus()

    data class Success(
        val questions: List<IComparableItem>
    ) : TestingViewStatus()

    data class ShowNewQuestion(
        val question: QuestionModel,
        val answers: List<IComparableItem>
    ) : TestingViewStatus()

    data class Error(val message: String) : TestingViewStatus()
}

sealed class TestingAction() {
    data class ShowSnackbar(val message: String) : TestingAction()
    object ReturnListConfiguration : TestingAction()
}

sealed class TestingEvent() {
    data class OnClickAnswer(val answer: AnswerModel) : TestingEvent()
    data class OnClickQuestion(val question: QuestionModel) : TestingEvent()
    data class InitTesting(val idQuiz: String)
    data class CurrentQuiz(val idQuiz: String) : TestingEvent()
}

