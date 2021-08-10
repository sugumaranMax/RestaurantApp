package com.example.myrestaurantapp.adapter;

import com.example.myrestaurantapp.menu.MenuItems;
import com.example.myrestaurantapp.restaurant.RestaurantModel;

public class AdapterModel {

    private MenuItems menuItems;
    private RestaurantModel restaurantModel;

    public AdapterModel(MenuItems menuItems, RestaurantModel restaurantModel) {
        this.menuItems = menuItems;
        this.restaurantModel = restaurantModel;
    }

    public MenuItems getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(MenuItems menuItems) {
        this.menuItems = menuItems;
    }

    public RestaurantModel getRestaurantModel() {
        return restaurantModel;
    }

    public void setRestaurantModel(RestaurantModel restaurantModel) {
        this.restaurantModel = restaurantModel;
    }
}
