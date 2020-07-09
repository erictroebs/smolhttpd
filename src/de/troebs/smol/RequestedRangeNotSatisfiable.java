package de.troebs.smol;

public final class RequestedRangeNotSatisfiable extends StatusException {
  private static final int CODE = 416;

  public RequestedRangeNotSatisfiable() {
    super(CODE);
  }
}
