package hootor.com.loftcoint19.screens.main.rate;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import hootor.com.loftcoint19.R;
import hootor.com.loftcoint19.data.db.model.CoinEntity;
import hootor.com.loftcoint19.data.db.model.QuoteEntity;
import hootor.com.loftcoint19.data.model.Currency;
import hootor.com.loftcoint19.data.model.Fiat;
import hootor.com.loftcoint19.data.prefs.Prefs;
import hootor.com.loftcoint19.utils.CurrencyFormatter;

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.RateViewHolder> {

    private List<CoinEntity> coins = Collections.emptyList();

    private Listener listener = null;

    private Prefs prefs;

    public RateAdapter(Prefs prefs) {
        this.prefs = prefs;
    }

    public void setCoins(List<CoinEntity> coins) {
        this.coins = coins;
        notifyDataSetChanged();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rate, viewGroup, false);
        return new RateViewHolder(view, prefs);
    }

    @Override
    public void onBindViewHolder(@NonNull RateViewHolder holder, int position) {
        holder.bind(coins.get(position), position, listener);
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

        Context context;

        private Random random = new Random();

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

        public RateViewHolder(@NonNull View itemView, Prefs prefs) {
            super(itemView);

            context = itemView.getContext();
            this.prefs = prefs;

            ButterKnife.bind(this, itemView);
        }

        public void bind(CoinEntity coin, int position, Listener listener) {
            bindIcon(coin);
            bindSymbol(coin);
            bindPrice(coin);
            bindPercentage(coin);
            bindBackground(position);
            bindListener(coin, listener);
        }

        private void bindListener(CoinEntity coin, Listener listener) {
            itemView.setOnLongClickListener(v -> {
                if (listener != null) {
                    listener.onRateLongClick(coin.symbol);
                }
                return true;
            });
        }

        private void bindBackground(int position) {
            if (position % 2 == 0) {
                itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.rate_item_background_even));
            } else {
                itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.rate_item_background_odd));
            }
        }

        private void bindPercentage(CoinEntity coin) {
            QuoteEntity quote = coin.getQuot(prefs.getFiatCurrency());

            float percentChangeValue = quote.percentChange24h;

            percentChange.setText(context.getString(R.string.rate_item_percent_change, percentChangeValue));

            if (percentChangeValue >= 0) {
                percentChange.setTextColor(context.getResources().getColor(R.color.percent_change_up));
            } else {
                percentChange.setTextColor(context.getResources().getColor(R.color.percent_change_down));
            }
        }

        private void bindPrice(CoinEntity coin) {
            Fiat fiat = prefs.getFiatCurrency();
            QuoteEntity quote = coin.getQuot(fiat);
            String value = currencyFormatter.format(quote.price, false);

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

    interface Listener {
        void onRateLongClick(String symbol);
    }
}
