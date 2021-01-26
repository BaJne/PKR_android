package com.example.medenjak.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.medenjak.R;
import com.example.medenjak.data.Repository;
import com.example.medenjak.model.User;
import com.example.medenjak.util.Util;
import com.google.android.material.textfield.TextInputEditText;

public class UserDataActivity extends AppCompatActivity {

    private User user;
    private TextInputEditText username;
    private TextInputEditText name;
    private TextInputEditText surname;
    private TextInputEditText address;
    private TextInputEditText phone;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        setTitle(R.string.user_data);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        user = Repository.getActiveUser();

        username = findViewById(R.id.user_data_username);
        name = findViewById(R.id.user_data_name);
        surname = findViewById(R.id.user_data_surname);
        address = findViewById(R.id.user_data_address);
        phone = findViewById(R.id.user_data_phone);

        findViewById(R.id.change_user_date_btn).setOnClickListener(v->{
            if(updateUserData()){
                Repository.sendNotification("Uspešno ste promenili korisničke podatke.");
                onBackPressed();
                finish();
            }
        });

        setUserDetails();
    }

    private void setUserDetails() {
        username.setText(user.getUsername());
        name.setText(user.getName());
        surname.setText(user.getSurname());
        address.setText(user.getAddress());
        phone.setText(user.getPhone());
    }

    private boolean updateUserData() {
        if(isInputTextEmpty()){
            return false;
        }
        if(!hasUserDataChanged()){
            Util.makeSnackBar(username, "Morate promeniti korisničke podatke", true);
            return false;
        }

        User mUser = new User(
            username.getText().toString(),
            user.getPassword(),
            name.getText().toString(),
            surname.getText().toString(),
            address.getText().toString(),
            phone.getText().toString()
        );
        Repository.updateUserData(mUser);
        return true;
    }

    private boolean hasUserDataChanged() {
        return
            !username.getText().toString().equals(user.getUsername()) ||
            !name.getText().toString().equals(user.getName())         ||
            !surname.getText().toString().equals(user.getSurname())   ||
            !address.getText().toString().equals(user.getAddress())   ||
            !phone.getText().toString().equals(user.getPhone());
    }

    private boolean isInputTextEmpty() {
        if(username.getText().toString().equals("")){
            username.setError("Morate popuniti ovo polje.");
            return true;
        }
        if(name.getText().toString().equals("")){
            name.setError("Morate popuniti ovo polje.");
            return true;
        }
        if(surname.getText().toString().equals("")){
            surname.setError("Morate popuniti ovo polje.");
            return true;
        }
        if(address.getText().toString().equals("")){
            address.setError("Morate popuniti ovo polje.");
            return true;
        }
        if(phone.getText().toString().equals("")){
            phone.setError("Morate popuniti ovo polje.");
            return true;
        }

        return false;
    }

}