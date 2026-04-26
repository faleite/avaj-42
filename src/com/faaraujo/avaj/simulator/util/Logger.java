package com.faaraujo.avaj.simulator.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

  private static volatile Logger instance;
  private BufferedWriter writer;

  private Logger() {

  }

  public static Logger getInstance() {
    if (instance == null) {
      synchronized (Logger.class) {
        if (instance == null) {
          instance = new Logger();
        }
      }
    }
    return instance;
  }

  public synchronized void log(String message) {
    if (writer == null) {
      throw new IllegalStateException("Logger not initialized. Call init() before logging.");
    }
    try {
      writer.write(String.valueOf(message));
      writer.newLine();
      writer.flush();

    } catch (IOException e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

  public synchronized void init(String filename) throws IOException {
    if (writer == null) {
      writer = new BufferedWriter(new FileWriter(filename));
    }
  }
}
