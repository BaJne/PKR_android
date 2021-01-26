package com.example.medenjak.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medenjak.R;
import com.example.medenjak.model.Article;
import com.example.medenjak.util.Util;

import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {
    private final Context context;
    private final List<Article> contentList;

    public OrderDetailAdapter(Context context, List<Article> contentList){
        this.context = context;
        this.contentList = contentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.article_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article article = contentList.get(position);

        holder.name.setText(article.getProduct().getName());
        holder.price.setText(Util.formatCurrency(article.getProduct().getPrice() * article.getItemCount()));
        holder.picture.setImageResource(article.getProduct().getPicture());
        holder.totalCount.setText(itemCountToString(article.getItemCount()));
    }

    private String itemCountToString(int itemCount) {
        StringBuilder sb = new StringBuilder();
        sb.append(itemCount);
        if(itemCount > 1)
            sb.append(" proizvod");
        else
            sb.append(" proizvoda");
        return sb.toString();
    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView picture;
        public TextView name;
        public TextView totalCount;
        public TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            picture = itemView.findViewById(R.id.article_picture);
            name = itemView.findViewById(R.id.article_name);
            totalCount = itemView.findViewById(R.id.article_total_item_count);
            price = itemView.findViewById(R.id.article_price);
        }
    }
}
