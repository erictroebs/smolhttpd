package de.troebs.smol;

public final class PermanentRedirect extends StatusException {
  private static final int CODE = 308;

  public PermanentRedirect(String location) {
    super(CODE);
    super.addHeader("Location", location);
  }
}
