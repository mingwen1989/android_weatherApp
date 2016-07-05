package com.example.guest.weatherapp;

/**
 * Created by Guest on 7/5/16.
 */
public class Forecast {
    private String mDescription; //ex. rain

    public Forecast(String description) {
        this.mDescription = description;
    }

    public String getDescription() {
        return mDescription;
    }
}
