package de.troebs.smol;

public final class Gone extends StatusException {
  private static final int CODE = 410;

  public Gone() {
    super(CODE);
  }
}
