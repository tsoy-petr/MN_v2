package com.android.hootr.test.view.ordres;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.hootr.test.R;
import com.android.hootr.test.data.db.model.OrderEntity;
import com.android.hootr.test.data.prefs.Prefs;
import com.android.hootr.test.utils.Util;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder> {

    private List<OrderEntity> orders;
    private Prefs prefs;

    public OrdersAdapter(Prefs prefs) {
        this.prefs = prefs;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_oreder, parent, false);
        return new OrdersViewHolder(view, prefs);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, int position) {
        OrderEntity order = orders.get(position);
        holder.bind(order);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }



    static class OrdersViewHolder extends RecyclerView.ViewHolder{

        private Prefs prefs;

        TextView orderNamber;
        TextView orderDate;

        public void bind(OrderEntity order) {
            orderNamber.setText(order.number = order.number);
            orderDate.setText(Util.formateDate(order.date));
        }

        public OrdersViewHolder(@NonNull View itemView, Prefs prefs) {
            super(itemView);

            orderNamber = itemView.findViewById(R.id.tvNamberOrder);
            orderDate = itemView.findViewById(R.id.tvDateOrder);

            this.prefs = prefs;
        }
    }
}
