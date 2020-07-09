package de.troebs.smol;

public final class NoContent extends StatusException {
  private static final int CODE = 204;

  public NoContent() {
    super(CODE);
  }
}
