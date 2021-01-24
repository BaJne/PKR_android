package com.example.medenjak.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medenjak.R;
import com.example.medenjak.data.Repository;
import com.example.medenjak.util.Util;
import com.fdev.backgroundchart.GradientChart;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        GradientChart gradient = findViewById(R.id.gradientChart);
        Float charts[] = {40f, 50f, 45f, 51f, 30f, 38f, 33f, 45f};
        gradient.setChartValues(charts);

        final TextView username = findViewById(R.id.login_username);
        final TextView password = findViewById(R.id.login_password);

        findViewById(R.id.log_in).setOnClickListener(v -> {
            String mUsername = username.getText().toString();
            String mPassword = password.getText().toString();
            login(mUsername, mPassword, v);
        });

        findViewById(R.id.register).setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        //Show notification in case registration is performed successfully
        String action = getIntent().getAction();
        if(action != null && action.equals("Registration is successful")){
            getIntent().setAction(null);
            Util.makeSnackBar(username, "Uspešno ste registrovani.", true);
        }
    }

    private void login(String mUsername, String mPassword, View v) {
        if(mUsername.isEmpty() || mPassword.isEmpty()){
            Util.makeSnackBar(v, "Morate popuniti sva polja.", true);
            return;
        }

        if(Repository.login(mUsername, mPassword)){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Util.makeSnackBar(v, "Kombinacija korisničkog imena i lozinke nije dobar.", true);
        }
    }

}