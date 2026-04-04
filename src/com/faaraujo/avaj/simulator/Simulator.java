package com.faaraujo.avaj.simulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.faaraujo.avaj.simulator.model.Flyable;
import com.faaraujo.avaj.simulator.model.Parser;
import com.faaraujo.avaj.simulator.tower.WeatherTower;

public class Simulator {

  public static void main(String[] args) {

    if (args.length != 1) {
      System.err.println("Error: Expected 1 argument, but received " + args.length + ".");
      System.err.println("Usage: java <classpath> com.faaraujo.avaj.simulator.Simulator <scenario_file>");
      System.exit(1);
    }

    try {
      Logger.getInstance().init("simulation.txt");
    } catch (IOException e) {
      System.err.println("Something went wrong, look: " + e.getMessage());
      System.exit(1);
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
      //
      // TODO: Igonorar Linhas vazias!!!
      //
      int simulations = Parser.getInstance().getNumberOfSimulations(reader.readLine());
      WeatherTower weatherTower = new WeatherTower();

      String line;
      while ((line = reader.readLine()) != null) {
        Flyable aircraft = Parser.getInstance().parseAircraft(line);
        aircraft.registerTower(weatherTower);

      }
      for (int i = 0; i < simulations; i++) {
        weatherTower.changeWeather();
      }
    } catch (IOException e) {
      System.err.println("File error: " + e.getMessage());
      System.exit(1);
    } catch (IllegalArgumentException e) {
      System.err.println("Parser error: " + e.getMessage());
      System.exit(1);
    }
  }
}
