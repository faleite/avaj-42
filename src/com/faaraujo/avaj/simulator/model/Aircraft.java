package com.faaraujo.avaj.simulator.model;

public abstract class Aircraft extends Flyable {

  protected long id;

  protected String name;

  protected Coordinates coordinates;

  protected Aircraft(long id, String name, Coordinates coordinates) {

    this.id = id;
    this.name = name;
    this.coordinates = coordinates;
  }

}
