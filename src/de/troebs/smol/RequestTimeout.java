package de.troebs.smol;

public final class RequestTimeout extends StatusException {
  private static final int CODE = 408;

  public RequestTimeout() {
    super(CODE);
  }
}
