package com.example.guest.weatherapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guest on 7/5/16.
 */
public class WeatherService {

    public static final String TAG = WeatherActivity.class.getSimpleName();


    public static void getWeather (String location, Callback callback){
        String API_KEY = Constants.WEATHER_API_KEY;

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.LOCATION_QUERY_PARAMETER, location + ",us");
        urlBuilder.addQueryParameter(Constants.PREFERRED_FORMAT, "json");
        urlBuilder.addQueryParameter("appid", API_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
            .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Forecast> processResults(Response response) {
        ArrayList<Forecast> forecasts = new ArrayList<>();

        try {
            String jsonData = response.body().string();

            if (response.isSuccessful()) {
                JSONArray weatherJSON = new JSONObject(jsonData).getJSONArray("list");

                for (int i = 0; i < weatherJSON.length(); i++) {
                    String desc = weatherJSON.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("description");

                    Forecast forecast = new Forecast(desc);
                    forecasts.add(forecast);
                }
//                Log.v(TAG, String.valueOf(forecasts));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return forecasts;
    }
}
