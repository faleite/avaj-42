package com.faaraujo.avaj.simulator;

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
