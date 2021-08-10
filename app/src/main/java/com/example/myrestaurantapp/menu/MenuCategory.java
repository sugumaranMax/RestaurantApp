package com.example.myrestaurantapp.menu;

import java.util.List;

public class MenuCategory {
    private int id;
    private String name;
    private List<MenuItems> menuitems;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<MenuItems> getMenuItems() {
        return menuitems;
    }
}
