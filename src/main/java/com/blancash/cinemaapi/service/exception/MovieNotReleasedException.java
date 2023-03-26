package com.blancash.cinemaapi.service.exception;

public class MovieNotReleasedException extends Exception {

  public MovieNotReleasedException(String message) {
    super(message);
  }
}
