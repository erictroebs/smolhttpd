package de.troebs.smol;

public final class RequestHeaderFieldsTooLarge extends StatusException {
  private static final int CODE = 431;

  public RequestHeaderFieldsTooLarge() {
    super(CODE);
  }
}
