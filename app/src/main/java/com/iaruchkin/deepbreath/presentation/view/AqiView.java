package com.iaruchkin.deepbreath.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.iaruchkin.deepbreath.common.State;
import com.iaruchkin.deepbreath.room.AqiEntity;
import com.iaruchkin.deepbreath.room.ForecastEntity;

import androidx.annotation.NonNull;

@StateStrategyType(value = SingleStateStrategy.class)
public interface AqiView extends MvpView {

    void setWeatherData(@NonNull ForecastEntity data);
    void setAqiData(@NonNull AqiEntity data);
    void showState(@NonNull State state);

}