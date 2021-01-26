package com.example.medenjak.data;

import com.example.medenjak.model.Article;
import com.example.medenjak.model.Order;
import com.example.medenjak.model.Product;
import com.example.medenjak.model.User;

import java.util.ArrayList;

public class Repository {

    public static final int REGISTRATION_OK = 1;
    public static final int USERNAME_IN_USE = 2;

    // MessageService
    public static void sendNotification(String msg){
        MessageService.getInstance().putMessage(msg);
    }
    public static String getNotification(){
        return MessageService.getInstance().getNotification();
    }

    // User
    public static User getActiveUser(){
        return AuthenticationService.getInstance().getActiveUser();
    }
    public static boolean login(String username, String password){
        return AuthenticationService.getInstance().login(username, password);
    }
    public static void logout(){
        AuthenticationService.getInstance().logout();
    }
    public static int register(User user){
        return AuthenticationService.getInstance().register(user);
    }
    public static void updateUserData(User user){
        AuthenticationService.getInstance().updateUserDetails(user);
    }
    public static boolean updatePassword(String oldPassword, String password){
        return AuthenticationService.getInstance().updateUserPassword(oldPassword, password);
    }

    // Products
    public static ArrayList<Product> getProducts(){
        return ProductService.getInstance().getProducts();
    }
    public static void checkProduct(int position){
        ProductService.getInstance().checkProductDetails(position);
    }
    public static Product getProductDetail(){
        return ProductService.getInstance().getProduct();
    }

    // Cart
    public static void addToCart(Article article){
        CartService.getInstance().addToCart(article);
    }
    public static ArrayList<Article> getCart(){
        return CartService.getInstance().getCart();
    }
    public static int getCartTotalCost(){
        return CartService.getInstance().getTotalCost();
    }
    public static boolean decrementItemCount(int position){
        return CartService.getInstance().decrementItemCount(position);
    }
    public static void incrementItemCount(int position){
        CartService.getInstance().incrementItemCount(position);
    }
    public static void removeFromCart(int position){
        CartService.getInstance().removeArticle(position);
    }
    public static boolean isCartEmpty() {
        return CartService.getInstance().isCartEmpty();
    }

    // Orders
    public static void addNewOrder(){
        OrderService.getInstance().addNewOrder();
    }
    public static ArrayList<Order> getOrders(){
        return OrderService.getInstance().getOrders();
    }

    public static void checkOrder(int position) {
        OrderService.getInstance().checkOrder(position);
    }
    public static Order getCheckedOrder() {
        return OrderService.getInstance().getCheckedOrder();
    }
}
