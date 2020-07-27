package com.android.hootor.quiz1c.sync

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.hootor.quiz1c.R
import com.android.hootor.quiz1c.domain.QuizItem

import kotlinx.android.synthetic.main.item_quiz.*
class SyncViewAdapter : RecyclerView.Adapter<SyncViewAdapter.SyncViewHolder>() {


    inner class SyncViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    val diffUtilCallback = object : DiffUtil.ItemCallback<QuizItem.Quiz>() {
        override fun areItemsTheSame(oldItem: QuizItem.Quiz, newItem: QuizItem.Quiz): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: QuizItem.Quiz, newItem: QuizItem.Quiz): Boolean =
            oldItem.hashCode() == newItem.hashCode()
    }

    val differ = AsyncListDiffer(this, diffUtilCallback)

    fun submitList(list: List<QuizItem.Quiz>) = differ.submitList(list)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SyncViewHolder {

        return SyncViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_quiz, parent, false
            )
        )

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: SyncViewHolder, position: Int) {
        val quizItem: QuizItem.Quiz = differ.currentList[position]
        
       val title = holder.itemView.findViewById<TextView>(R.id.tvTitleQuizList)
        title.text = quizItem.title
    }
}