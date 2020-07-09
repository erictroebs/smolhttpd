package de.troebs.smol;

public final class UnprocessableEntity extends StatusException {
  private static final int CODE = 422;

  public UnprocessableEntity() {
    super(CODE);
  }
}
