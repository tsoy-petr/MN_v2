package com.android.hootor.mn_v2.ui.synchronization

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.hootor.mn_v2.App
import com.android.hootor.mn_v2.R
import com.android.hootor.mn_v2.domain.sync.model.PackageItemInfo
import com.android.hootor.mn_v2.remote.SyncRemoteImpl
import com.android.hootor.mn_v2.utils.injectViewModel
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_synchronization.*
import javax.inject.Inject

class SynchronizationFragment : Fragment(R.layout.fragment_synchronization) {

    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SyncViewPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = injectViewModel(factory = viewModelFactory)
        viewModel.viewStates().observe(requireActivity(), Observer {status ->
            when(status) {
                is SyncFetchStatus.Loading -> tvSyncStatus.text = status.message
                is SyncFetchStatus.ShowError -> tvSyncStatus.text = status.message
                is SyncFetchStatus.Success -> tvSyncStatus.text = "Синхронизация выполнена"
            }
        })
        btnTest1.setOnClickListener {
            viewModel.obtainEvent(SyncEvent.GetData)
        }

        btnSelectAllGoods.setOnClickListener {
            viewModel.selectAllGoods()
        }

    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }


    companion object {
        @JvmStatic
        fun newInstance(args: Bundle? = null) =
            SynchronizationFragment()
                .apply {
                    arguments = args
                }
    }
}