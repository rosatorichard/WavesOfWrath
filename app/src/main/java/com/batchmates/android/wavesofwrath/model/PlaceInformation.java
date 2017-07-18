package com.batchmates.android.wavesofwrath.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Android on 7/17/2017.
 */

public class PlaceInformation {

    String name,address,icon;
    int price;
    double rating;
    LatLng location;

    public PlaceInformation(String name, String address, String icon, int price,double rating,LatLng location) {
        this.name = name;
        this.address = address;
        this.icon = icon;
        this.price = price;
        this.rating = rating;
        this.location=location;
    }

    public LatLng getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getIcon() {
        return icon;
    }

    public int getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }
}
