package com.faaraujo.avaj.simulator.model;

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
        System.out.println("JetPlane#" + this.name + "(" + this.id + "):" +
            " SPECIFIC_MESSAGE");
        break;

      case "RAIN":
        this.coordinates = coordinates.add(0, 5, 0);

        // TODO: repelace with logger
        System.out.println("JetPlane#" + this.name + "(" + this.id + "):" +
            " SPECIFIC_MESSAGE");
        break;

      case "FOG":
        this.coordinates = coordinates.add(0, 1, 0);

        // TODO: repelace with logger
        System.out.println("JetPlane#" + this.name + "(" + this.id + "):" +
            " SPECIFIC_MESSAGE");
        break;

      case "SNOW":
        this.coordinates = coordinates.add(0, 0, -7);

        // TODO: repelace with logger
        System.out.println("JetPlane#" + this.name + "(" + this.id + "):" +
            " SPECIFIC_MESSAGE");
    }

    if (this.coordinates.getHeight() == 0) {
      // TODO: repelace with logger
      System.out.println("JetPlane#" + this.name + "(" + this.id + "): landing.");

      this.weatherTower.unregister(this);
    }
  }
}
