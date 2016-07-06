package com.example.guest.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import com.example.guest.weatherapp.adapters.WeatherListAdapter;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Guest on 7/5/16.
        */
public class WeatherActivity extends AppCompatActivity {

    public static final String TAG = WeatherActivity.class.getSimpleName();
    //@Bind(R.id.listView) ListView mListView;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private WeatherListAdapter mAdapter;

    public ArrayList<Forecast> mForecasts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        getWeather(location);
    }

    private void getWeather(String location) {
        final WeatherService weatherService = new WeatherService();

        weatherService.getWeather(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mForecasts = weatherService.processResults(response);

                WeatherActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new WeatherListAdapter(getApplicationContext(), mForecasts);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(WeatherActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }

        });
    }
}
