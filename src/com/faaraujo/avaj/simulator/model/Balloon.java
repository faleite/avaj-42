package com.faaraujo.avaj.simulator.model;

import com.faaraujo.avaj.simulator.util.Logger;

public class Balloon extends Aircraft {

  public Balloon(long id, String name, Coordinates coordinates) {
    super(id, name, coordinates);
  }

  @Override
  public void updateConditions() {
    String weather = weatherTower.getWeather(this.coordinates);

    switch (weather) {
      case "SUN":
        this.coordinates = coordinates.add(2, 0, 4);

        // TODO: repelace with logger
        Logger.getInstance().log("Balloon#" + this.name + "(" + this.id + "):" +
            " Perfect day to do absolutely nothing but float.");
        break;

      case "RAIN":
        this.coordinates = coordinates.add(0, 0, -5);

        // TODO: repelace with logger
        Logger.getInstance().log("Balloon#" + this.name + "(" + this.id + "):" +
            " I'm basically a giant, colorful, leaking umbrella now.");
        break;

      case "FOG":
        this.coordinates = coordinates.add(0, 0, -3);

        // TODO: repelace with logger
        Logger.getInstance().log("Balloon#" + this.name + "(" + this.id + "):" +
            " I can't see the ground, but at least the birds can't see me.");
        break;

      case "SNOW":
        this.coordinates = coordinates.add(0, 0, -15);

        // TODO: repelace with logger
        Logger.getInstance().log("Balloon#" + this.name + "(" + this.id + "):" +
            " Help, I'm becoming a giant frozen blueberry!");
    }

    if (this.coordinates.getHeight() == 0) {
      // TODO: repelace with logger
      Logger.getInstance().log("Balloon#" + this.name + "(" + this.id + "): Hold on tight, we're landing.");

      this.weatherTower.unregister(this);
    }
  }
}
