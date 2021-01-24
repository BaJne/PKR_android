package com.example.medenjak.controller.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medenjak.R;
import com.example.medenjak.adapter.ListItemClickListener;
import com.example.medenjak.adapter.ProductAdapter;
import com.example.medenjak.controller.Navigator;
import com.example.medenjak.controller.ProductDetailActivity;
import com.example.medenjak.data.Repository;

import java.util.ArrayList;

public class ProductsFragment extends Fragment {

    private Navigator navigator;

    private final ListItemClickListener listener = new ListItemClickListener() {
        @Override
        public void onListItemClick(int position) {
            Repository.checkProduct(position);
            navigator.startActivity(ProductDetailActivity.class);
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
        Log.d("ProductsFragment", "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        gridLayoutManager.setSmoothScrollbarEnabled(true);

        ProductAdapter productAdapter = new ProductAdapter(getContext(), Repository.getProducts(), listener);

        RecyclerView productRecyclerView = view.findViewById(R.id.products_recycler_view);
        productRecyclerView.setHasFixedSize(true);
        productRecyclerView.setLayoutManager(gridLayoutManager);
        productRecyclerView.setAdapter(productAdapter);

        return view;
    }
}