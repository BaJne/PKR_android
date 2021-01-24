package com.example.medenjak.data;

import android.util.Log;

import com.example.medenjak.model.User;

import java.util.ArrayList;

class AuthenticationService {

    public static final int REGISTRATION_OK = 1;
    public static final int USERNAME_IN_USE = 2;

    private static AuthenticationService instance;
    public static AuthenticationService getInstance(){
        if(instance == null){
            instance = new AuthenticationService();
        }
        return instance;
    }

    private final ArrayList<User> users;
    private User activeUser = null;

    private AuthenticationService() {
        this.users = new ArrayList<>();
        init();
    }

    private void init() {
        users.add(new User("branislav.jevtic@gmail.com", "123", "Branislav", "Jevtic", "Volgina 16a", "064821578"));
    }

    public boolean login(String username, String password){
        return users.stream().anyMatch(user -> {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                activeUser = user;
                return true;
            }
            return false;
        });
    }

    public void logout(){
        activeUser = null;
    }

    public int register(User user){
        final String username = user.getUsername().toLowerCase();

        for (int i = 0; i < users.size(); i++) {
            if(username.equals(users.get(i).getUsername().toLowerCase())){
                return USERNAME_IN_USE;
            }
        }
        return REGISTRATION_OK;
    }

    public User getActiveUser(){
        return activeUser;
    }
}
