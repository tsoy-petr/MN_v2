package com.android.hootr.hogwartslibrary.ui.scenes.students

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.hootr.hogwartslibrary.R
import com.android.hootr.hogwartslibrary.ui.scenes.students.adapters.StudentsAdapter
import kotlinx.android.synthetic.main.fragment_studients.*

class StudientsFragment : Fragment() {

    private lateinit var studentViewModel: StudientsViewModel
    private val adapter = StudentsAdapter()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        studentViewModel =
                ViewModelProvider(this)[StudientsViewModel::class.java]
        val root = inflater.inflate(R.layout.fragment_studients, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupData()
        setupIsLoading()

        context?.let {
            recyclerStudents.adapter = adapter
            recyclerStudents.layoutManager = LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
        }

        textStudentsSearch.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                adapter.filter(query = s.toString())
            }

        })

        studentViewModel.fetchStudents()

    }

    private fun setupIsLoading() {
        studentViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            txtStudentIsLoading.visibility = if (it) View.VISIBLE else View.GONE
            recyclerStudents.visibility = if (it) View.GONE else View.VISIBLE
            textStudentsSearch.visibility = if (it) View.GONE else View.VISIBLE
        })
    }

    private fun setupData() {
        studentViewModel.students.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
    }
}
