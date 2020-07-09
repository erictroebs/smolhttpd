package de.troebs.smol;

public final class LengthRequired extends StatusException {
  private static final int CODE = 411;

  public LengthRequired() {
    super(CODE);
  }
}
