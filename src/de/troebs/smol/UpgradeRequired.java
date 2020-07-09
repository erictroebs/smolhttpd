package de.troebs.smol;

public final class UpgradeRequired extends StatusException {
  private static final int CODE = 426;

  public UpgradeRequired() {
    super(CODE);
  }
}
