package com.disupport.storeFinder;

import java.util.List;

public class StoreRoot {
    public List<Store> store;
    public List<Place> place;
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
}

class Place{
    public double latitude;
    public double longitude;
}
