package com.android.hootr.hogwartslibrary.ui.scenes.house

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.android.hootr.hogwartslibrary.R
import com.android.hootr.hogwartslibrary.helpers.Keys
import kotlinx.android.synthetic.main.house_detail_fragment.*

class HouseDetailFragment : Fragment() {

    companion object {
        fun newInstance() = HouseDetailFragment()
    }

    private lateinit var viewModel: HouseDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.house_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[HouseDetailViewModel::class.java]



        configureLayout()

        viewModel.fetchData(hous = arguments?.get(Keys.Faculty.value) as? Houses)
    }

    private fun configureLayout() {

        viewModel.founder.observe(viewLifecycleOwner, Observer {
            txtHouseOwner.text = getString(R.string.house_detail_owner).replace("[FOUNDER_NAME]", it)
        })

        viewModel.ghost.observe(viewLifecycleOwner, Observer {
            txtHouseGhost.text = getString(R.string.house_detail_ghost).replace("[GHOST_NAME]", it)
        })

        viewModel.leader.observe(viewLifecycleOwner, Observer {
            txtHouseLeader.text = getString(R.string.house_detail_leader).replace("[LEADER_NAME]", it)
        })

        viewModel.housesName.observe(viewLifecycleOwner, Observer {
            txtHouseName.text = it
        })

        viewModel.housesImage.observe(viewLifecycleOwner, Observer {
            imgHouseDetail.setImageResource(it)
        })
    }

}
