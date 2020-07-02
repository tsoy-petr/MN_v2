package com.android.hootr.hogwartslibrary.ui.scenes.houses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.android.hootr.hogwartslibrary.R
import com.android.hootr.hogwartslibrary.helpers.Keys
import com.android.hootr.hogwartslibrary.ui.scenes.house.Houses
import kotlinx.android.synthetic.main.fragment_houses.*

class HousesFragment : Fragment() {

    private lateinit var housesViewModel: HousesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_houses, container, false)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        housesViewModel =
            ViewModelProvider(this)[HousesViewModel::class.java]


        imgGryffindor.setOnClickListener { showDetail(it) }
        imgHufflepuff.setOnClickListener { showDetail(it) }
        imgRavenclaw.setOnClickListener { showDetail(it) }
        imgSlytherin.setOnClickListener { showDetail(it) }
    }

    private fun showDetail(sender: View) {

        val house = when (sender.tag) {
            "0" -> Houses.Gryffindor
            "0" -> Houses.Hufflepuff
            "0" -> Houses.Ravenclaw
            else -> Houses.Slytherin
        }

        sender.findNavController().navigate(R.id.action_navigation_dashboard_to_houseDetailFragment,
            Bundle().apply
            { this.putSerializable(Keys.Faculty.value, house) })
    }
}
