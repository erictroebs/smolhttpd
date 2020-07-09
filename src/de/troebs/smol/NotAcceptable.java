package de.troebs.smol;

public final class NotAcceptable extends StatusException {
  private static final int CODE = 406;

  public NotAcceptable() {
    super(CODE);
  }
}
