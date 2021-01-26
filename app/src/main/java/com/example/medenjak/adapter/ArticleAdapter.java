package com.example.medenjak.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
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
import java.util.Locale;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    public static final String TAG = ArticleAdapter.class.getSimpleName();

    private final Context context;
    private final List<Article> contentList;
    private final ArticleClickListener mArticleClickListener;

    // Selected item index
    private ViewHolder selectedViewHolder = null;
    private int selectedIndex = -1;

    public ArticleAdapter(Context context, List<Article> contentList, ArticleClickListener listener){
        this.context = context;
        this.contentList = contentList;
        mArticleClickListener = listener;
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

        if(position != selectedIndex){
            resetSelectedDecoration(holder);
        } else {
            updateSelectedItem(holder, position);
        }

        holder.name.setText(article.getProduct().getName());
        holder.price.setText(Util.formatCurrency(article.getProduct().getPrice() * article.getItemCount()));

        holder.picture.setImageResource(article.getProduct().getPicture());
        holder.totalCount.setText(itemCountToString(article.getItemCount()));
        holder.editTotalCount.setText(String.valueOf(article.getItemCount()));

        holder.delete.setOnClickListener(v -> {
            mArticleClickListener.removeItem(position);
            selectedIndex = -1;
            selectedViewHolder = null;
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, contentList.size());
        });

        holder.minus.setOnClickListener(v -> {
            if(mArticleClickListener.decrementCount(position))
                notifyItemChanged(position);
        });

        holder.plus.setOnClickListener(v -> {
            mArticleClickListener.incrementCount(position);
            notifyItemChanged(position);
        });

        holder.clickView.setOnClickListener(v -> {
            Log.d(TAG, "onBindViewHolder: clicked top view");
            if(position != selectedIndex){
                updateSelectedItem(holder, position);
            }
        });
    }

    private void resetSelectedDecoration(ViewHolder holder) {
        holder.clickView.setBackgroundResource(R.color.white);
        holder.name.setTextColor(context.getColor(R.color.dark_grey));
        holder.totalCount.setTypeface(Util.getFontFamily(context, Util.FontType.THIN_FONT));
        holder.price.setTextColor(context.getColor(R.color.dark_grey));
        holder.layout.setVisibility(View.GONE);
    }

    private void updateSelectedItem(ViewHolder holder, int position) {

        holder.clickView.setBackgroundResource(R.color.link_blue);
        holder.name.setTextColor(context.getColor(R.color.white));
        holder.totalCount.setTypeface(Util.getFontFamily(context, Util.FontType.REGULAR_FONT));
        holder.price.setTextColor(context.getColor(R.color.primary));
        holder.layout.setVisibility(View.VISIBLE);


        if(selectedIndex != position && selectedViewHolder != null){
            resetSelectedDecoration(selectedViewHolder);
        }
        selectedIndex = position;
        selectedViewHolder = holder;
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
        public TextView editTotalCount;
        public ImageButton minus;
        public ImageButton plus;
        public ImageButton delete;
        public LinearLayout layout;

        public View clickView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            picture = itemView.findViewById(R.id.article_picture);
            name = itemView.findViewById(R.id.article_name);
            totalCount = itemView.findViewById(R.id.article_total_item_count);
            price = itemView.findViewById(R.id.article_price);
            layout = itemView.findViewById(R.id.additional_control_layout);
            editTotalCount = itemView.findViewById(R.id.edit_article_item_count);
            minus = itemView.findViewById(R.id.minus);
            plus = itemView.findViewById(R.id.plus);
            delete = itemView.findViewById(R.id.remove_article);

            clickView = itemView.findViewById(R.id.awd);
        }
    }
}