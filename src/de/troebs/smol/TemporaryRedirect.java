package de.troebs.smol;

public final class TemporaryRedirect extends StatusException {
  private static final int CODE = 307;

  public TemporaryRedirect(String location) {
    super(CODE);
    super.addHeader("Location", location);
  }
}
