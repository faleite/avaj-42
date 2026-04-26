package com.faaraujo.avaj.simulator.provider;

import com.faaraujo.avaj.simulator.model.Coordinates;

public class WeatherProvider {

  private static final String[] weather = { "RAIN", "FOG", "SUN", "SNOW" };

  private static volatile WeatherProvider instance;

  private WeatherProvider() {

  }

  public static WeatherProvider getInstance() {
    if (instance == null) {
      synchronized (WeatherProvider.class) {
        if (instance == null) {
          instance = new WeatherProvider();
        }
      }
    }
    return instance;
  }

  public String getCurrentWeather(Coordinates coordinates) {
    int index = Math.abs(coordinates.getLongitude() +
        coordinates.getLatitude() +
        coordinates.getHeight()) % weather.length;

    return weather[index];
  }

}
