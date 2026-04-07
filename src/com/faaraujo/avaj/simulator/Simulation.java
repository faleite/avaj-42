package com.faaraujo.avaj.simulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.faaraujo.avaj.simulator.exceptions.ScenarioException;
import com.faaraujo.avaj.simulator.model.Flyable;
import com.faaraujo.avaj.simulator.util.Parser;
import com.faaraujo.avaj.simulator.util.Logger;
import com.faaraujo.avaj.simulator.tower.WeatherTower;

public class Simulation {

  private String scenarioFile;
  private int simulations;

  public Simulation(String[] args) {

    if (args.length != 1) {
      System.err.println("Error: Expected 1 argument, but received " + args.length + ".");
      System.err.println("Usage: java <classpath> com.faaraujo.avaj.simulator.Simulator <scenario_file>");
      System.exit(1);
    }

    this.scenarioFile = args[0];
  }

  public void initLogFile() {
    try {
      Logger.getInstance().init("simulation.txt");
    } catch (IOException e) {
      System.err.println("Log file error: " + e.getMessage());
      System.exit(1);
    }
  }

  public void run() {
    initLogFile();

    try (BufferedReader reader = new BufferedReader(new FileReader(scenarioFile))) {
      String line;
      while ((line = reader.readLine()) != null) {
        if (!line.trim().isEmpty()) {
          try {
            simulations = Parser.getInstance().getNumberOfSimulations(line);
          } catch (ScenarioException e) {
            System.err.println("Scenario error: " + e.getMessage());
            System.exit(1);
          }
          break;
        }
      }
      loadScenario(reader);
    } catch (IOException e) {
      System.err.println("File error: " + e.getMessage());
      System.exit(1);
    }
  }

  private void loadScenario(BufferedReader reader) throws IOException {

    WeatherTower weatherTower = new WeatherTower();

    String line;
    while ((line = reader.readLine()) != null) {
      if (!line.trim().isEmpty()) {
        try {
          Flyable aircraft = Parser.getInstance().parseAircraft(line);
          aircraft.registerTower(weatherTower);
        } catch (ScenarioException e) {
          System.err.println("Scenario error: " + e.getMessage());
          System.exit(1);
        }
      }
    }

    for (int i = 0; i < simulations; i++) {
      weatherTower.changeWeather();
    }
  }
}
