package com.example.medenjak.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.medenjak.R;
import com.example.medenjak.controller.fragments.CartFragment;
import com.example.medenjak.controller.fragments.OrderFragment;
import com.example.medenjak.controller.fragments.ProductsFragment;
import com.example.medenjak.data.Repository;
import com.example.medenjak.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements Navigator{

    public static final String TAG = HomeActivity.class.getSimpleName();
    User user = null;

    private final BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        private Integer oldId = 0;
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();

            if(oldId == id){
                return false;
            } else {
                oldId = id;
            }

            Log.d(TAG, "onNavigationItemSelected: itemId: " + id);
            Fragment fragment = null;

            switch (id){
                case R.id.products:
                    setTitle(R.string.products);
                    fragment = new ProductsFragment();
                    break;
                case R.id.cart:
                    setTitle(R.string.cart);
                    fragment = new CartFragment();
                    break;
                case R.id.orders:
                    setTitle(R.string.orders);
                    fragment = new OrderFragment();
                    break;
            }

            show(fragment);
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        checkIsUserAuthenticated();
        BottomNavigationView bottomNavigation = findViewById(R.id.nav);
        bottomNavigation.setOnNavigationItemSelectedListener(bottomNavigationListener);

        bottomNavigation.setSelectedItemId(R.id.products);
    }

    private void checkIsUserAuthenticated() {
        user = Repository.getActiveUser();
        if(user == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void show(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        if(fragment.isAdded()){
            Log.d(TAG, "show: Fragment is added!");
            transaction.show(fragment).commit();
            fragment.onResume();
        } else {
            Log.d(TAG, "show: Fragment is being replaced!");
            transaction.replace(R.id.container, fragment).commit();
        }
    }

    @Override
    public void startActivity(Class activity) {
        Intent intent = new Intent(getBaseContext(), activity);
        startActivity(intent);
    }


}