package com.faaraujo.avaj.simulator.model;

import com.faaraujo.avaj.simulator.Logger;

public class Helicopter extends Aircraft {

  public Helicopter(long id, String name, Coordinates coordinates) {
    super(id, name, coordinates);
  }

  @Override
  public void updateConditions() {
    String weather = weatherTower.getWeather(this.coordinates);

    switch (weather) {
      case "SUN":
        this.coordinates = coordinates.add(10, 0, 2);

        // TODO: repelace with logger
        Logger.getInstance().log("Helicopter#" + this.name + "(" + this.id + "):" +
            " Spinning fast enough to chop the sunlight into pieces.");
        break;

      case "RAIN":
        this.coordinates = coordinates.add(5, 0, 0);

        // TODO: repelace with logger
        Logger.getInstance().log("Helicopter#" + this.name + "(" + this.id + "):" +
            " It's like flying inside a giant, noisy washing machine.");
        break;

      case "FOG":
        this.coordinates = coordinates.add(1, 0, 0);

        // TODO: repelace with logger
        Logger.getInstance().log("Helicopter#" + this.name + "(" + this.id + "):" +
            " Static everywhere; I hope that tree isn't where I think it is.");
        break;

      case "SNOW":
        this.coordinates = coordinates.add(0, 0, -12);

        // TODO: repelace with logger
        Logger.getInstance().log("Helicopter#" + this.name + "(" + this.id + "):" +
            " Note to self: Being a giant ceiling fan in a blizzard is a bad idea.");
    }

    if (this.coordinates.getHeight() == 0) {
      // TODO: repelace with logger
      Logger.getInstance().log("Helicopter#" + this.name + "(" + this.id + "): Hold on tight, we're landing.");

      this.weatherTower.unregister(this);
    }
  }
}
