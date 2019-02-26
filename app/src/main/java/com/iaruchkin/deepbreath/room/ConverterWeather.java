package com.iaruchkin.deepbreath.room;

import android.content.Context;
import android.util.Log;

import com.iaruchkin.deepbreath.App;
import com.iaruchkin.deepbreath.network.weatherApixuDTO.Forecastday;

import java.util.ArrayList;
import java.util.List;

public class ConverterWeather {

    private static final String TAG = "RoomConverterWeather";

    private static WeatherDao weatherDao = AppDatabase.getAppDatabase(App.INSTANCE).weatherDao();

    public static List<WeatherEntity> dtoToDao(List<Forecastday> listDTO, String weatherLocation){
        List<WeatherEntity> listDao = new ArrayList<>();

        for(Forecastday dto : listDTO) {
            WeatherEntity weatherEntity = new WeatherEntity();
            weatherEntity.setId(dto.date + weatherLocation);
            weatherEntity.setTemperature(dto.getDay().avgtemp_c);
            weatherEntity.setDate(dto.date);
            weatherEntity.setLocation(weatherLocation);

            listDao.add(weatherEntity);
        }
        return listDao;
    }

    public static WeatherEntity getDataById(Context context, String id){
        AppDatabase db = AppDatabase.getAppDatabase(context);
        return db.weatherDao().getDataById(id);
    }

    public static List<WeatherEntity> loadDataFromDb(Context context, String location) {
        AppDatabase db = AppDatabase.getAppDatabase(context);
        Log.i(TAG, "Weather data loaded from DB");
        return db.weatherDao().getAll(location);
    }

    public static void saveAllDataToDb(Context context, List<WeatherEntity> list, String location){
        AppDatabase db = AppDatabase.getAppDatabase(context);
        db.weatherDao().deleteAll(location);
        Log.i(TAG, "Weather DB: deleteAll");

        WeatherEntity data[] = list.toArray(new WeatherEntity[list.size()]);
        db.weatherDao().insertAll(data);
        Log.i(TAG, "Weather DB: insertAll");

        Log.i(TAG, "Weather data saved to DB");
        Log.i(TAG, list.toString());
        weatherDao.insertAll(data);

    }

    public static void editNewsToDb(Context context, WeatherEntity weatherEntity){
        AppDatabase db = AppDatabase.getAppDatabase(context);
        db.weatherDao().edit(weatherEntity);
    }

    public static void deleteNewsFromDb(Context context, WeatherEntity weatherEntity){
        AppDatabase db = AppDatabase.getAppDatabase(context);
        db.weatherDao().delete(weatherEntity);
    }
}
