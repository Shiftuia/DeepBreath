package com.iaruchkin.deepbreath.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iaruchkin.deepbreath.R;
import com.iaruchkin.deepbreath.room.WeatherEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class WeatherItemAdapter extends RecyclerView.Adapter<WeatherItemAdapter.WeatherViewHolder>{
    private final List<WeatherEntity> weatherItemList = new ArrayList<>();
    private final WeatherAdapterOnClickHandler mClickHandler;

    public interface WeatherAdapterOnClickHandler {
        void onClick(WeatherEntity weatherItem);
    }

    public WeatherItemAdapter(WeatherAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View weatherListView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
        return new WeatherViewHolder(weatherListView);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, final int position) {
        WeatherEntity weatherItem = weatherItemList.get(position);
        holder.bind(weatherItem);
    }

    @Override
    public int getItemCount() {
        if (null == weatherItemList) return 0;
        return weatherItemList.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView imageView;
        private final TextView categoryTextView;
        private final TextView dateTextView;

        public void bind(WeatherEntity weatherItem) {
            categoryTextView.setText(weatherItem.getLocation());
            dateTextView.setText(weatherItem.getDate());
        }

        public WeatherViewHolder(View view) {
            super(view);

            imageView=view.findViewById(R.id.weather_icon);
            categoryTextView=view.findViewById(R.id.high_temperature);
            dateTextView = view.findViewById(R.id.date);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            WeatherEntity newsItem = weatherItemList.get(adapterPosition);
            mClickHandler.onClick(newsItem);
        }
    }

        public void replaceItems(List<WeatherEntity> weatherData) {
            weatherItemList.clear();
            weatherItemList.addAll(weatherData);
            notifyDataSetChanged();
        }
}