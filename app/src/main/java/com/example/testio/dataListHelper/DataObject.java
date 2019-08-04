package com.example.testio.dataListHelper;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataObject {
    private String countryName;
    private String distance;

    public DataObject(JSONObject object){
        try {
            this.countryName = object.getString("name");
            this.distance = object.getString("distance");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<DataObject> fromJson(JSONArray jsonObjects) {
        ArrayList<DataObject> server = new ArrayList<>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                server.add(new DataObject(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return server;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getDistance() {
        return distance;
    }
}
