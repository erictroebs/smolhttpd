package de.troebs.smol;

public final class ProxyAuthenticationRequired extends StatusException {
  private static final int CODE = 407;

  public ProxyAuthenticationRequired() {
    super(CODE);
  }

  public ProxyAuthenticationRequired(String realm) {
    this();
    super.addHeader("Proxy-Authenticate", "Basic realm=" + realm);
  }
}
