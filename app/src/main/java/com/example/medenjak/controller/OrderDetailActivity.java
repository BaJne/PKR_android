package com.example.medenjak.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.medenjak.R;
import com.example.medenjak.adapter.OrderDetailAdapter;
import com.example.medenjak.data.Repository;
import com.example.medenjak.model.Order;
import com.example.medenjak.util.Util;

public class OrderDetailActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        setTitle("Detalji narudžbine");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Order order = Repository.getCheckedOrder();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);

        OrderDetailAdapter adapter = new OrderDetailAdapter(getBaseContext(), order.getCart());
        RecyclerView recyclerView = findViewById(R.id.order_detail_recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        ((TextView)findViewById(R.id.order_detail_date)).setText(Util.formatDate(order.getDeliveryDate()));
        ((TextView)findViewById(R.id.order_detail_price)).setText(Util.formatCurrency(order.getTotalCost()));
        ((TextView)findViewById(R.id.order_detail_status)).setText(order.getStatus().getStatusMsg());

    }
}