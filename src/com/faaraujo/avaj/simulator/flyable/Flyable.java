package com.faaraujo.avaj.simulator.flyable;

import com.faaraujo.avaj.simulator.tower.WeatherTower;

public abstract class Flyable {

  protected WeatherTower weatherTower;

  public abstract void updateConditions();

  public void registerTower(WeatherTower weatherTower) {

  }

}
