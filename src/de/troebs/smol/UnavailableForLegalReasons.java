package de.troebs.smol;

public final class UnavailableForLegalReasons extends StatusException {
  private static final int CODE = 451;

  public UnavailableForLegalReasons() {
    super(CODE);
  }
}
