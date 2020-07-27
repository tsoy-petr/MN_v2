package com.android.hootor.quiz1c.view.adapter

import android.icu.text.CaseMap
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.android.hootor.quiz1c.R
import com.android.hootor.quiz1c.domain.QuizItem
import com.example.delegateadapter.delegate.KDelegateAdapter
import com.example.delegateadapter.delegate.diff.IComparableItem

class QuizAdapter(
    private val onClick: (payload: QuizItem.Quiz) -> Unit
) : KDelegateAdapter<QuizViewModel>() {

    override fun getLayoutId(): Int = R.layout.item_quiz

    override fun isForViewType(items: MutableList<*>, position: Int): Boolean =
        items[position] is QuizViewModel

    override fun onBind(item: QuizViewModel, viewHolder: KViewHolder) {
        viewHolder.itemView.findViewById<TextView>(R.id.tvTitleQuizList).text = item.title
        viewHolder.itemView.findViewById<CardView>(R.id.cardQuiz)
            .setOnClickListener {
                onClick(item.payload)
            }

    }

}


data class QuizViewModel(
    val title: String,
    val payload: QuizItem.Quiz
) : IComparableItem {

    override fun id(): Any = this::class.java

    override fun content(): Any = this

}

