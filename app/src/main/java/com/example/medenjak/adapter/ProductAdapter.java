package com.example.medenjak.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medenjak.R;
import com.example.medenjak.model.Product;

import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>  {

    private final Context context;
    private final List<Product> contentList;
    private final ListItemClickListener mOnClickListener;


    public ProductAdapter(Context context, List<Product> contentList, ListItemClickListener listener){
        this.context = context;
        this.contentList = contentList;
        mOnClickListener = listener;
    }

    @NonNull @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Here, instead of context could be put viewGroup - parent.getContext()
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // When Layout Manager would ask adapter to update given holder onBindViewHolder would be called
        Product product = contentList.get(position);

        holder.name.setText(product.getName());
        holder.price.setText(String.format(Locale.US, "%d din.", product.getPrice()));
        holder.picture.setImageResource(product.getPicture());
        holder.clickView.setOnClickListener(v->{
            mOnClickListener.onListItemClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView picture;
        public TextView name;
        public TextView price;
        public View clickView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            picture = itemView.findViewById(R.id.product_picture);
            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            clickView = itemView.findViewById(R.id.click);
        }
    }
}
