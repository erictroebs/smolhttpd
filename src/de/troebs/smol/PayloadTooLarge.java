package de.troebs.smol;

public final class PayloadTooLarge extends StatusException {
  private static final int CODE = 413;

  public PayloadTooLarge() {
    super(CODE);
  }

  public PayloadTooLarge(int retry) {
    this();
    super.addHeader("Retry-After", String.valueOf(retry));
  }
}
