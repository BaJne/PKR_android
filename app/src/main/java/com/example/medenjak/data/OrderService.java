package com.example.medenjak.data;

import com.example.medenjak.model.Article;
import com.example.medenjak.model.Order;

import java.util.ArrayList;

public class OrderService {
    private final ArrayList<Order> orders;

    private static OrderService instance;
    public static OrderService getInstance(){
        if(instance == null)
            instance = new OrderService();
        return instance;
    }

    private OrderService(){
        orders = new ArrayList<>();
    }
    private Order details = null;

    public void addNewOrder(){
        ArrayList<Article> cart = CartService.getInstance().getCart();
        if(cart.isEmpty())
            return;

        Order order = new Order(cart, CartService.getInstance().getTotalCost());
        orders.add(order);
        CartService.getInstance().resetCart();
    }
    public ArrayList<Order> getOrders(){
        return orders;
    }

    public void checkOrder(int position) {
        details = orders.get(position);
    }

    public Order getCheckedOrder(){
        return details;
    }
}
