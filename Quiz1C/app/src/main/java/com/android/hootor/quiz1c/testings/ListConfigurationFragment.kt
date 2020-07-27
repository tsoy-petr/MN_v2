package com.android.hootor.quiz1c.testings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.hootor.quiz1c.R
import com.android.hootor.quiz1c.domain.QuizItem
import com.android.hootor.quiz1c.view.adapter.QuizAdapter
import com.example.delegateadapter.delegate.diff.DiffUtilCompositeAdapter
import kotlinx.android.synthetic.main.fragment_list_configuration.*
import kotlinx.android.synthetic.main.tool_bar.*

class ListConfigurationFragment : Fragment() {

    private lateinit var presentation: ListTestingsPresentationModel
    private val adapter: DiffUtilCompositeAdapter by lazy { getDiffAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_configuration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPresentationViewModel()
        bindRecycler()
    }

    fun bindRecycler() {
        rvListTestings.adapter = adapter
        rvListTestings.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun getDiffAdapter(): DiffUtilCompositeAdapter = DiffUtilCompositeAdapter.Builder()
        .add(QuizAdapter(this::onClickItem))
        .build()

    private fun initPresentationViewModel() {
        presentation = ViewModelProvider(this)[ListTestingsPresentationModel::class.java]
        presentation.viewStates().observe(viewLifecycleOwner, Observer {
            adapter.swapData(it.items)
        })
        presentation.viewEffects().observe(viewLifecycleOwner, Observer { action ->

            when (action) {
                is QuizListAction.OpenTestingFragment -> {
                    findNavController().navigate(R.id.action_listConfigurationFragment_to_testingFragment,
                        Bundle().apply
                        {
                            putString("id", action.itemQuiz.id)
                        })
                }
            }

        })
    }

    fun onClickItem(quiz: QuizItem.Quiz) {
        presentation.obtainEvent(QuizListEvent.OnClickItemList(quiz))
    }

}