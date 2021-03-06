package com.iaruchkin.deepbreath.network.endpoints;

import com.iaruchkin.deepbreath.network.dtos.WeatherResponse;

import androidx.annotation.NonNull;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherEndpoint {

    @GET("v1/forecast.json")
    Single<WeatherResponse> get(@Query("q") @NonNull String location);
}


