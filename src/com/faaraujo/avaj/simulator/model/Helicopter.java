package com.faaraujo.avaj.simulator.model;

public class Helicopter extends Aircraft {

  public Helicopter() {

  }

  public Helicopter(Long id, String name, Coordinates coordinates) {

  }

  @Override
  public void updateConditions() {
    // comportamento ESPECÍFICO do Helicopter
    // ex: sobe/desce/para baseado no weather
  }

}
