package com.android.hootor.quiz1c.view.adapter

import android.icu.text.CaseMap
import android.widget.TextView
import com.android.hootor.quiz1c.R
import com.android.hootor.quiz1c.view.model.AnswerModel
import com.android.hootor.quiz1c.view.model.QuestionModel
import com.example.delegateadapter.delegate.KDelegateAdapter
import com.example.delegateadapter.delegate.diff.IComparableItem

class AnswerAdapter(
    private val onClick: (payload: AnswerModel) -> Unit
) : KDelegateAdapter<AnswerViewModel>()  {
    override fun getLayoutId(): Int = R.layout.item_answer

    override fun isForViewType(items: MutableList<*>, position: Int): Boolean =
        items[position] is AnswerViewModel

    override fun onBind(item: AnswerViewModel, viewHolder: KViewHolder) {
        val tv = viewHolder.containerView.findViewById<TextView>(R.id.tvTitleQuestion)
        tv.text = item.title
        viewHolder.containerView.setOnClickListener {
            onClick(item.payload)
        }
    }

}

data class AnswerViewModel(
    val title: String,
    val payload: AnswerModel
) : IComparableItem {

    override fun id(): Any = this::class.java

    override fun content(): Any = this

}