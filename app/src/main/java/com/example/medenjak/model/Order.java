package com.example.medenjak.model;

import java.util.ArrayList;
import java.util.Calendar;

public class Order {
    public enum OrderStatus{
        NOT_PROCESSED("Nije obrađeno"),
        PROCESSED("Obrađeno"),
        APPROVED("Odobreno"),
        DENIED("Odbijeno");

        String statusMsg;
        OrderStatus(String msg){
            statusMsg = msg;
        }

        public String getStatusMsg() {
            return statusMsg;
        }
    }

    private final OrderStatus status;
    private final Calendar deliveryDate;
    private final ArrayList<Article> cart;
    private final int totalCost;

    public Order(ArrayList<Article> cart, int totalCost) {
        status = OrderStatus.NOT_PROCESSED;
        deliveryDate = null;
        this.cart = cart;
        this.totalCost = totalCost;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Calendar getDeliveryDate() {
        return deliveryDate;
    }

    public ArrayList<Article> getCart() {
        return cart;
    }

    public int getTotalCost() {
        return totalCost;
    }


}
