package com.faaraujo.avaj.simulator.factory;

import com.faaraujo.avaj.simulator.model.*;

public class AircraftFactory {

  private static AircraftFactory instance;
  private static long idGenerate = 0;

  private AircraftFactory() {

  }

  public Flyable newAircraft(String type, String name, Coordinates coordinates) {
    Flyable flyable = null;

    switch (type) {
      case "jetplane":
        flyable = new JetPlane(++idGenerate, name, coordinates);
        break;
      case "helicopter":
        flyable = new Helicopter(++idGenerate, name, coordinates);
        break;
      case "balloon":
        flyable = new Balloon(++idGenerate, name, coordinates);
        break;
    }

    if (flyable == null) {
      throw new IllegalArgumentException("Unknown Aircraft type: " + type);
    }

    return flyable;
  }

  public static AircraftFactory getInstance() {

    if (instance == null) {
      instance = new AircraftFactory();
    }

    return instance;
  }
}
