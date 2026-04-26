package com.faaraujo.avaj.simulator.factory;

import com.faaraujo.avaj.simulator.model.*;
import java.util.concurrent.atomic.AtomicLong;

public class AircraftFactory {

  private static volatile AircraftFactory instance;
  private static final AtomicLong idGenerate = new AtomicLong(0);

  private AircraftFactory() {

  }

  public Flyable newAircraft(String type, String name, Coordinates coordinates) {
    Flyable flyable = null;

    switch (type) {
      case "jetplane":
        flyable = new JetPlane(idGenerate.incrementAndGet(), name, coordinates);
        break;
      case "helicopter":
        flyable = new Helicopter(idGenerate.incrementAndGet(), name, coordinates);
        break;
      case "balloon":
        flyable = new Balloon(idGenerate.incrementAndGet(), name, coordinates);
        break;
    }

    if (flyable == null) {
      throw new IllegalArgumentException("Unknown Aircraft type: " + type);
    }

    return flyable;
  }

  public static AircraftFactory getInstance() {
    if (instance == null) {
      synchronized (AircraftFactory.class) {
        if (instance == null) {
          instance = new AircraftFactory();
        }
      }
    }
    return instance;
  }
}
