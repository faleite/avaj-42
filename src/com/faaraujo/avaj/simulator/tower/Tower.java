package com.faaraujo.avaj.simulator;

import java.util.List;

public class Tower {

  private List<Flyable> observers;

  public Tower() {
  }

  public Tower(List<Flyable> observers) {
    this.observers = observers;
  }

  public void register(Flyable p_flyable) {

  }

  public void unregister(Flyable p_flyable) {

  }

  protected void conditionChanged() {

  }

  public List<Flyable> getObservers() {
    return observers;
  }

  public void setObservers(List<Flyable> observers) {
    this.observers = observers;
  }

}
