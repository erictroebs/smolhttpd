package de.troebs.smol;

public final class Conflict extends StatusException {
  private static final int CODE = 409;

  public Conflict() {
    super(CODE);
  }
}
