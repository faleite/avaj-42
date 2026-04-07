package com.faaraujo.avaj.simulator.util;

import com.faaraujo.avaj.simulator.exceptions.ScenarioException;
import com.faaraujo.avaj.simulator.factory.AircraftFactory;
import com.faaraujo.avaj.simulator.model.Flyable;
import com.faaraujo.avaj.simulator.model.Coordinates;

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

  public int getNumberOfSimulations(String line) throws ScenarioException {
    int simulations = Integer.parseInt(line);
    if (simulations <= 0) {
      throw new ScenarioException("Number of simulations must be positive.");
    }
    return simulations;
  }

  public Flyable parseAircraft(String lines) throws ScenarioException {

    String[] fields = lines.split(" ");

    if (fields.length != 5) {
      throw new ScenarioException("Invalid aircraft format."
          + "\nExpected: TYPE NAME LON LAT HEIGHT");
    }

    try {
      String type = fields[0].toLowerCase();
      String name = fields[1];
      int longitude = Integer.parseInt(fields[2]);
      int latitude = Integer.parseInt(fields[3]);
      int height = Integer.parseInt(fields[4]);

      if (longitude < 0 || latitude < 0 || height < 0 || height > 100) {
        throw new ScenarioException("Parameter outside the acceptable range.");
      }

      Coordinates coordinates = Coordinates.of(longitude, latitude, height);
      return AircraftFactory.getInstance().newAircraft(type, name, coordinates);

    } catch (NumberFormatException e) {
      throw new ScenarioException("Invalid coordinates. They must be integers.", e);
    }
  }
}
