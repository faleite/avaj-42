package com.faaraujo.avaj.simulator.model;

public class Baloon extends Aircraft {

  public Baloon() {

  }

  public Baloon(Long id, String name, Coordinates coordinates) {

  }

  @Override
  public void updateConditions() {
    // comportamento ESPECÍFICO do Baloon
    // ex: sobe/desce/para baseado no weather
  }

}
