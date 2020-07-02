package com.android.hootor.mn_v2.ui.orders

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.hootor.mn_v2.App
import com.android.hootor.mn_v2.R
import com.android.hootor.mn_v2.domain.usecases.ordersList.model.OrderListModel
import com.android.hootor.mn_v2.presentation.viewModel.*
import com.android.hootor.mn_v2.ui.NavScreens
import com.android.hootor.mn_v2.ui.common.navigation.BackButtonListener
import com.android.hootor.mn_v2.ui.common.navigation.RouterProvider
import com.android.hootor.mn_v2.ui.orders.OrderActivity.Companion.KEY_UUID_ORDER
import com.android.hootor.mn_v2.utils.injectViewModel
import com.example.delegateadapter.delegate.diff.DiffUtilCompositeAdapter
import kotlinx.android.synthetic.main.orders_list_fragment.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class OrdersListFragment : Fragment(){

    private lateinit var viewModel: OrdersListPresenterModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val adapter: DiffUtilCompositeAdapter by lazy { getDiffAdapter() }

    private fun getDiffAdapter(): DiffUtilCompositeAdapter = DiffUtilCompositeAdapter.Builder()
        .add(OrdersListAdapter(this::onclickItemOrder))
        .build()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.orders_list_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(requireContext())

        viewModel = injectViewModel(factory = viewModelFactory)
        viewModel.viewStates().observe(requireActivity(), Observer { bindViewState(it) })
        viewModel.viewEffects().observe(requireActivity(), Observer { bindViewAction(it) })

//        viewModel.obtainEvent(OrdersListEvent.ShowData)
    }

    private fun bindViewAction(viewAction: OrdersListAction) {
        when (viewAction) {
            is OrdersListAction.ShowSnackbar -> Toast.makeText(
                context, viewAction.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun bindViewState(viewState: OrdersListViewState) {
        when (viewState.fetchStatus) {
            FetchStatus.Loading -> print("Loading")
            FetchStatus.Success -> update(viewState)
        }
    }

    private fun update(model: OrdersListViewState) {
        adapter.swapData(model.data)
    }

    fun onclickItemOrder(order: OrderListModel) {
//        val intent = Intent(activity, OrderActivity::class.java).apply {
//            putExtra(KEY_UUID_ORDER, order.uuid)
//        }
//        activity?.startActivity(intent)
        activity?.let {
            (it as RouterProvider).router.navigateTo(NavScreens.OrderRootScreen(order))
        }
    }

    companion object {
        fun newInstance(args: Bundle?) = OrdersListFragment().apply { arguments = args }
    }

}