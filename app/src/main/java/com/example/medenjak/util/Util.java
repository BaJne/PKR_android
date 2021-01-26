package com.example.medenjak.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import com.example.medenjak.R;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Locale;

public class Util {
    public enum FontType{
        REGULAR_FONT,
        THIN_FONT,
        BOLD_FONT,
        MEDIUM_FONT,
        ITALIC_FONT
    }

    private static Hashtable<FontType, Typeface> fontCache = new Hashtable<FontType, Typeface>();

    public static void makeSnackBar(@NonNull View view, String text, boolean isShort){
        Snackbar snackbar = Snackbar.make(view, text, isShort ? Snackbar.LENGTH_SHORT: Snackbar.LENGTH_LONG);

        if(isShort){
            snackbar.setTextColor(Color.parseColor("#FFCC00"));
            snackbar.setBackgroundTint(Color.parseColor("#383F47"));
        } else {
            snackbar.setTextColor(Color.parseColor("#383F47"));
            snackbar.setBackgroundTint(Color.parseColor("#FFCC00"));
            snackbar.setAction("X", v -> snackbar.dismiss());
        }

        snackbar.show();
    }

    public static String formatCurrency(int value){
        String money = ".00 din.";
        while(value > 0) {
            String digits = String.valueOf(value % 1000);
            money =  digits + money;
            value /= 1000;

            if(value > 0){
                String firstZeros = "";
                if(digits.length()==1){
                    firstZeros = "00";
                } else if(digits.length()==2){
                    firstZeros = "0";
                }
                money = ',' + firstZeros + money;
            }
        }
        return money;
    }

    public static Typeface getFontFamily(Context context, FontType font_type){
        Typeface tf = fontCache.get(font_type);
        if(tf != null){
            return tf;
        }

        switch (font_type){
            case MEDIUM_FONT:
                tf = ResourcesCompat.getFont(context, R.font.roboto_medium);
                break;
            case REGULAR_FONT:
                tf = ResourcesCompat.getFont(context, R.font.roboto_regular);
                break;
            case BOLD_FONT:
                tf = ResourcesCompat.getFont(context, R.font.roboto_bold);
                break;
            case THIN_FONT:
                tf = ResourcesCompat.getFont(context, R.font.roboto_light);
                break;
            case ITALIC_FONT:
                tf = ResourcesCompat.getFont(context, R.font.roboto_italic);
                break;
            default:
                return null;
        }

        fontCache.put(font_type, tf);
        return tf;
    }

    public static String formatDate(Calendar calendar){
        if(calendar == null)
            return "Nije određeno";
        SimpleDateFormat formmat1 = new SimpleDateFormat("dd/MM/yyyy");
        String formatter = formmat1.format(calendar.getTime());
        return formatter;
    }

    public static String articleCountToString(int size) {
        StringBuilder sb = new StringBuilder();
        sb.append(size).append(" ");
        if(size > 1){
            sb.append("artikala");
        } else {
            sb.append("artikal");
        }
        return sb.toString();
    }

}
