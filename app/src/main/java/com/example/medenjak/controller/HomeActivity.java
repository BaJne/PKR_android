package com.example.medenjak.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.medenjak.R;
import com.example.medenjak.controller.fragments.CartFragment;
import com.example.medenjak.controller.fragments.OrderFragment;
import com.example.medenjak.controller.fragments.ProductsFragment;
import com.example.medenjak.data.Repository;
import com.example.medenjak.model.User;
import com.example.medenjak.util.Util;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements Navigator{

    public static final String TAG = HomeActivity.class.getSimpleName();
    private User user = null;
    private BottomNavigationView bottomNavigation;

    private final BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        private Integer oldId = 0;
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();

            if(oldId == id){
                return false;
            }

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
                default: return false;
            }

            oldId = id;
            show(fragment);
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Log.d(TAG, "onCreate: ");
        checkIsUserAuthenticated();
        bottomNavigation = findViewById(R.id.nav);
        bottomNavigation.setOnNavigationItemSelectedListener(bottomNavigationListener);

        bottomNavigation.setSelectedItemId(R.id.products);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Log.d(TAG, "onOptionsItemSelected: " + id);

        switch (id){
            case R.id.user_data:
                startActivity(UserDataActivity.class);
                break;
            case R.id.password_data:
                startActivity(PasswordChangeActivity.class);
                break;
            case R.id.logout_option:
                Repository.logout();
                checkIsUserAuthenticated();
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        String msg = Repository.getNotification();
        if(msg != null){
            Util.makeSnackBar(findViewById(R.id.container), msg, false);
        }
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
    public void selectBottomNavigationMenu(int id) {
        bottomNavigation.setSelectedItemId(id);
    }

    @Override
    public void show(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, 0);

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