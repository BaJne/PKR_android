package com.example.medenjak.data;

import com.example.medenjak.model.Article;

import java.util.ArrayList;

public class CartService {
    private static CartService instance;
    public static CartService getInstance(){
        if(instance == null){
            instance = new CartService();
        }
        return instance;
    }

    private ArrayList<Article> cart;
    private int totalCost;

    private CartService(){
        resetCart();
    }

    public void resetCart(){
        cart = new ArrayList<>();
        totalCost = 0;
    }

    public boolean addToCart(Article article){
        totalCost += article.getItemCount() * article.getProduct().getPrice();

        for(int i = 0; i < cart.size(); ++i){
            if(cart.get(i).equals(article)){
                cart.get(i).addItemCount(article.getItemCount());
                return true;
            }
        }
        cart.add(article);
        return true;
    }

    public void removeArticle(int position){
        int price = cart.get(position).getProduct().getPrice();
        int count = cart.get(position).getItemCount();
        totalCost -= count * price;
        cart.remove(position);
    }

    public void updateItemCount(Article article, int newItemCount){
        int diff = newItemCount - article.getItemCount();
        totalCost += diff * article.getProduct().getPrice();
        article.setItemCount(newItemCount);
    }

    public ArrayList<Article> getCart(){
        return cart;
    }
    public int getTotalCost(){
        return totalCost;
    }

    public boolean isCartEmpty() {
        return cart.isEmpty();
    }

    public void incrementItemCount(int position){
        changeItemCount(cart.get(position), 1);
    }

    public boolean decrementItemCount(int position) {
        int count = cart.get(position).getItemCount();
        if(count > 1){
            changeItemCount(cart.get(position), -1);
            return true;
        }
        return false;
    }

    private void changeItemCount(Article item, int diff){
        item.addItemCount(diff);
        totalCost += diff * item.getProduct().getPrice();
    }
}
