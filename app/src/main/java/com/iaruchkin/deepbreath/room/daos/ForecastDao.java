package com.iaruchkin.deepbreath.room.daos;

import com.iaruchkin.deepbreath.room.entities.ForecastEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ForecastDao {

    @Query("SELECT * FROM forecast")
    List<ForecastEntity> getAll();

    @Query("SELECT * FROM forecast WHERE locationName = :locationName")
    List<ForecastEntity> getAll(String locationName);

    @Query("SELECT * FROM forecast WHERE parameter = :parameter")
    List<ForecastEntity> getByParameter(String parameter);

    @Query("SELECT * FROM forecast WHERE id = :id")
    ForecastEntity getDataById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ForecastEntity... forecastEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ForecastEntity forecastEntity);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void edit(ForecastEntity forecastEntity);

    @Delete
    void delete(ForecastEntity forecastEntity);

    @Query("DELETE FROM forecast WHERE parameter = :parameter")
    void deleteAll(String parameter);

    @Query("DELETE FROM forecast WHERE locationName = :locationName")
    void deleteByLocation(String locationName);

}
