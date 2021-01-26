package com.example.medenjak.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.medenjak.R;
import com.example.medenjak.data.Repository;
import com.example.medenjak.model.User;
import com.example.medenjak.util.Util;
import com.google.android.material.textfield.TextInputEditText;

public class PasswordChangeActivity extends AppCompatActivity {

    private User user;
    private TextInputEditText password;
    private TextInputEditText newPassword;
    private TextInputEditText repeatPassword;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);

        setTitle(R.string.change_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        user = Repository.getActiveUser();
        password = findViewById(R.id.change_password_old);
        newPassword = findViewById(R.id.change_password_new);
        repeatPassword = findViewById(R.id.change_password_repeat);

        findViewById(R.id.change_password_btn).setOnClickListener(v -> {
            if(updatePasswords()){
                Repository.sendNotification("Uspešno ste promenili korisničke podatke.");
                onBackPressed();
                finish();
            } else {
                password.setError("Lozinka nije ispravna.");
            }
        });
    }

    private boolean updatePasswords() {
        if(isInputTextEmpty()){
            return false;
        }

        if(doesPasswordMatch()){
            newPassword.setError("Lozinke se moraju poklapati");
            repeatPassword.setError("Lozinke se moraju poklapati");
            return false;
        }

        return Repository.updatePassword(password.getText().toString(), newPassword.getText().toString());
    }

    private boolean isInputTextEmpty() {
        boolean status = false;

        if(status = password.getText().toString().equals("")){
            password.setError("Morate popuniti ovo polje.");
        }
        if(status = newPassword.getText().toString().equals("")){
            newPassword.setError("Morate popuniti ovo polje.");
        }
        if(status = repeatPassword.getText().toString().equals("")){
            repeatPassword.setError("Morate popuniti ovo polje.");
        }
        return status;
    }

    private boolean doesPasswordMatch() {
        return
            !newPassword.getText().toString().equals(
                repeatPassword.getText().toString()
            );
    }
}