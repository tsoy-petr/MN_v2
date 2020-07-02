package com.android.hootr.myloftcoint.screens.main.rate;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.hootr.myloftcoint.App;
import com.android.hootr.myloftcoint.R;
import com.android.hootr.myloftcoint.data.db.model.CoinEntity;
import com.android.hootr.myloftcoint.data.db.model.QuoteEntity;
import com.android.hootr.myloftcoint.data.model.Currency;
import com.android.hootr.myloftcoint.data.model.Fiat;
import com.android.hootr.myloftcoint.data.prefs.Prefs;
import com.android.hootr.myloftcoint.utils.CurrencyFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RateAdapter
        extends RecyclerView.Adapter<RateAdapter.RateViewHolder> {

    private List<CoinEntity> coins = new ArrayList<>();

    @Inject
    public Prefs prefs;

    public RateAdapter() {

        App.getComponent().injectRateAdapter(this);

    }

    public void setCoins(List<CoinEntity> newCoins) {


        CoinDiffCallback diffCallback = new CoinDiffCallback(this.coins, newCoins);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.coins.clear();
        this.coins.addAll(newCoins);
        diffResult.dispatchUpdatesTo(this);
        //notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rate, parent, false);
        return new RateViewHolder(view, prefs);
    }

    @Override
    public void onBindViewHolder(@NonNull RateViewHolder holder, int position) {
        holder.bind(coins.get(position), position);
    }

    @Override
    public int getItemCount() {
        return coins.size();
    }

    @Override
    public long getItemId(int position) {
        return coins.get(position).id;
    }

    static class RateViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.symbol_text)
        TextView symbolText;
        @BindView(R.id.symbol_icon)
        ImageView symbolIcon;
        @BindView(R.id.currency_name)
        TextView name;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.percent_change)
        TextView percentChange;


        private Random random = new Random();

        private Context context;

        private Prefs prefs;

        private CurrencyFormatter currencyFormatter = new CurrencyFormatter();

        private static int[] colors = {
                0xFFF5FF30,
                0xFFFFFFFF,
                0xFF2ABDF5,
                0xFFFF7416,
                0xFFFF7416,
                0xFF534FFF,
        };


        RateViewHolder(View itemView, Prefs prefs) {
            super(itemView);

            symbolText = itemView.findViewById(R.id.symbol_text);
            symbolIcon = itemView.findViewById(R.id.symbol_icon);
            name = itemView.findViewById(R.id.currency_name);
            price = itemView.findViewById(R.id.price);
            percentChange = itemView.findViewById(R.id.percent_change);

            context = itemView.getContext();
            this.prefs = prefs;

            ButterKnife.bind(this, itemView);
        }


        void bind(CoinEntity coin, int position) {
            bindIcon(coin);
            bindSymbol(coin);
            bindPrice(coin);
            bindPercentage(coin);
            bindBackground(position);
        }

        private void bindBackground(int position) {
            if (position % 2 == 0) {
                itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.rate_item_background_even));
            } else {
                itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.rate_item_background_odd));
            }
        }

        private void bindPercentage(CoinEntity coin) {

            QuoteEntity quote = coin.getQuote(prefs.getFiatCurrency());

            float percentChangeValue = quote.percentChange24h;


            percentChange.setText(context.getString(R.string.rate_item_percent_change, percentChangeValue));

            if (percentChangeValue >= 0) {
//                percentChange.setTextColor(context.getResources().getColor(R.color.percent_change_up));
                percentChange.setTextColor(ContextCompat.getColor(context, R.color.percent_change_up));

            } else {
//                percentChange.setTextColor(context.getResources().getColor(R.color.percent_change_down));
                percentChange.setTextColor(ContextCompat.getColor(context, R.color.percent_change_down));
            }
        }

        private void bindPrice(CoinEntity coin) {
            Fiat fiat = prefs.getFiatCurrency();
            QuoteEntity quote = coin.getQuote(fiat);
            String value = null;
            if (quote != null) {
                value = currencyFormatter.format(quote.price, false);
            }
            price.setText(context.getString(R.string.currency_amount, value, fiat.symbol));
        }

        private void bindSymbol(CoinEntity coin) {
            name.setText(coin.symbol);
        }

        private void bindIcon(CoinEntity coin) {
            Currency currency = Currency.getCurrency(coin.symbol);

            if (currency != null) {
                symbolIcon.setVisibility(View.VISIBLE);
                symbolText.setVisibility(View.GONE);

                symbolIcon.setImageResource(currency.iconRes);
            } else {
                symbolIcon.setVisibility(View.GONE);
                symbolText.setVisibility(View.VISIBLE);

                Drawable background = symbolText.getBackground();
                Drawable wrapped = DrawableCompat.wrap(background);
                DrawableCompat.setTint(wrapped, colors[random.nextInt(colors.length)]);

                symbolText.setText(String.valueOf(coin.symbol.charAt(0)));
            }
        }

    }
}