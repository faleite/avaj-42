package com.faaraujo.avaj.simulator;

import com.faaraujo.avaj.simulator.Flyable;

public class Aircraft extends Flyable {

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
