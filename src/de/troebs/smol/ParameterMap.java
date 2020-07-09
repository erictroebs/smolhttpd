package de.troebs.smol;

import com.sun.net.httpserver.Headers;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ParameterMap {
  private final Map<String, String> values;


  public ParameterMap() {
    this.values = new HashMap<>();
  }

  public ParameterMap(List<HandlerList.NameValuePair> values) {
    this();

    for (HandlerList.NameValuePair n : values) {
      this.add(n.name, n.value);
    }
  }

  public ParameterMap(Headers headers) {
    this();

    for (String key : headers.keySet()) {
      this.add(key, headers.getFirst(key));
    }
  }

  public ParameterMap(InputStream body) throws IOException {
    this();

    StringBuilder builder = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(body))) {
      String line;
      while ((line = reader.readLine()) != null) {
        builder.append(line);
      }
    }

    for (String p0 : builder.toString().split("&")) {
      this.add(p0.split("="));
    }
  }


  public void add(String key, String value) {
    try {
      this.values.put(
        key,
        URLDecoder.decode(value, "UTF-8")
      );
    }
    catch (UnsupportedEncodingException ignored) {
    }
  }

  public void add(String key, List<String> value) {
    if (value.size() > 0)
      this.add(key, value.get(0));
  }

  public void add(String[] kv) {
    if (kv.length > 1)
      this.add(kv[0], kv[1]);
  }


  public class ParseException extends Exception {
    public ParseException(Exception e) {
      this.addSuppressed(e);
    }

    public ParseException() {
    }
  }

  public String toString(String key) {
    return this.values.get(key);
  }

  public String toString(String key, boolean throwParseExceptionIfNull) throws ParseException {
    String value = this.toString(key);
    if (throwParseExceptionIfNull && value == null)
      throw new ParseException();

    return value;
  }

  public byte toByte(String key) throws ParseException {
    try {
      return Byte.parseByte(this.toString(key));
    }
    catch (NumberFormatException e) {
      throw new ParseException(e);
    }
  }

  public short toShort(String key) throws ParseException {
    try {
      return Short.parseShort(this.toString(key));
    }
    catch (NumberFormatException e) {
      throw new ParseException(e);
    }
  }

  public int toInt(String key) throws ParseException {
    try {
      return Integer.parseInt(this.toString(key));
    }
    catch (NumberFormatException e) {
      throw new ParseException(e);
    }
  }

  public int toInteger(String key) throws ParseException {
    return this.toInt(key);
  }

  public long toLong(String key) throws ParseException {
    try {
      return Long.parseLong(this.toString(key));
    }
    catch (NumberFormatException e) {
      throw new ParseException(e);
    }
  }

  public float toFloat(String key) throws ParseException {
    try {
      return Float.parseFloat(this.toString(key));
    }
    catch (NumberFormatException e) {
      throw new ParseException(e);
    }
  }

  public double toDouble(String key) throws ParseException {
    try {
      return Double.parseDouble(this.toString(key));
    }
    catch (NumberFormatException e) {
      throw new ParseException(e);
    }
  }

  public boolean toBoolean(String key) throws ParseException {
    try {
      return this.toString(key).equalsIgnoreCase("true");
    }
    catch (NullPointerException e) {
      throw new ParseException(e);
    }
  }

  public char toChar(String key) throws ParseException {
    try {
      return this.toString(key).charAt(0);
    }
    catch (StringIndexOutOfBoundsException e) {
      throw new ParseException(e);
    }
  }

  public String[] toStringArray(String key) {
    return this.toString(key).split(",");
  }

  public byte[] toByteArray(String key) throws ParseException {
    String[] str = this.toStringArray(key);
    byte[] b = new byte[str.length];

    for (int i = 0; i < str.length; i++)
      try {
        b[i] = Byte.parseByte(str[i]);
      }
      catch (NumberFormatException e) {
        throw new ParseException(e);
      }

    return b;
  }

  public short[] toShortArray(String key) throws ParseException {
    String[] str = this.toStringArray(key);
    short[] s = new short[str.length];

    for (int i = 0; i < str.length; i++)
      try {
        s[i] = Short.parseShort(str[i]);
      }
      catch (NumberFormatException e) {
        throw new ParseException(e);
      }

    return s;
  }

  public int[] toIntArray(String key) throws ParseException {
    String[] str = this.toStringArray(key);
    int[] r = new int[str.length];

    for (int i = 0; i < str.length; i++)
      try {
        r[i] = Integer.parseInt(str[i]);
      }
      catch (NumberFormatException e) {
        throw new ParseException(e);
      }

    return r;
  }

  public int[] toIntegerArray(String key) throws ParseException {
    return this.toIntArray(key);
  }

  public long[] toLongArray(String key) throws ParseException {
    String[] str = this.toStringArray(key);
    long[] l = new long[str.length];

    for (int i = 0; i < str.length; i++)
      try {
        l[i] = Long.parseLong(str[i]);
      }
      catch (NumberFormatException e) {
        throw new ParseException(e);
      }

    return l;
  }

  public float[] toFloatArray(String key) throws ParseException {
    String[] str = this.toStringArray(key);
    float[] f = new float[str.length];

    for (int i = 0; i < str.length; i++)
      try {
        f[i] = Float.parseFloat(str[i]);
      }
      catch (NumberFormatException e) {
        throw new ParseException(e);
      }

    return f;
  }

  public double[] toDoubleArray(String key) throws ParseException {
    String[] str = this.toStringArray(key);
    double[] d = new double[str.length];

    for (int i = 0; i < str.length; i++)
      try {
        d[i] = Double.parseDouble(str[i]);
      }
      catch (NumberFormatException e) {
        throw new ParseException(e);
      }

    return d;
  }

  public boolean[] toBooleanArray(String key) throws ParseException {
    String[] str = this.toStringArray(key);
    boolean[] b = new boolean[str.length];

    for (int i = 0; i < str.length; i++)
      try {
        b[i] = str[i].equalsIgnoreCase("true");
      }
      catch (NumberFormatException e) {
        throw new ParseException(e);
      }

    return b;
  }

  public char[] toCharArray(String key) throws ParseException {
    String[] str = this.toStringArray(key);
    char[] c = new char[str.length];

    for (int i = 0; i < str.length; i++)
      try {
        c[i] = str[i].charAt(0);
      }
      catch (NumberFormatException e) {
        throw new ParseException(e);
      }

    return c;
  }


  public void addTo(Object[] array, int index, String key, Class type) throws ParseException {
    try {
      if (type == String.class)
        array[index] = this.toString(key, true);
      else if (type == byte.class)
        array[index] = this.toByte(key);
      else if (type == short.class)
        array[index] = this.toShort(key);
      else if (type == int.class)
        array[index] = this.toInt(key);
      else if (type == long.class)
        array[index] = this.toLong(key);
      else if (type == float.class)
        array[index] = this.toFloat(key);
      else if (type == double.class)
        array[index] = this.toDouble(key);
      else if (type == boolean.class)
        array[index] = this.toBoolean(key);
      else if (type == char.class)
        array[index] = this.toChar(key);
      else if (type == String[].class)
        array[index] = this.toStringArray(key);
      else if (type == byte[].class)
        array[index] = this.toByteArray(key);
      else if (type == short[].class)
        array[index] = this.toShortArray(key);
      else if (type == int[].class)
        array[index] = this.toIntArray(key);
      else if (type == long[].class)
        array[index] = this.toLongArray(key);
      else if (type == float[].class)
        array[index] = this.toFloatArray(key);
      else if (type == double[].class)
        array[index] = this.toDoubleArray(key);
      else if (type == boolean[].class)
        array[index] = this.toBooleanArray(key);
      else if (type == char[].class)
        array[index] = this.toCharArray(key);
      else
        throw new NullPointerException();
    }
    catch (NullPointerException e) {
      throw new ParseException(e);
    }
  }
}
