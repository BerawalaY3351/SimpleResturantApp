package com.example.simplerestaurantapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private MenuAdapter adapter;
    private List<Dish> dishList;
    private Button buttonDeals, buttonGrocery, buttonFood;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize Data and Adapter
        dishList = new ArrayList<>();
        adapter = new MenuAdapter(dishList);
        recyclerView.setAdapter(adapter);

        // Set up TabLayout dynamically
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);

        // Create the Delivery tab
        TabLayout.Tab deliveryTab = tabLayout.newTab().setText("Delivery");
        deliveryTab.setContentDescription("Switch to Delivery");
        tabLayout.addTab(deliveryTab);

        // Create the Pickup tab
        TabLayout.Tab pickupTab = tabLayout.newTab().setText("Pickup");
        pickupTab.setContentDescription("Switch to Pickup");
        tabLayout.addTab(pickupTab);

        // Set up listener for TabLayout
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    // Delivery selected
                    loadFoodItems(); // Load default food items or change logic as needed
                } else {
                    // Pickup selected
                    loadPickupItems(); // Load pickup items or change logic as needed
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Handle unselected tab if needed
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Handle reselected tab if needed
            }
        });

        // Set up Buttons
        buttonDeals = view.findViewById(R.id.button_deals);
        buttonGrocery = view.findViewById(R.id.button_grocery);
        buttonFood = view.findViewById(R.id.button_food);

        // Set up button listeners
        buttonDeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDealsItems(); // Load Deals items
            }
        });

        buttonGrocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadGroceryItems(); // Load Grocery items
            }
        });

        buttonFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFoodItems(); // Load Food items
            }
        });

        // Load default items for the initial state
        loadFoodItems();

        return view;
    }

    // Methods to load different categories of items
    private void loadDealsItems() {
        dishList.clear();
        dishList.add(new Dish("Pizza Deal", 9.99));
        dishList.add(new Dish("Burger Combo", 7.99));
        dishList.add(new Dish("Salad Special", 5.99));
        adapter.notifyDataSetChanged();
    }

    private void loadGroceryItems() {
        dishList.clear();
        dishList.add(new Dish("Milk", 2.99));
        dishList.add(new Dish("Bread", 1.99));
        dishList.add(new Dish("Eggs", 3.99));
        adapter.notifyDataSetChanged();
    }

    private void loadFoodItems() {
        dishList.clear();
        dishList.add(new Dish("Pizza", 12.99));
        dishList.add(new Dish("Burger", 8.99));
        dishList.add(new Dish("Salad", 6.99));
        adapter.notifyDataSetChanged();
    }

    private void loadPickupItems() {
        dishList.clear();
        dishList.add(new Dish("Pickup Item 1", 10.99));
        dishList.add(new Dish("Pickup Item 2", 7.99));
        dishList.add(new Dish("Pickup Item 3", 5.99));
        adapter.notifyDataSetChanged();
    }
}
