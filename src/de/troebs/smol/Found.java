package de.troebs.smol;

public final class Found extends StatusException {
  private static final int CODE = 302;

  public Found(String location) {
    super(CODE);
    super.addHeader("Location", location);
  }
}
