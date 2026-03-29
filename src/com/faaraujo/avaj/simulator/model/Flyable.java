package com.faaraujo.avaj.simulator.model;

import com.faaraujo.avaj.simulator.tower.WeatherTower;

public abstract class Flyable {

  protected WeatherTower weatherTower;

  public abstract void updateConditions();

  public void registerTower(WeatherTower weatherTower) {
    this.weatherTower = weatherTower;
    weatherTower.register(this);
  }

}
