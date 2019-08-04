package com.example.testio.dataListHelper;


public class DataObject {
    private String countryName;
    private String distance;

    public DataObject(String name, String distance) {
        this.countryName = name;
        this.distance = distance;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getDistance() {
        return distance;
    }
}
