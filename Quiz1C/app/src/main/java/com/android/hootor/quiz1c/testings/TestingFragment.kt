package com.android.hootor.quiz1c.testings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.hootor.quiz1c.R
import com.android.hootor.quiz1c.view.adapter.AnswerAdapter
import com.android.hootor.quiz1c.view.adapter.QuestionAdapter
import com.android.hootor.quiz1c.view.model.AnswerModel
import com.android.hootor.quiz1c.view.model.QuestionModel
import com.example.delegateadapter.delegate.diff.DiffUtilCompositeAdapter
import kotlinx.android.synthetic.main.fragment_testing.*


class TestingFragment : Fragment() {

    private lateinit var presentation: TestingPresentationModel
    private val adapterQuestion: DiffUtilCompositeAdapter by lazy { getDiffAdapterQuestions() }
    private val adaoterAnswers: DiffUtilCompositeAdapter by lazy { getDiffAdapterAnswers() }

    private var id: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString("id", "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_testing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPresentationViewModel()
        bindRecycler()

        presentation.obtainEvent(TestingEvent.CurrentQuiz(arguments?.getString("id", "") ?: ""))
    }

    private fun initPresentationViewModel() {
        presentation = ViewModelProvider(this)[TestingPresentationModel::class.java]
        presentation.viewStates().observe(viewLifecycleOwner, Observer { state ->
            when(state.status) {
                is TestingViewStatus.Loading -> {pbTesting.visibility = View.VISIBLE}
                is TestingViewStatus.Error -> {pbTesting.visibility = View.GONE}
                is TestingViewStatus.ShowNewQuestion -> {
                    pbTesting.visibility = View.GONE
                    tvQuestion.text = state.status.question.title
                    adaoterAnswers.swapData(state.status.answers)
                }
                is TestingViewStatus.Success -> {
                    pbTesting.visibility = View.GONE
                    adapterQuestion.swapData(state.status.questions)

                }
            }
        })
        presentation.viewEffects().observe(viewLifecycleOwner, Observer {

        })
    }

    private fun getDiffAdapterQuestions(): DiffUtilCompositeAdapter = DiffUtilCompositeAdapter.Builder()
        .add(QuestionAdapter(this::onClickNumberQuestion))
        .build()

    private fun getDiffAdapterAnswers() : DiffUtilCompositeAdapter = DiffUtilCompositeAdapter.Builder()
        .add(AnswerAdapter(this::onClickAnswer))
        .build()

    fun onClickAnswer(answer: AnswerModel) {
        presentation.obtainEvent(
            TestingEvent.OnClickAnswer(answer)
        )
    }

    fun onClickNumberQuestion(question: QuestionModel) {
        presentation.obtainEvent(
            TestingEvent.OnClickQuestion(question)
        )
    }

    fun bindRecycler() {
        rvQuestions.adapter = adapterQuestion
        rvQuestions.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        rvAnswers.adapter = adaoterAnswers
        rvAnswers.layoutManager = LinearLayoutManager(requireContext())
    }

}