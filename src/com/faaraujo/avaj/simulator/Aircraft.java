package com.faaraujo.avaj.simulator;

public class Aircraft {

  protected Long id;

  protected String name;

  protected Coordinates coordinates;

  protected Aircraft() {

  }

  protected Aircraft(Long id, String name, Coordinates coordinates) {

    this.id = id;
    this.name = name;
    this.coordinates = coordinates;
  }
}
