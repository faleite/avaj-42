package com.faaraujo.avaj.simulator;

public abstract class Flyable {

  protected WeatherTower weatherTower;

  public abstract void updateConditions();

  public void registerTower(WeatherTower weatherTower) {

  }

}
