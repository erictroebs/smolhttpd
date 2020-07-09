package util;

public class PortUtil {
  private static final int DEFAULT_PORT = 8080;
  private static int offset = -1;

  public static int r() {
    return 8080 + PortUtil.offset;
  }

  public static int p() {
    PortUtil.offset = (PortUtil.offset + 1) % 5000;
    return PortUtil.r();
  }

  public static String q() {
    return String.valueOf(PortUtil.r());
  }
}
