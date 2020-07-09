package de.troebs.smol;

public final class MultipleChoice extends StatusException {
  private static final int CODE = 300;

  public MultipleChoice(String location) {
    super(CODE);
    super.addHeader("Location", location);
  }
}
