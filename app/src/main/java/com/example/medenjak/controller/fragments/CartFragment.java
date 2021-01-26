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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.medenjak.R;
import com.example.medenjak.adapter.ArticleAdapter;
import com.example.medenjak.adapter.ArticleClickListener;
import com.example.medenjak.controller.Navigator;
import com.example.medenjak.data.Repository;
import com.example.medenjak.model.Article;
import com.example.medenjak.util.Util;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    private RecyclerView cartRecyclerView;
    private TextView totalPrice;
    private LinearLayout emptyLayout;
    private LinearLayout bottomControlLayout;
    private Button addArticleBtn;
    private Navigator navigator;

    private final ArticleClickListener listener = new ArticleClickListener() {
        @Override
        public void incrementCount(int position) {
            Repository.incrementItemCount(position);
            totalPrice.setText(Util.formatCurrency(Repository.getCartTotalCost()));
        }

        @Override
        public boolean decrementCount(int position) {
            boolean status = Repository.decrementItemCount(position);
            if(status)
                totalPrice.setText(Util.formatCurrency(Repository.getCartTotalCost()));
            return status;
        }

        @Override
        public void removeItem(int position) {
            Repository.removeFromCart(position);
            if(Repository.isCartEmpty()){
                cartRecyclerView.setVisibility(View.GONE);
                bottomControlLayout.setVisibility(View.GONE);
                emptyLayout.setVisibility(View.VISIBLE);
            } else {
                totalPrice.setText(Util.formatCurrency(Repository.getCartTotalCost()));
            }
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
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        ArrayList<Article> cart = Repository.getCart();

        emptyLayout = view.findViewById(R.id.empty_cart_layout);
        view.findViewById(R.id.empty_cart_add_product).setOnClickListener(v->{
            navigator.selectBottomNavigationMenu(R.id.products);
        });

        if(cart.size() > 0){
            emptyLayout.setVisibility(View.GONE);
            initializeRecyclerView(cart, view);
            initializeBottomControlLayout(view);
        }

        return view;
    }

    private void initializeRecyclerView(ArrayList<Article> cart, View view) {

        // Initialize linear manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.offsetChildrenVertical(8);

        // Initialize recyclerView adapter
        ArticleAdapter articleAdapter = new ArticleAdapter(getContext(), cart, listener);

        // Initialize recyclerView
        cartRecyclerView = view.findViewById(R.id.cart_recycler_view);
        cartRecyclerView.setHasFixedSize(true);
        cartRecyclerView.setLayoutManager(linearLayoutManager);
        cartRecyclerView.setAdapter(articleAdapter);
        cartRecyclerView.setVisibility(View.VISIBLE);

    }

    private void initializeBottomControlLayout(View view) {
        bottomControlLayout = view.findViewById(R.id.cart_order_linear_layout);
        bottomControlLayout.setVisibility(View.VISIBLE);

        view.findViewById(R.id.make_order_btn).setOnClickListener(v -> {
            Repository.addNewOrder();
            navigator.selectBottomNavigationMenu(R.id.orders);
        });

        //Initialize bottom control sector
        totalPrice = view.findViewById(R.id.cart_total_price);
        totalPrice.setText(Util.formatCurrency(Repository.getCartTotalCost()));
    }
}