package com.android.hootor.quiz1c.view.adapter

import android.widget.TextView
import com.android.hootor.quiz1c.R
import com.android.hootor.quiz1c.view.model.QuestionModel
import com.example.delegateadapter.delegate.KDelegateAdapter
import com.example.delegateadapter.delegate.diff.IComparableItem

class QuestionAdapter(
    private val onClick: (payload: QuestionModel) -> Unit
) : KDelegateAdapter<QuestionViewModel>() {
    override fun getLayoutId(): Int = R.layout.item_number_question

    override fun isForViewType(items: MutableList<*>, position: Int): Boolean =
        items[position] is QuestionViewModel

    override fun onBind(item: QuestionViewModel, viewHolder: KViewHolder) {
        val tv = viewHolder.containerView.findViewById<TextView>(R.id.tvTitleNumberQuestion)
        tv.text = item.title
        tv.setOnClickListener {
            onClick(item.payload)
        }
        if (viewHolder.adapterPosition == 0) {
            onClick(item.payload)
        }
    }

}

data class QuestionViewModel(
    val title: String,
    val payload: QuestionModel
) : IComparableItem {
    override fun id(): Any = this::class.java

    override fun content(): Any = this
}