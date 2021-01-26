package com.example.medenjak.adapter;

public interface ArticleClickListener {
    void incrementCount(int position);
    boolean decrementCount(int position);
    void removeItem(int position);
}
