package com.android.hootr.hogwartslibrary.ui.scenes.students.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.hootr.hogwartslibrary.R
import com.android.hootr.hogwartslibrary.domains.models.StudentModel
import java.util.*

class StudentsAdapter : RecyclerView.Adapter<StudentsAdapter.StudentsViewHolder>() {


    private val mDataList = ArrayList<StudentCellModel>()
    private val mDisplayList = ArrayList<StudentCellModel>()

    fun setData(newData: List<StudentCellModel>) {
        mDataList.clear()
        mDataList.addAll(newData)

       filter("")


    }

    fun filter(query: String) {
        mDisplayList.clear()

        if (query.isEmpty()) {
            mDisplayList.addAll(mDataList)

        } else {

            mDisplayList.addAll(mDataList.filter {
                it.name.contains(query, true) ||
                        it.facultyName.contains(query, true)
            })
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return StudentsViewHolder(
            itemView = inflater.inflate(
                R.layout.cell_students,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = mDisplayList.count()


    override fun onBindViewHolder(holder: StudentsViewHolder, position: Int) {
        holder.bind(mDataList[position])
    }

    class StudentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val txtName: TextView = itemView.findViewById(R.id.txtStudentsName)
        private val txtFaculty: TextView = itemView.findViewById(R.id.txtStudentsFaculty)
        fun bind(model: StudentCellModel) {
            txtName.text = model.name
            txtFaculty.text = model.facultyName
        }
    }

}

data class StudentCellModel(
    val id: String,
    val name: String,
    val facultyName: String
)

fun StudentModel.toUI(): StudentCellModel {
    return StudentCellModel(id = this.id, name = this.name, facultyName = this.facultyName)
}