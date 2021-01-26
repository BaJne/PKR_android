package com.example.medenjak.model;

import androidx.annotation.Nullable;

public class Article {
    private Product product;
    private int itemCount;

    public Article(Product product, int itemCount) {
        this.product = product;
        this.itemCount = itemCount;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj == this)
            return true;

        if(!(obj instanceof  Article)){
            return false;
        }

        Article a = (Article)obj;
        return a.product.getId() == this.product.getId();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public void addItemCount(int addition){
        this.itemCount += addition;
    }
}
