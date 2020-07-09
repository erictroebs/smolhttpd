package de.troebs.smol;

public final class URITooLong extends StatusException {
  private static final int CODE = 414;

  public URITooLong() {
    super(CODE);
  }
}
