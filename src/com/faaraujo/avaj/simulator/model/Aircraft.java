package com.faaraujo.avaj.simulator.model;

import com.faaraujo.avaj.simulator.flyable.Flyable;

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

  @Override
  public void updateConditions() {
  }
}
