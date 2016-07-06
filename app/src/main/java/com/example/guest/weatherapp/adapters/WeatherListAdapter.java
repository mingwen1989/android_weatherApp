package com.example.guest.weatherapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.guest.weatherapp.Forecast;
import com.example.guest.weatherapp.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 7/6/16.
 */
public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder> {
    private ArrayList<Forecast> mForecasts = new ArrayList<>();
    private Context mContext;

    public WeatherListAdapter(Context context, ArrayList<Forecast> forecasts) {
        mContext = context;
        mForecasts = forecasts;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list, parent, false);
        WeatherViewHolder viewHolder = new WeatherViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        int newPosition = position + 1;
        String day = "Day " + newPosition + ":  ";
        holder.bindWeather(day + mForecasts.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mForecasts.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.weatherTextView) TextView mWeatherTextView;

        private Context mContext;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindWeather(String forecast) {
            mWeatherTextView.setText(forecast);
        }
    }
}
