package de.troebs.smol;

public final class PreconditionFailed extends StatusException {
  private static final int CODE = 412;

  public PreconditionFailed() {
    super(CODE);
  }
}
