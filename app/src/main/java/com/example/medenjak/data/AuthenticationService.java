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
    private int activeIndex = -1;

    private AuthenticationService() {
        this.users = new ArrayList<>();
        init();
    }

    private void init() {
        users.add(new User("branislav.jevtic@gmail.com", "123", "Branislav", "Jevtic", "Volgina 16a", "064821578"));
    }

    public boolean login(String username, String password){
        for(int i = 0; i < users.size(); ++i){
            User user = users.get(i);
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                activeUser = user;
                activeIndex = i;
                return true;
            }
        }
        return false;
    }

    public void logout(){
        activeIndex = -1;
        activeUser = null;
    }

    public int register(User user){
        final String username = user.getUsername().toLowerCase();

        for (int i = 0; i < users.size(); i++) {
            if(username.equals(users.get(i).getUsername().toLowerCase())){
                return USERNAME_IN_USE;
            }
        }
        users.add(user);
        return REGISTRATION_OK;
    }

    public void updateUserDetails(User user){
        if(activeUser == null)
            return;
        user.setPassword(activeUser.getPassword());
        activeUser = user;
        users.set(activeIndex, user);
    }

    public boolean updateUserPassword(String oldPassword, String password){
        if(activeUser == null)
            return false;

        if(!oldPassword.equals(activeUser.getPassword()))
            return false;

        activeUser.setPassword(password);
        return true;
    }

    public User getActiveUser(){
        return activeUser;
    }
}
