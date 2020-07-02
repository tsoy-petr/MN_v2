package com.android.hootr.hogwartslibrary.ui.scenes.spells.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.hootr.hogwartslibrary.R
import java.util.*

class SpellAdapter : RecyclerView.Adapter<SpellAdapter.SpellsViewHolder>() {

    private val mDataList = ArrayList<SpellCellModel>()

    fun setData(newData: List<SpellCellModel>) {
        mDataList.clear()
        mDataList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellsViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return SpellsViewHolder(
            itemView = inflater.inflate(
                R.layout.cell_spell,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = mDataList.count()


    override fun onBindViewHolder(holder: SpellsViewHolder, position: Int) {
        holder.bind(mDataList[position])
    }

    class SpellsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val txtName: TextView = itemView.findViewById(R.id.txtSpellName)
        private val txtType: TextView = itemView.findViewById(R.id.txtSpellType)
        fun bind(model: SpellCellModel) {
            txtName.text = model.name
            txtType.text = model.type
        }
    }
}

data class SpellCellModel(val name: String, val type: String)