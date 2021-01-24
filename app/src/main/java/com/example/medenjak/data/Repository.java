package com.example.medenjak.data;

import com.example.medenjak.model.Product;
import com.example.medenjak.model.User;

import java.util.ArrayList;

public class Repository {

    public static final int REGISTRATION_OK = 1;
    public static final int USERNAME_IN_USE = 2;

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

    public static ArrayList<Product> getProducts(){
        return ProductService.getInstance().getProducts();
    }
    public static void checkProduct(int position){
        ProductService.getInstance().checkProductDetails(position);
    }
    public static Product getProductDetail(){
        return ProductService.getInstance().getProduct();
    }
}
