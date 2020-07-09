package de.troebs.smol;

public final class MethodNotAllowed extends StatusException {
  private static final int CODE = 405;

  public static final String GET = "GET";
  public static final String POST = "POST";
  public static final String PUT = "PUT";
  public static final String PATCH = "PATCH";
  public static final String DELETE = "DELETE";

  public MethodNotAllowed(String... allowed) {
    super(CODE);

    StringBuilder builder = new StringBuilder();
    for (String a : allowed)
      builder.append(a).append(", ");
    builder.setLength(builder.length() - 2);

    super.addHeader("Allow", builder.toString());
  }
}
