package com.faaraujo.avaj.simulator.model;

import java.io.IOException;

import com.faaraujo.avaj.simulator.factory.AircraftFactory;

public class Parser {

  private static Parser instance;

  private Parser() {

  }

  public static Parser getInstance() {

    if (instance == null) {
      instance = new Parser();
    }
    return instance;
  }

  public int getNumberOfSimulations(String line) throws IllegalArgumentException {
    int simulations = Integer.parseInt(line);
    if (simulations <= 0) {
      throw new IllegalArgumentException("Number of simulations must be positive");
    }
    return simulations;
  }

  /*
   * Type Name Long lat hei
   * Balloon B2 1 8 66
   * JetPlane J1 23 44 32
   * Helicopter H1 654 33 20
   **/
  public Flyable parseAircraft(String lines) throws IllegalArgumentException {

    String[] fields = lines.split(" ");

    if (fields.length != 5) {
      throw new IllegalArgumentException("Error: Invalid aircraft format."
          + "\nExpected: TYPE NAME LON LAT HEIGHT");
    }

    try {
      String type = fields[0];
      String name = fields[1];
      int longitude = Integer.parseInt(fields[2]);
      int latitude = Integer.parseInt(fields[3]);
      int height = Integer.parseInt(fields[4]);

      Coordinates coordinates = new Coordinates(longitude, latitude, height);
      return AircraftFactory.getInstance().newAircraft(type, name, coordinates);

    } catch (NumberFormatException e) {
      // TODO: handle exception
      throw new IllegalArgumentException("Invalid coordinates: must be integers", e);
    }
  }
}
