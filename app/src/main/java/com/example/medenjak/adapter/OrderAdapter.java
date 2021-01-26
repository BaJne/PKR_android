package com.example.medenjak.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medenjak.R;
import com.example.medenjak.model.Order;
import com.example.medenjak.model.Product;
import com.example.medenjak.util.Util;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private final Context context;
    private final List<Order> contentList;
    private final ListItemClickListener mOnClickListener;


    public OrderAdapter(Context context, List<Order> contentList, ListItemClickListener listener){
        this.context = context;
        this.contentList = contentList;
        mOnClickListener = listener;
    }

    @NonNull @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = contentList.get(position);

        holder.totalItems.setText(Util.articleCountToString(order.getCart().size()));
        holder.date.setText(Util.formatDate(order.getDeliveryDate()));
        holder.price.setText(Util.formatCurrency(order.getTotalCost()));
        holder.status.setText(order.getStatus().getStatusMsg());

        holder.click.setOnClickListener(v -> {
            mOnClickListener.onListItemClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView totalItems;
        public TextView date;
        public TextView price;
        public TextView status;
        public View click;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            totalItems = itemView.findViewById(R.id.order_item_article_count);
            date = itemView.findViewById(R.id.order_item_date);
            price = itemView.findViewById(R.id.order_item_total_cost);
            status = itemView.findViewById(R.id.order_item_status);
            click = itemView.findViewById(R.id.order_item_click);
        }
    }
}
