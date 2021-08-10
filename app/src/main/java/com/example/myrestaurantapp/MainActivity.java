package com.example.myrestaurantapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myrestaurantapp.adapter.AdapterModel;
import com.example.myrestaurantapp.adapter.RestaurantRecyclerAdapter;
import com.example.myrestaurantapp.menu.MenuItems;
import com.example.myrestaurantapp.restaurant.RestaurantModel;
import com.example.myrestaurantapp.util.AppUtil;
import com.example.myrestaurantapp.util.MyInterface;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyInterface {

    private EditText edtSearch;
    private RecyclerView recyclerView;
    private AppUtil appUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        appUtil = AppUtil.getInstance(this);
        appUtil.initializeData(this);

        recyclerView = findViewById(R.id.recyclerView);
        edtSearch = findViewById(R.id.edtSearch);

        appUtil.addTextChangeListener(edtSearch);
    }

    @Override
    public void updateData(List<MenuItems> menuTempList, List<RestaurantModel> restaurantTempList) {
        List<AdapterModel> list = new ArrayList<>();

        for(RestaurantModel restaurantModel : restaurantTempList){
            list.add(new AdapterModel(null, restaurantModel));
        }
        for(MenuItems menuItem : menuTempList){
            list.add(new AdapterModel(menuItem, null));
        }

        RestaurantRecyclerAdapter restaurantRecyclerAdapter = new RestaurantRecyclerAdapter(this, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(restaurantRecyclerAdapter);

    }
}