package com.example.myrestaurantapp.menu;

import java.util.List;

public class MenuModel {
    private int restaurantId;
    private List<MenuCategory> categories;

    public int getRestaurantId() {
        return restaurantId;
    }

    public List<MenuCategory> getCategories() {
        return categories;
    }
}
