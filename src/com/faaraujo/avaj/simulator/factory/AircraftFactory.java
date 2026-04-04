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
      case "JetPlane":
        flyable = new JetPlane(idGenerate++, name, coordinates);
        break;
      case "Helicopter":
        flyable = new Helicopter(idGenerate++, name, coordinates);
        break;
      case "Balloon":
        flyable = new Balloon(idGenerate++, name, coordinates);
        break;
      default:
        // TODO: create log message
        System.out.println("We dont know this type of Aircraft man. Sorry!");
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
