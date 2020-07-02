package com.android.hootr.cryptoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.hootr.cryptoapp.adapters.CoinInfoAdapter
import com.android.hootr.cryptoapp.pojo.CoinPriceInfo
import kotlinx.android.synthetic.main.activity_coin_price_list.*


class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)

        val adapter = CoinInfoAdapter(this)

        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                startActivity(CoinDetailActivity.newIntent(this@CoinPriceListActivity, coinPriceInfo.fromSymbol))
            }

        }

        rvCoinPriceList.adapter = adapter

        viewModel = ViewModelProvider(this).get(CoinViewModel::class.java)

        viewModel.priceList.observe(this, Observer {
            adapter.coinInfoList = it
        }
        )

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
