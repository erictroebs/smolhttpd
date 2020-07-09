package de.troebs.smol;

public final class MisdirectedRequest extends StatusException {
  private static final int CODE = 421;

  public MisdirectedRequest() {
    super(CODE);
  }
}
