package com.example.medenjak.controller.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medenjak.R;
import com.example.medenjak.adapter.ListItemClickListener;
import com.example.medenjak.adapter.OrderAdapter;
import com.example.medenjak.controller.Navigator;
import com.example.medenjak.controller.OrderDetailActivity;
import com.example.medenjak.data.Repository;
import com.example.medenjak.model.Order;

import java.util.ArrayList;

public class OrderFragment extends Fragment {
    private Navigator navigator;

    private final ListItemClickListener listener = new ListItemClickListener() {
        @Override
        public void onListItemClick(int position) {
            Repository.checkOrder(position);
            navigator.startActivity(OrderDetailActivity.class);
        }
    };

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navigator = (Navigator)context;
    }

    @Override
    public void onDetach() {
        navigator = null;
        super.onDetach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        ArrayList<Order> orders = Repository.getOrders();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);

        OrderAdapter orderAdapter = new OrderAdapter(getContext(), orders, listener);

        RecyclerView productRecyclerView = view.findViewById(R.id.order_recyclerview);
        productRecyclerView.setHasFixedSize(true);
        productRecyclerView.setLayoutManager(linearLayoutManager);
        productRecyclerView.setAdapter(orderAdapter);


        if(orders.isEmpty()){
            view.findViewById(R.id.no_order_layout).setVisibility(View.VISIBLE);
        }

        return view;
    }

}