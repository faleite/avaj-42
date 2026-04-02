package com.faaraujo.avaj.simulator.tower;

import com.faaraujo.avaj.simulator.model.Coordinates;
import com.faaraujo.avaj.simulator.provider.WeatherProvider;

public class WeatherTower extends Tower {

  public String getWeather(Coordinates coodirnates) {
    return WeatherProvider.getInstance().getCurrentWeather(coodirnates);
  }

  public void changeWeather() {
    super.conditionChanged();
  }
}
