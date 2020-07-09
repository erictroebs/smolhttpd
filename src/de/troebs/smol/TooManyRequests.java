package de.troebs.smol;

public final class TooManyRequests extends StatusException {
  private static final int CODE = 429;

  public TooManyRequests() {
    super(CODE);
  }
}
