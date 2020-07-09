package de.troebs.smol;

public final class UnsupportedMediaType extends StatusException {
  private static final int CODE = 415;

  public UnsupportedMediaType() {
    super(CODE);
  }
}
