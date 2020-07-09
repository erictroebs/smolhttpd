package de.troebs.smol;

public final class Locked extends StatusException {
  private static final int CODE = 423;

  public Locked() {
    super(CODE);
  }
}
