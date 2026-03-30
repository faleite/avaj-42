package com.faaraujo.avaj.simulator.model;

public class Balloon extends Aircraft {

  public Balloon(long id, String name, Coordinates coordinates) {
    super(id, name, coordinates);
  }

  @Override
  public void updateConditions() {
    // comportamento ESPECÍFICO do Baloon
    // ex: sobe/desce/para baseado no weather
    String weather = weatherTower.getWeather(this.coordinates);

    switch (weather) {
      case "SUN":
        this.coordinates = coordinates.add(2, 0, 4);

        // TODO: repelace with logger
        System.out.println("Balloon#" + this.name + "(" + this.id + "):" +
            " SPECIFIC_MESSAGE");
        break;

      case "RAIN":
        this.coordinates = coordinates.add(0, 0, -5);

        // TODO: repelace with logger
        System.out.println("Balloon#" + this.name + "(" + this.id + "):" +
            " SPECIFIC_MESSAGE");
        break;

      case "FOG":
        this.coordinates = coordinates.add(0, 0, -3);

        // TODO: repelace with logger
        System.out.println("Balloon#" + this.name + "(" + this.id + "):" +
            " SPECIFIC_MESSAGE");
        break;

      case "SNOW":
        this.coordinates = coordinates.add(0, 0, -15);

        // TODO: repelace with logger
        System.out.println("Balloon#" + this.name + "(" + this.id + "):" +
            " SPECIFIC_MESSAGE");
    }

    if (this.coordinates.getHeight() == 0) {
      // TODO: repelace with logger
      System.out.println("Balloon#" + this.name + "(" + this.id + "): landing.");

      this.weatherTower.unregister(this);
    }
  }
}
