package com.faaraujo.avaj.simulator.model;

import com.faaraujo.avaj.simulator.Logger;

public class JetPlane extends Aircraft {

  public JetPlane(long id, String name, Coordinates coordinates) {
    super(id, name, coordinates);
  }

  @Override
  public void updateConditions() {
    String weather = weatherTower.getWeather(this.coordinates);

    switch (weather) {
      case "SUN":
        this.coordinates = coordinates.add(0, 10, 2);

        // TODO: repelace with logger
        Logger.getInstance().log("JetPlane#" + this.name + "(" + this.id + "):" +
            " Clear skies! Time to break the sound barrier and some windows.");
        break;

      case "RAIN":
        this.coordinates = coordinates.add(0, 5, 0);

        // TODO: repelace with logger
        Logger.getInstance().log("JetPlane#" + this.name + "(" + this.id + "):" +
            " Wipers on max, but I'm still faster than the clouds.");
        break;

      case "FOG":
        this.coordinates = coordinates.add(0, 1, 0);

        // TODO: repelace with logger
        Logger.getInstance().log("JetPlane#" + this.name + "(" + this.id + "):" +
            " Flying blind, but hey, that's what the expensive buttons are for.");
        break;

      case "SNOW":
        this.coordinates = coordinates.add(0, 0, -7);

        // TODO: repelace with logger
        Logger.getInstance().log("JetPlane#" + this.name + "(" + this.id + "):" +
            " Engines are hot, but my wings are turning into popsicles.");
    }

    if (this.coordinates.getHeight() == 0) {
      // TODO: repelace with logger
      Logger.getInstance().log("JetPlane#" + this.name + "(" + this.id + "): Hold on tight, we're landing.");

      this.weatherTower.unregister(this);
    }
  }
}
