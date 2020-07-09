package de.troebs.smol;

public final class PreconditionRequired extends StatusException {
  private static final int CODE = 428;

  public PreconditionRequired() {
    super(CODE);
  }
}
