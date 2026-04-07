package com.faaraujo.avaj.simulator.tower;

import com.faaraujo.avaj.simulator.model.Coordinates;
import com.faaraujo.avaj.simulator.provider.WeatherProvider;

public class WeatherTower extends Tower {

  public String getWeather(Coordinates coordinates) {
    return WeatherProvider.getInstance().getCurrentWeather(coordinates);
  }

  public void changeWeather() {
    super.conditionChanged();
  }
}
