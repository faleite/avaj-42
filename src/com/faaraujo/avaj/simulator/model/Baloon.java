package com.faaraujo.avaj.simulator.aircraft;

import com.faaraujo.avaj.simulator.Aircraft;
import com.faaraujo.avaj.simulator.Coordinates;

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
