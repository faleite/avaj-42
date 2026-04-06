package com.faaraujo.avaj.simulator;

import java.io.IOException;

public class Simulator {

  public static void main(String[] args) {

    Simulation simulation = new Simulation(args);

    try {
      simulation.run();
    } catch (IOException e) {
      System.err.println("Something went wrong, look: " + e.getMessage());
      System.exit(1);
    }
  }
}
