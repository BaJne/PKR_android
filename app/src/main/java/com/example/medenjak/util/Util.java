package com.example.medenjak.util;

import android.graphics.Color;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

public class Util {
    public static void makeSnackBar(@NonNull View view, String text, boolean isShort){
        Snackbar.make(view, text, isShort ? Snackbar.LENGTH_SHORT: Snackbar.LENGTH_LONG)
                .setTextColor(Color.parseColor("#FFCC00"))
                .show();
    }
}
