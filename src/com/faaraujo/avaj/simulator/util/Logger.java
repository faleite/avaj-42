package com.faaraujo.avaj.simulator.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

  private static Logger instance;
  private BufferedWriter writer;

  private Logger() {

  }

  public static Logger getInstance() {

    if (instance == null) {
      instance = new Logger();
    }

    return instance;
  }

  public void log(String message) {
    try {

      writer.write(message);
      writer.newLine();
      writer.flush();

    } catch (IOException e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

  public void init(String filename) throws IOException {
    writer = new BufferedWriter(new FileWriter(filename));
  }
}
