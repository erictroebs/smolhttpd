package de.troebs.smol;

public final class MovedPermanently extends StatusException {
  private static final int CODE = 301;

  public MovedPermanently(String location) {
    super(CODE);
    super.addHeader("Location", location);
  }
}
