package de.troebs.smol;

public final class NotModified extends StatusException {
  private static final int CODE = 304;

  public NotModified() {
    super(CODE);
  }
}
