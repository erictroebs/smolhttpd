package de.troebs.smol;

public final class Created extends StatusException {
  private static final int CODE = 201;
  final Object body;

  public Created(Object body, String location) {
    super(CODE);
    super.addHeader("Location", location);
    this.body = body;
  }

  public Created(Object body) {
    super(CODE);
    this.body = body;
  }

  public Created(String location) {
    this(null, location);
  }

  public Created() {
    super(CODE);
    this.body = null;
  }
}
