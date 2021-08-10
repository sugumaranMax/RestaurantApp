package com.example.myrestaurantapp.restaurant;

import java.util.List;

public class RestaurantModel {

    private int id;
    private String name;
    private String neighborhood;
    private String photograph;
    private String address;
    private MyLatLng latlng;
    private String cuisine_type;
    private OperatingHours operating_hours;
    private List<ReviewModel> reviews;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getPhotograph() {
        return photograph;
    }

    public String getAddress() {
        return address;
    }

    public MyLatLng getLatlng() {
        return latlng;
    }

    public String getCuisine_type() {
        return cuisine_type;
    }

    public OperatingHours getOperating_hours() {
        return operating_hours;
    }

    public List<ReviewModel> getReviews() {
        return reviews;
    }
}
