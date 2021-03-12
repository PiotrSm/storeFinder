package com.disupport.storeFinder;

import java.util.ArrayList;
import java.util.List;

public class StoreRoot {
    public List<Store> store = new ArrayList<>();
    public List<Place> place;

    @Override
    public String toString() {
        return "StoreRoot{" +
                "store=" + store +
                ", place=" + place +
                '}';
    }
}
class OpeningTime{
    public String from;
    public String to;
}

class OpeningDay{
    public String days;
    public List<OpeningTime> openingTimes;
    public boolean extra;
}

class Store{
    public int id;
    public String city;
    public String postcode;
    public String street;
    public String streetWithoutNumber;
    public String streetNumber;
    public double latitude;
    public double longitude;
    public double distance;
    public List<OpeningDay> openingDay;

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", postcode='" + postcode + '\'' +
                ", street='" + street + '\'' +
                ", streetWithoutNumber='" + streetWithoutNumber + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", distance=" + distance +
                ", openingDay=" + openingDay +
                '}';
    }
}

class Place{
    public double latitude;
    public double longitude;
}
