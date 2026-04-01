package com.faaraujo.avaj.simulator.tower;

import com.faaraujo.avaj.simulator.model.Flyable;

import java.util.ArrayList;
import java.util.List;

public class Tower {

  private List<Flyable> observers = new ArrayList<>();

  public void register(Flyable flyable) {
    observers.add(flyable);
    // TODO: create log message
    System.out.println("Tower says: " + flyable + " registered to weather tower.");
  }

  public void unregister(Flyable flyable) {
    observers.remove(flyable);
    // TODO: create log message
    System.out.println("Tower says: " + flyable + " unregistered from weather tower.");
  }

  protected void conditionChanged() {

    for (Flyable flyable : new ArrayList<>(observers)) {
      flyable.updateConditions();
    }
  }
}
