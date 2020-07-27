package com.android.hootor.quiz1c.sync

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.hootor.quiz1c.R
import com.android.hootor.quiz1c.remote.ServiceFactory
import com.android.hootor.quiz1c.remote.core.NetworkHandler
import com.android.hootor.quiz1c.remote.core.Request
import kotlinx.android.synthetic.main.fragment_sync.*

class SyncFragment : Fragment(R.layout.fragment_sync) {

    private lateinit var syncAdapter: SyncViewAdapter
    private lateinit var viewPresenter: SyncPresentationModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        val factory = SyncViewModelFactory(
            SyncInteractor(
                ServiceFactory.makeService(true), Request(
                    NetworkHandler(requireContext())
                )
            )
        )
        viewPresenter = ViewModelProvider(this, factory)[SyncPresentationModel::class.java]
        viewPresenter.viewStates().observe(viewLifecycleOwner, Observer {

            when(it){
                is SyncViewState.Success -> {
                    Log.d("happy", it.items.size.toString())
                    syncAdapter.submitList(it.items)
                }
            }
        })
        viewPresenter.obtainEvent(
            SyncEvent.StartSync
        )



    }

    private fun setupRecyclerView() = rvSunc.apply {
        syncAdapter = SyncViewAdapter()
        adapter = syncAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

}