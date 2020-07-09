package de.troebs.smol;

public final class Forbidden extends StatusException {
  private static final int CODE = 403;

  public Forbidden() {
    super(CODE);
  }
}
