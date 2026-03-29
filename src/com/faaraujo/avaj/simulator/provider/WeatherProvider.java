package com.faaraujo.avaj.simulator.provider;

import com.faaraujo.avaj.simulator.model.Coordinates;

public class WeatherProvider {

  private String[] weather;

  private WeatherProvider() {

  }

  private WeatherProvider(String[] weather) {
    this.weather = weather;
  }

  public String getCurrentWeather(Coordinates coordinates) {
    return null;
  }

  public String[] getWeather() {
    return this.weather;
  }

  public void setWeather(String[] weather) {
    this.weather = weather;
  }
}
