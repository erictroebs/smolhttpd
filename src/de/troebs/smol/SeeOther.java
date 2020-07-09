package de.troebs.smol;

public final class SeeOther extends StatusException {
  private static final int CODE = 303;

  public SeeOther(String location) {
    super(CODE);
    super.addHeader("Location", location);
  }
}
