package de.troebs.smol;

public final class Unauthorized extends StatusException {
  private static final int CODE = 401;

  public Unauthorized() {
    super(CODE);
  }

  public Unauthorized(String realm) {
    this();
    super.addHeader("WWW-Authenticate", "Basic realm=" + realm);
  }
}
