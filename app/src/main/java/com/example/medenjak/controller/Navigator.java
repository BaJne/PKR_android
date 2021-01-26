package com.example.medenjak.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public interface Navigator {
    void show(Fragment fragment);
    void startActivity(Class activity);
    void selectBottomNavigationMenu(int id);
}
