package de.troebs.smol;

public final class FailedDependency extends StatusException {
  private static final int CODE = 424;

  public FailedDependency() {
    super(CODE);
  }
}
