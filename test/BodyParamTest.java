import de.troebs.smol.BodyParam;
import de.troebs.smol.Post;
import de.troebs.smol.SmolHTTPd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.ArrayUtil;
import util.HttpUtil;
import util.PortUtil;
import util.RandomUtil;

import java.util.HashMap;
import java.util.Map;

public class BodyParamTest {
  @Test
  public void testStringParameter() throws Exception {
    final String value = RandomUtil.getRandomString();
    final Map<String, String> data = new HashMap<>();
    data.put("string", value);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final StringHandler handler = new StringHandler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/", data);
      Assertions.assertTrue(handler.used);
      Assertions.assertEquals(value, handler.get);
    }
  }

  @Test
  public void testByteParameter() throws Exception {
    final byte value = RandomUtil.getRandomByte();
    final Map<String, String> data = new HashMap<>();
    data.put("byte", String.valueOf(value));

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final ByteHandler handler = new ByteHandler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/", data);
      Assertions.assertTrue(handler.used);
      Assertions.assertEquals(value, handler.get);
    }
  }

  @Test
  public void testShortParameter() throws Exception {
    final short value = RandomUtil.getRandomShort();
    final Map<String, String> data = new HashMap<>();
    data.put("short", String.valueOf(value));

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final ShortHandler handler = new ShortHandler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/", data);
      Assertions.assertTrue(handler.used);
      Assertions.assertEquals(value, handler.get);
    }
  }

  @Test
  public void testIntParameter() throws Exception {
    final int value = RandomUtil.getRandomInteger();
    final Map<String, String> data = new HashMap<>();
    data.put("int", String.valueOf(value));

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final IntHandler handler = new IntHandler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/", data);
      Assertions.assertTrue(handler.used);
      Assertions.assertEquals(value, handler.get);
    }
  }

  @Test
  public void testLongParameter() throws Exception {
    final long value = RandomUtil.getRandomLong();
    final Map<String, String> data = new HashMap<>();
    data.put("long", String.valueOf(value));

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final LongHandler handler = new LongHandler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/", data);
      Assertions.assertTrue(handler.used);
      Assertions.assertEquals(value, handler.get);
    }
  }

  @Test
  public void testFloatParameter() throws Exception {
    final float value = RandomUtil.getRandomFloat();
    final Map<String, String> data = new HashMap<>();
    data.put("float", String.valueOf(value));

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final FloatHandler handler = new FloatHandler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/", data);
      Assertions.assertTrue(handler.used);
      Assertions.assertEquals(value, handler.get);
    }
  }

  @Test
  public void testDoubleParameter() throws Exception {
    final double value = RandomUtil.getRandomDouble();
    final Map<String, String> data = new HashMap<>();
    data.put("double", String.valueOf(value));

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final DoubleHandler handler = new DoubleHandler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/", data);
      Assertions.assertTrue(handler.used);
      Assertions.assertEquals(value, handler.get);
    }
  }

  @Test
  public void testBooleanParameter() throws Exception {
    final boolean value = RandomUtil.getRandomBoolean();
    final Map<String, String> data = new HashMap<>();
    data.put("boolean", String.valueOf(value));

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final BooleanHandler handler = new BooleanHandler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/", data);
      Assertions.assertTrue(handler.used);
      Assertions.assertEquals(value, handler.get);
    }
  }

  @Test
  public void testCharParameter() throws Exception {
    final char value = RandomUtil.getRandomCharacter();
    final Map<String, String> data = new HashMap<>();
    data.put("char", String.valueOf(value));

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final CharHandler handler = new CharHandler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/", data);
      Assertions.assertTrue(handler.used);
      Assertions.assertEquals(value, handler.get);
    }
  }

  @Test
  public void testStringArrayParameter() throws Exception {
    final String[] value = RandomUtil.getRandomStrings(5);
    final Map<String, String> data = new HashMap<>();
    data.put("string", ArrayUtil.implode(value, ","));

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final StringArrayHandler handler = new StringArrayHandler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/", data);
      Assertions.assertTrue(handler.used);
      Assertions.assertArrayEquals(value, handler.get);
    }
  }

  @Test
  public void testByteArrayParameter() throws Exception {
    final byte[] value = RandomUtil.getRandomBytes(5);
    final Map<String, String> data = new HashMap<>();
    data.put("byte", ArrayUtil.implode(value, ","));

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final ByteArrayHandler handler = new ByteArrayHandler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/", data);
      Assertions.assertTrue(handler.used);
      Assertions.assertArrayEquals(value, handler.get);
    }
  }

  @Test
  public void testShortArrayParameter() throws Exception {
    final short[] value = RandomUtil.getRandomShorts(5);
    final Map<String, String> data = new HashMap<>();
    data.put("short", ArrayUtil.implode(value, ","));

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final ShortArrayHandler handler = new ShortArrayHandler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/", data);
      Assertions.assertTrue(handler.used);
      Assertions.assertArrayEquals(value, handler.get);
    }
  }

  @Test
  public void testIntArrayParameter() throws Exception {
    final int[] value = RandomUtil.getRandomIntegers(5);
    final Map<String, String> data = new HashMap<>();
    data.put("int", ArrayUtil.implode(value, ","));

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final IntArrayHandler handler = new IntArrayHandler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/", data);
      Assertions.assertTrue(handler.used);
      Assertions.assertArrayEquals(value, handler.get);
    }
  }

  @Test
  public void testLongArrayParameter() throws Exception {
    final long[] value = RandomUtil.getRandomLongs(5);
    final Map<String, String> data = new HashMap<>();
    data.put("long", ArrayUtil.implode(value, ","));

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final LongArrayHandler handler = new LongArrayHandler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/", data);
      Assertions.assertTrue(handler.used);
      Assertions.assertArrayEquals(value, handler.get);
    }
  }

  @Test
  public void testFloatArrayParameter() throws Exception {
    final float[] value = RandomUtil.getRandomFloats(5);
    final Map<String, String> data = new HashMap<>();
    data.put("float", ArrayUtil.implode(value, ","));

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final FloatArrayHandler handler = new FloatArrayHandler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/", data);
      Assertions.assertTrue(handler.used);
      Assertions.assertArrayEquals(value, handler.get);
    }
  }

  @Test
  public void testDoubleArrayParameter() throws Exception {
    final double[] value = RandomUtil.getRandomDoubles(5);
    final Map<String, String> data = new HashMap<>();
    data.put("double", ArrayUtil.implode(value, ","));

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final DoubleArrayHandler handler = new DoubleArrayHandler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/", data);
      Assertions.assertTrue(handler.used);
      Assertions.assertArrayEquals(value, handler.get);
    }
  }

  @Test
  public void testBooleanArrayParameter() throws Exception {
    final boolean[] value = RandomUtil.getRandomBooleans(5);
    final Map<String, String> data = new HashMap<>();
    data.put("boolean", ArrayUtil.implode(value, ","));

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final BooleanArrayHandler handler = new BooleanArrayHandler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/", data);
      Assertions.assertTrue(handler.used);
      Assertions.assertArrayEquals(value, handler.get);
    }
  }

  @Test
  public void testCharArrayParameter() throws Exception {
    final char[] value = RandomUtil.getRandomCharacters(5);
    final Map<String, String> data = new HashMap<>();
    data.put("char", ArrayUtil.implode(value, ","));

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final CharArrayHandler handler = new CharArrayHandler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/", data);
      Assertions.assertTrue(handler.used);
      Assertions.assertArrayEquals(value, handler.get);
    }
  }

  @Test
  public void testArrayOverlappingPrimitive() throws Exception {
    final int intValue = RandomUtil.getRandomInteger();
    final Map<String, String> dataValue = new HashMap<>();
    dataValue.put("int", String.valueOf(intValue));
    final int[] intArrayValue = RandomUtil.getRandomIntegers(5);
    final Map<String, String> dataArrayValue = new HashMap<>();
    dataArrayValue.put("int", ArrayUtil.implode(intArrayValue, ","));

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      IntArrayHandler handler0 = new IntArrayHandler();
      IntHandler handler1 = new IntHandler();
      IntHandler handler2 = new IntHandler();
      IntArrayHandler handler3 = new IntArrayHandler();
      IntArrayHandler handler4 = new IntArrayHandler();
      IntHandler handler5 = new IntHandler();
      IntHandler handler6 = new IntHandler();
      IntArrayHandler handler7 = new IntArrayHandler();

      http.register("/a", handler0);
      http.register("/a", handler1);
      http.register("/b", handler2);
      http.register("/b", handler3);
      http.register("/c", handler4);
      http.register("/c", handler5);
      http.register("/d", handler6);
      http.register("/d", handler7);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/a", dataValue);
      Assertions.assertTrue(handler0.used);
      Assertions.assertFalse(handler1.used);
      Assertions.assertArrayEquals(new int[] {intValue}, handler0.get);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/b", dataValue);
      Assertions.assertTrue(handler2.used);
      Assertions.assertFalse(handler3.used);
      Assertions.assertEquals(intValue, handler2.get);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/c", dataArrayValue);
      Assertions.assertTrue(handler4.used);
      Assertions.assertFalse(handler5.used);
      Assertions.assertArrayEquals(intArrayValue, handler4.get);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/d", dataArrayValue);
      Assertions.assertFalse(handler6.used);
      Assertions.assertTrue(handler7.used);
      Assertions.assertArrayEquals(intArrayValue, handler7.get);
    }
  }


  public static class StringHandler {
    public boolean used = false;
    public String get;

    @Post
    public void get(@BodyParam("string") String param) {
      this.used = true;
      this.get = param;
    }
  }

  public static class ByteHandler {
    public boolean used = false;
    public byte get;

    @Post
    public void get(@BodyParam("byte") byte param) {
      this.used = true;
      this.get = param;
    }
  }

  public static class ShortHandler {
    public boolean used = false;
    public short get;

    @Post
    public void get(@BodyParam("short") short param) {
      this.used = true;
      this.get = param;
    }
  }

  public static class IntHandler {
    public boolean used = false;
    public int get;

    @Post
    public void get(@BodyParam("int") int param) {
      this.used = true;
      this.get = param;
    }
  }

  public static class LongHandler {
    public boolean used = false;
    public long get;

    @Post
    public void get(@BodyParam("long") long param) {
      this.used = true;
      this.get = param;
    }
  }

  public static class FloatHandler {
    public boolean used = false;
    public float get;

    @Post
    public void get(@BodyParam("float") float param) {
      this.used = true;
      this.get = param;
    }
  }

  public static class DoubleHandler {
    public boolean used = false;
    public double get;

    @Post
    public void get(@BodyParam("double") double param) {
      this.used = true;
      this.get = param;
    }
  }

  public static class BooleanHandler {
    public boolean used = false;
    public boolean get;

    @Post
    public void get(@BodyParam("boolean") boolean param) {
      this.used = true;
      this.get = param;
    }
  }

  public static class CharHandler {
    public boolean used = false;
    public char get;

    @Post
    public void get(@BodyParam("char") char param) {
      this.used = true;
      this.get = param;
    }
  }

  public static class StringArrayHandler {
    public boolean used = false;
    public String[] get;

    @Post
    public void get(@BodyParam("string") String[] param) {
      this.used = true;
      this.get = param;
    }
  }

  public static class ByteArrayHandler {
    public boolean used = false;
    public byte[] get;

    @Post
    public void get(@BodyParam("byte") byte[] param) {
      this.used = true;
      this.get = param;
    }
  }

  public static class ShortArrayHandler {
    public boolean used = false;
    public short[] get;

    @Post
    public void get(@BodyParam("short") short[] param) {
      this.used = true;
      this.get = param;
    }
  }

  public static class IntArrayHandler {
    public boolean used = false;
    public int[] get;

    @Post
    public void get(@BodyParam("int") int[] param) {
      this.used = true;
      this.get = param;
    }
  }

  public static class LongArrayHandler {
    public boolean used = false;
    public long[] get;

    @Post
    public void get(@BodyParam("long") long[] param) {
      this.used = true;
      this.get = param;
    }
  }

  public static class FloatArrayHandler {
    public boolean used = false;
    public float[] get;

    @Post
    public void get(@BodyParam("float") float[] param) {
      this.used = true;
      this.get = param;
    }
  }

  public static class DoubleArrayHandler {
    public boolean used = false;
    public double[] get;

    @Post
    public void get(@BodyParam("double") double[] param) {
      this.used = true;
      this.get = param;
    }
  }

  public static class BooleanArrayHandler {
    public boolean used = false;
    public boolean[] get;

    @Post
    public void get(@BodyParam("boolean") boolean[] param) {
      this.used = true;
      this.get = param;
    }
  }

  public static class CharArrayHandler {
    public boolean used = false;
    public char[] get;

    @Post
    public void get(@BodyParam("char") char[] param) {
      this.used = true;
      this.get = param;
    }
  }
}
