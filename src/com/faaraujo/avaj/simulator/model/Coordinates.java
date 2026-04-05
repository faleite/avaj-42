package com.faaraujo.avaj.simulator.model;

public class Coordinates {

  private int longitude;
  private int latitude;
  private int height;

  Coordinates(
      int longitude, int latitude,
      int height) {

    this.longitude = longitude;
    this.latitude = latitude;
    this.height = height;

  }

  public int getLongitude() {
    return this.longitude;
  }

  public int getLatitude() {
    return this.latitude;
  }

  public int getHeight() {
    return this.height;
  }

  public static Coordinates of(int longitude, int latitude, int height) {
    return new Coordinates(longitude, latitude, height);
  }

  Coordinates add(int longitude, int latitude, int height) {

    return new Coordinates(
        this.longitude + longitude,
        this.latitude + latitude,
        Math.min(100, Math.max(0, this.height + height)));
  }

}
