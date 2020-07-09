package de.troebs.smol;

public final class NotFound extends StatusException {
  private static final int CODE = 404;

  public NotFound() {
    super(CODE);
  }
}
