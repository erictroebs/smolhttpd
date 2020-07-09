package util;

public class ArrayUtil {
  public ArrayUtil() {
    throw new AssertionError();
  }

  public static String implode(String[] array, String glue) {
    StringBuilder builder = new StringBuilder();
    for (Object a : array)
      builder.append(a.toString()).append(glue);

    builder.setLength(builder.length() - 1);
    return builder.toString();
  }

  public static String implode(byte[] array, String glue) {
    StringBuilder builder = new StringBuilder();
    for (Object a : array)
      builder.append(a.toString()).append(glue);

    builder.setLength(builder.length() - 1);
    return builder.toString();
  }

  public static String implode(short[] array, String glue) {
    StringBuilder builder = new StringBuilder();
    for (Object a : array)
      builder.append(a.toString()).append(glue);

    builder.setLength(builder.length() - 1);
    return builder.toString();
  }

  public static String implode(int[] array, String glue) {
    StringBuilder builder = new StringBuilder();
    for (Object a : array)
      builder.append(a.toString()).append(glue);

    builder.setLength(builder.length() - 1);
    return builder.toString();
  }

  public static String implode(long[] array, String glue) {
    StringBuilder builder = new StringBuilder();
    for (Object a : array)
      builder.append(a.toString()).append(glue);

    builder.setLength(builder.length() - 1);
    return builder.toString();
  }

  public static String implode(float[] array, String glue) {
    StringBuilder builder = new StringBuilder();
    for (Object a : array)
      builder.append(a.toString()).append(glue);

    builder.setLength(builder.length() - 1);
    return builder.toString();
  }

  public static String implode(double[] array, String glue) {
    StringBuilder builder = new StringBuilder();
    for (Object a : array)
      builder.append(a.toString()).append(glue);

    builder.setLength(builder.length() - 1);
    return builder.toString();
  }

  public static String implode(boolean[] array, String glue) {
    StringBuilder builder = new StringBuilder();
    for (Object a : array)
      builder.append(a.toString()).append(glue);

    builder.setLength(builder.length() - 1);
    return builder.toString();
  }

  public static String implode(char[] array, String glue) {
    StringBuilder builder = new StringBuilder();
    for (Object a : array)
      builder.append(a.toString()).append(glue);

    builder.setLength(builder.length() - 1);
    return builder.toString();
  }
}
