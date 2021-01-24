package com.example.medenjak.controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medenjak.R;
import com.example.medenjak.data.Repository;
import com.example.medenjak.model.User;
import com.example.medenjak.util.Util;
import com.fdev.backgroundchart.GradientChart;

public class RegisterActivity extends AppCompatActivity {

    TextView username;
    TextView password;
    TextView repeat_password;
    TextView name;
    TextView surname;
    TextView address;
    TextView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        GradientChart gradient = findViewById(R.id.register_background);
        Float charts[] = {40f, 50f, 45f, 51f, 30f, 38f, 33f, 45f};
        gradient.setChartValues(charts);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repeat_password = findViewById(R.id.password_repeat);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);

        findViewById(R.id.register_btn).setOnClickListener(v -> {
            if(isRequiredFieldsEmpty()){
                Util.makeSnackBar(v, "Morate popuniti sva polja.", true);
                return;
            }

            if(!isPasswordsEqual()){
                Util.makeSnackBar(v, "Lozinke se ne poklapaju", true);
                return;
            }

            User newUser = new User(
                username.getText().toString(),
                password.getText().toString(),
                name.getText().toString(),
                surname.getText().toString(),
                address.getText().toString(),
                phone.getText().toString()
            );
            int result = Repository.register(newUser);

            if(result == Repository.REGISTRATION_OK){
                navigateToLogin(true);
            }
            else {
                Util.makeSnackBar(v, "Korisničko ime je već u upotrebi.", true);
            }
        });

        findViewById(R.id.has_account).setOnClickListener(v -> {
            navigateToLogin(false);
        });
    }

    private void navigateToLogin(boolean registrationCompleted){
        Intent intent = new Intent(this, LoginActivity.class);
        if(registrationCompleted){
            intent.setAction("Registration is successful");
        }
        startActivity(intent);
    }

    private boolean isPasswordsEqual() {
        return password.getText().toString().equals(
            repeat_password.getText().toString()
        );
    }

    private boolean isRequiredFieldsEmpty() {
        if(
            username.getText().length() == 0 ||
            password.getText().length() == 0 ||
            repeat_password.getText().length() == 0 ||
            name.getText().length() == 0 ||
            surname.getText().length() == 0 ||
            address.getText().length() == 0 ||
            phone.getText().length() == 0
        ){
            return true;
        }
        return false;
    }
}