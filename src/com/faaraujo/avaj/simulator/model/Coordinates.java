package com.faaraujo.avaj.simulator;

public class Coordinates {

  private Integer longitude;
  private Integer latitude;
  private Integer height;

  public Coordinates() {

  }

  public Coordinates(
      Integer longitude, Integer latitude,
      Integer height) {

    this.longitude = longitude;
    this.latitude = latitude;
    this.height = height;

  }

  public Integer getLongitude() {
    return this.longitude;
  }

  public void setLongitude(Integer longitude) {
    this.longitude = longitude;
  }

  public Integer getLatitude() {
    return this.latitude;
  }

  public void setLatitude(Integer latitude) {
    this.latitude = latitude;
  }

  public Integer getHeight() {
    return this.height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }
}
