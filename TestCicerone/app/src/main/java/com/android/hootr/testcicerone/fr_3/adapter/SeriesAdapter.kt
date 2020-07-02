package com.android.hootr.testcicerone.fr_3.adapter

import android.view.MenuItem
import androidx.appcompat.widget.PopupMenu
import com.android.hootr.testcicerone.R
import com.android.hootr.testcicerone.fr_3.Series
import com.example.delegateadapter.delegate.KDelegateAdapter
import com.example.delegateadapter.delegate.diff.IComparableItem
import kotlinx.android.synthetic.main.item_series.*


class SeriesAdapter(
    private val onClick: (payLoad: Series) -> Unit
) : KDelegateAdapter<SeriesViewModel>(), PopupMenu.OnMenuItemClickListener {
    override fun getLayoutId() = R.layout.item_series

    override fun isForViewType(items: MutableList<*>, position: Int): Boolean =
        items[position] is SeriesViewModel

    override fun onBind(item: SeriesViewModel, viewHolder: KViewHolder) {
        viewHolder.tviewSeries.text = item.title
        viewHolder.itemView.setOnClickListener {
            onClick(item.payLoad)

            val popup = PopupMenu(it.getContext(), it)
            popup.inflate(R.menu.menu_series_list)
            popup.setOnMenuItemClickListener(this@SeriesAdapter)
            popup.show()
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {

        return true
    }


}

data class SeriesViewModel(
    val title: String,
    val payLoad: Series
) : IComparableItem {
    override fun id(): Any = this::class.java

    override fun content(): Any = this
}