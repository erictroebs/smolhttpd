package de.troebs.smol;

public final class BadRequest extends StatusException {
  private static final int CODE = 400;

  public BadRequest() {
    super(CODE);
  }
}
