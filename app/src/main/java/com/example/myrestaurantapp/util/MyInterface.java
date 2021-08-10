package com.example.myrestaurantapp.util;

import com.example.myrestaurantapp.menu.MenuItems;
import com.example.myrestaurantapp.restaurant.RestaurantModel;

import java.util.List;

public interface MyInterface {
    void updateData(List<MenuItems> menuTempList, List<RestaurantModel> restaurantTempList);
}
