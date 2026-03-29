package com.faaraujo.avaj.simulator.aircraft;

import com.faaraujo.avaj.simulator.Aircraft;
import com.faaraujo.avaj.simulator.Coordinates;

public class JetPlane extends Aircraft {

  public JetPlane() {

  }

  public JetPlane(Long id, String name, Coordinates coordinates) {

  }

  @Override
  public void updateConditions() {
    // comportamento ESPECÍFICO do JetPlane
    // ex: sobe/desce/para baseado no weather
  }

}
