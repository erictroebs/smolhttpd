package util;

import java.util.Random;

public final class RandomUtil {
  private static final char[] CHARACTERS = {
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
  };

  public RandomUtil() {
    throw new AssertionError();
  }

  public static boolean getRandomBoolean() {
    return (new Random()).nextBoolean();
  }

  public static boolean[] getRandomBooleans(int length) {
    boolean[] result = new boolean[length];
    for (int i = 0; i < length; i++)
      result[i] = RandomUtil.getRandomBoolean();

    return result;
  }

  public static byte getRandomByte() {
    return RandomUtil.getRandomBytes(1)[0];
  }

  public static byte[] getRandomBytes(int length) {
    byte[] rnd = new byte[length];
    (new Random()).nextBytes(rnd);
    return rnd;
  }

  public static short getRandomShort() {
    byte[] rnd = RandomUtil.getRandomBytes(2);
    return (short) (rnd[0] << 8 | rnd[1]);
  }

  public static short[] getRandomShorts(int length) {
    short[] result = new short[length];
    for (int i = 0; i < length; i++)
      result[i] = RandomUtil.getRandomShort();

    return result;
  }

  public static int getRandomInteger() {
    return (new Random()).nextInt();
  }

  public static int getRandomInteger(int min, int max) {
    return (new Random()).nextInt((max - min) + 1) + min;
  }

  public static int[] getRandomIntegers(int length) {
    int[] result = new int[length];
    for (int i = 0; i < length; i++)
      result[i] = RandomUtil.getRandomInteger();

    return result;
  }

  public static long getRandomLong() {
    return (new Random()).nextLong();
  }

  public static long[] getRandomLongs(int length) {
    long[] result = new long[length];
    for (int i = 0; i < length; i++)
      result[i] = RandomUtil.getRandomLong();

    return result;
  }

  public static float getRandomFloat() {
    return (new Random()).nextFloat();
  }

  public static float[] getRandomFloats(int length) {
    float[] result = new float[length];
    for (int i = 0; i < length; i++)
      result[i] = RandomUtil.getRandomFloat();

    return result;
  }

  public static double getRandomDouble() {
    return (new Random()).nextDouble();
  }

  public static double[] getRandomDoubles(int length) {
    double[] result = new double[length];
    for (int i = 0; i < length; i++)
      result[i] = RandomUtil.getRandomDouble();

    return result;
  }

  public static char getRandomCharacter() {
    return RandomUtil.CHARACTERS[RandomUtil.getRandomInteger(0, RandomUtil.CHARACTERS.length - 1)];
  }

  public static char[] getRandomCharacters(int length) {
    char[] result = new char[length];
    for (int i = 0; i < length; i++)
      result[i] = RandomUtil.getRandomCharacter();

    return result;
  }

  public static String getRandomString() {
    return RandomUtil.getRandomString(RandomUtil.getRandomInteger(8, 64));
  }

  public static String getRandomString(int length) {
    StringBuilder b = new StringBuilder();
    for (int i = 0; i < length; i++)
      b.append(RandomUtil.getRandomCharacter());

    return b.toString();
  }

  public static String[] getRandomStrings(int length) {
    String[] result = new String[length];
    for (int i = 0; i < length; i++)
      result[i] = RandomUtil.getRandomString();

    return result;
  }
}
