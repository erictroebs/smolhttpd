package de.troebs.smol;

public final class ExpectationFailed extends StatusException {
  private static final int CODE = 417;

  public ExpectationFailed() {
    super(CODE);
  }
}
