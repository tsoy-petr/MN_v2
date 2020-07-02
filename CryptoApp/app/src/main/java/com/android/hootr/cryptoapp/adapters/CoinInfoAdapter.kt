package com.android.hootr.cryptoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.hootr.cryptoapp.R
import com.android.hootr.cryptoapp.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coin_info.view.*

class CoinInfoAdapter(private val context: Context) :
    RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var onCoinClickListener: OnCoinClickListener? = null

    var coinInfoList: List<CoinPriceInfo> = arrayListOf<CoinPriceInfo>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class CoinInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivLogoCoin = itemView.ivLogoCoin
        val tvSymbols = itemView.tvSymbols
        val tvPrice = itemView.tvPrice
        val tvLastUpdate = itemView.tvLastUpdate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info, parent, false)
        return CoinInfoViewHolder(view)
    }

    override fun getItemCount() = coinInfoList.size

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]

        with(holder) {
            val symbolsTempalte = context.resources.getString(R.string.symbols_template)
            val lastUpdateTemolate = context.resources.getString(R.string.lable)
            tvSymbols.text = String.format(symbolsTempalte, coin.fromSymbol, coin.toSymbol)
            tvPrice.text = coin.price
            tvLastUpdate.text = String.format(lastUpdateTemolate, coin.getFormattedTime())
            Picasso.get().load(coin.getFullImageUrl()).into(ivLogoCoin)

            itemView.setOnClickListener {
                onCoinClickListener?.onCoinClick(coin)
            }
        }

    }

    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo: CoinPriceInfo)
    }
}