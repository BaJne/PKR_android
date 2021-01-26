package com.example.medenjak.controller;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.medenjak.R;
import com.example.medenjak.data.Repository;
import com.example.medenjak.model.Article;
import com.example.medenjak.model.Product;
import com.example.medenjak.util.Util;

public class ProductDetailActivity extends AppCompatActivity {

    private Product product;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        product = Repository.getProductDetail();
        setTitle(product.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ((TextView)findViewById(R.id.product_details_description)).setText(product.getDescription());
        ((TextView)findViewById(R.id.product_details_way_of_use)).setText(product.getWayOfUse());
        ((TextView)findViewById(R.id.product_details_price)).setText(Util.formatCurrency(product.getPrice()));
        ((ImageView)findViewById(R.id.product_details_picture)).setImageResource(product.getPicture());

        EditText count = findViewById(R.id.item_count);
        count.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable edt) {
                if (edt.length() == 1 && edt.toString().equals("0") || edt.length() == 0)
                    count.setText("1");
            }
        });

        findViewById(R.id.put_in_cart_btn).setOnClickListener( v ->{
            Repository.addToCart(new Article(product, Integer.parseInt(count.getText().toString())));
            Repository.sendNotification("Proizvod je dodat u korpu");
            onBackPressed();
        });

    }
}