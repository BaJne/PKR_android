package com.example.medenjak.controller;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.medenjak.R;
import com.example.medenjak.data.Repository;
import com.example.medenjak.model.Product;

public class ProductDetailActivity extends AppCompatActivity {

    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        product = Repository.getProductDetail();
        setTitle(product.getName());

        ((TextView)findViewById(R.id.product_details_description)).setText(product.getDescription());
        ((TextView)findViewById(R.id.product_details_way_of_use)).setText(product.getWayOfUse());
        ((TextView)findViewById(R.id.product_details_price)).setText(product.getPrice() + " din.");

    }
}