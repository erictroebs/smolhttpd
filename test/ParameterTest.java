import de.troebs.smol.Get;
import de.troebs.smol.Param;
import de.troebs.smol.Post;
import de.troebs.smol.SmolHTTPd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.ArrayUtil;
import util.HttpUtil;
import util.PortUtil;
import util.RandomUtil;

import java.util.HashMap;

public class ParameterTest {
  @Test
  public void testStringParameter() throws Exception {
    final String value = RandomUtil.getRandomString();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final StringHandler handler = new StringHandler();
      http.register("/{string}", handler);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + value);
      Assertions.assertTrue(handler.used);
      Assertions.assertEquals(value, handler.get);
    }
  }

  @Test
  public void testUnknownStringParameter() throws Exception {
    final String value = RandomUtil.getRandomString();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final StringHandler handler0 = new StringHandler();
      final StringHandler handler1 = new StringHandler();
      http.register("/{test}", handler0);
      http.register("/{string}", handler1);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + value);
      Assertions.assertFalse(handler0.used);
      Assertions.assertTrue(handler1.used);
      Assertions.assertEquals(value, handler1.get);
    }
  }

  @Test
  public void testOverlappingStringParameter() throws Exception {
    final String value = RandomUtil.getRandomString();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final StringHandler handler0 = new StringHandler();
      final StringHandler handler1 = new StringHandler();
      final StringHandler handler2 = new StringHandler();
      final StringHandler handler3 = new StringHandler();

      http.register("/a/{string}", handler0);
      http.register("/a/{string}", handler1);
      http.register("/b/{string_alt}", handler2);
      http.register("/b/{string}", handler3);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/a/" + value);
      Assertions.assertTrue(handler0.used);
      Assertions.assertFalse(handler1.used);
      Assertions.assertEquals(value, handler0.get);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/b/" + value);
      Assertions.assertTrue(handler2.used_alt);
      Assertions.assertFalse(handler3.used);
      Assertions.assertEquals(value, handler2.get);
    }
  }

  @Test
  public void testByteParameter() throws Exception {
    final byte value = RandomUtil.getRandomByte();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final ByteHandler handler = new ByteHandler();
      http.register("/{byte}", handler);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + value);
      Assertions.assertTrue(handler.used);
      Assertions.assertEquals(value, handler.get);
    }
  }

  @Test
  public void testUnknownByteParameter() throws Exception {
    final byte value = RandomUtil.getRandomByte();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final ByteHandler handler0 = new ByteHandler();
      final ByteHandler handler1 = new ByteHandler();
      http.register("/{test}", handler0);
      http.register("/{byte}", handler1);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + value);
      Assertions.assertFalse(handler0.used);
      Assertions.assertTrue(handler1.used);
      Assertions.assertEquals(value, handler1.get);
    }
  }

  @Test
  public void testOverlappingByteParameter() throws Exception {
    final byte value = RandomUtil.getRandomByte();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final ByteHandler handler0 = new ByteHandler();
      final ByteHandler handler1 = new ByteHandler();
      final ByteHandler handler2 = new ByteHandler();
      final ByteHandler handler3 = new ByteHandler();

      http.register("/a/{byte}", handler0);
      http.register("/a/{byte}", handler1);
      http.register("/b/{byte_alt}", handler2);
      http.register("/b/{byte}", handler3);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/a/" + value);
      Assertions.assertTrue(handler0.used);
      Assertions.assertFalse(handler1.used);
      Assertions.assertEquals(value, handler0.get);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/b/" + value);
      Assertions.assertFalse(handler2.used_alt);
      Assertions.assertTrue(handler3.used);
      Assertions.assertEquals(value, handler3.get);
    }
  }

  @Test
  public void testShortParameter() throws Exception {
    final short value = RandomUtil.getRandomShort();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final ShortHandler handler = new ShortHandler();
      http.register("/{short}", handler);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + value);
      Assertions.assertTrue(handler.used);
      Assertions.assertEquals(value, handler.get);
    }
  }

  @Test
  public void testUnknownShortParameter() throws Exception {
    final short value = RandomUtil.getRandomShort();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final ShortHandler handler0 = new ShortHandler();
      final ShortHandler handler1 = new ShortHandler();
      http.register("/{test}", handler0);
      http.register("/{short}", handler1);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + value);
      Assertions.assertFalse(handler0.used);
      Assertions.assertTrue(handler1.used);
      Assertions.assertEquals(value, handler1.get);
    }
  }

  @Test
  public void testOverlappingShortParameter() throws Exception {
    final short value = RandomUtil.getRandomShort();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final ShortHandler handler0 = new ShortHandler();
      final ShortHandler handler1 = new ShortHandler();
      final ShortHandler handler2 = new ShortHandler();
      final ShortHandler handler3 = new ShortHandler();

      http.register("/a/{short}", handler0);
      http.register("/a/{short}", handler1);
      http.register("/b/{short_alt}", handler2);
      http.register("/b/{short}", handler3);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/a/" + value);
      Assertions.assertTrue(handler0.used);
      Assertions.assertFalse(handler1.used);
      Assertions.assertEquals(value, handler0.get);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/b/" + value);
      Assertions.assertFalse(handler2.used_alt);
      Assertions.assertTrue(handler3.used);
      Assertions.assertEquals(value, handler3.get);
    }
  }

  @Test
  public void testIntParameter() throws Exception {
    final int value = RandomUtil.getRandomInteger();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final IntHandler handler = new IntHandler();
      http.register("/{int}", handler);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + value);
      Assertions.assertTrue(handler.used);
      Assertions.assertEquals(value, handler.get);
    }
  }

  @Test
  public void testUnknownIntParameter() throws Exception {
    final int value = RandomUtil.getRandomInteger();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final IntHandler handler0 = new IntHandler();
      final IntHandler handler1 = new IntHandler();
      http.register("/{test}", handler0);
      http.register("/{int}", handler1);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + value);
      Assertions.assertFalse(handler0.used);
      Assertions.assertTrue(handler1.used);
      Assertions.assertEquals(value, handler1.get);
    }
  }

  @Test
  public void testOverlappingIntParameter() throws Exception {
    final int value = RandomUtil.getRandomInteger();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final IntHandler handler0 = new IntHandler();
      final IntHandler handler1 = new IntHandler();
      final IntHandler handler2 = new IntHandler();
      final IntHandler handler3 = new IntHandler();

      http.register("/a/{int}", handler0);
      http.register("/a/{int}", handler1);
      http.register("/b/{int_alt}", handler2);
      http.register("/b/{int}", handler3);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/a/" + value);
      Assertions.assertTrue(handler0.used);
      Assertions.assertFalse(handler1.used);
      Assertions.assertEquals(value, handler0.get);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/b/" + value);
      Assertions.assertFalse(handler2.used_alt);
      Assertions.assertTrue(handler3.used);
      Assertions.assertEquals(value, handler3.get);
    }
  }

  @Test
  public void testLongParameter() throws Exception {
    final long value = RandomUtil.getRandomLong();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final LongHandler handler = new LongHandler();
      http.register("/{long}", handler);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + value);
      Assertions.assertTrue(handler.used);
      Assertions.assertEquals(value, handler.get);
    }
  }

  @Test
  public void testUnknownLongParameter() throws Exception {
    final long value = RandomUtil.getRandomLong();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final LongHandler handler0 = new LongHandler();
      final LongHandler handler1 = new LongHandler();
      http.register("/{test}", handler0);
      http.register("/{long}", handler1);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + value);
      Assertions.assertFalse(handler0.used);
      Assertions.assertTrue(handler1.used);
      Assertions.assertEquals(value, handler1.get);
    }
  }

  @Test
  public void testOverlappingLongParameter() throws Exception {
    final long value = RandomUtil.getRandomLong();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final LongHandler handler0 = new LongHandler();
      final LongHandler handler1 = new LongHandler();
      final LongHandler handler2 = new LongHandler();
      final LongHandler handler3 = new LongHandler();

      http.register("/a/{long}", handler0);
      http.register("/a/{long}", handler1);
      http.register("/b/{long_alt}", handler2);
      http.register("/b/{long}", handler3);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/a/" + value);
      Assertions.assertTrue(handler0.used);
      Assertions.assertFalse(handler1.used);
      Assertions.assertEquals(value, handler0.get);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/b/" + value);
      Assertions.assertFalse(handler2.used_alt);
      Assertions.assertTrue(handler3.used);
      Assertions.assertEquals(value, handler3.get);
    }
  }

  @Test
  public void testFloatParameter() throws Exception {
    final float value = RandomUtil.getRandomFloat();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final FloatHandler handler = new FloatHandler();
      http.register("/{float}", handler);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + value);
      Assertions.assertTrue(handler.used);
      Assertions.assertEquals(value, handler.get);
    }
  }

  @Test
  public void testUnknownFloatParameter() throws Exception {
    final float value = RandomUtil.getRandomFloat();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final FloatHandler handler0 = new FloatHandler();
      final FloatHandler handler1 = new FloatHandler();
      http.register("/{test}", handler0);
      http.register("/{float}", handler1);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + value);
      Assertions.assertFalse(handler0.used);
      Assertions.assertTrue(handler1.used);
      Assertions.assertEquals(value, handler1.get);
    }
  }

  @Test
  public void testOverlappingFloatParameter() throws Exception {
    final float value = RandomUtil.getRandomFloat();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final FloatHandler handler0 = new FloatHandler();
      final FloatHandler handler1 = new FloatHandler();
      final FloatHandler handler2 = new FloatHandler();
      final FloatHandler handler3 = new FloatHandler();

      http.register("/a/{float}", handler0);
      http.register("/a/{float}", handler1);
      http.register("/b/{float_alt}", handler2);
      http.register("/b/{float}", handler3);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/a/" + value);
      Assertions.assertTrue(handler0.used);
      Assertions.assertFalse(handler1.used);
      Assertions.assertEquals(value, handler0.get);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/b/" + value);
      Assertions.assertFalse(handler2.used_alt);
      Assertions.assertTrue(handler3.used);
      Assertions.assertEquals(value, handler3.get);
    }
  }

  @Test
  public void testDoubleParameter() throws Exception {
    final double value = RandomUtil.getRandomDouble();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final DoubleHandler handler = new DoubleHandler();
      http.register("/{double}", handler);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + value);
      Assertions.assertTrue(handler.used);
      Assertions.assertEquals(value, handler.get);
    }
  }

  @Test
  public void testUnknownDoubleParameter() throws Exception {
    final double value = RandomUtil.getRandomDouble();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final DoubleHandler handler0 = new DoubleHandler();
      final DoubleHandler handler1 = new DoubleHandler();
      http.register("/{test}", handler0);
      http.register("/{double}", handler1);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + value);
      Assertions.assertFalse(handler0.used);
      Assertions.assertTrue(handler1.used);
      Assertions.assertEquals(value, handler1.get);
    }
  }

  @Test
  public void testOverlappingDoubleParameter() throws Exception {
    final double value = RandomUtil.getRandomDouble();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final DoubleHandler handler0 = new DoubleHandler();
      final DoubleHandler handler1 = new DoubleHandler();
      final DoubleHandler handler2 = new DoubleHandler();
      final DoubleHandler handler3 = new DoubleHandler();

      http.register("/a/{double}", handler0);
      http.register("/a/{double}", handler1);
      http.register("/b/{double_alt}", handler2);
      http.register("/b/{double}", handler3);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/a/" + value);
      Assertions.assertTrue(handler0.used);
      Assertions.assertFalse(handler1.used);
      Assertions.assertEquals(value, handler0.get);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/b/" + value);
      Assertions.assertFalse(handler2.used_alt);
      Assertions.assertTrue(handler3.used);
      Assertions.assertEquals(value, handler3.get);
    }
  }

  @Test
  public void testBooleanParameter() throws Exception {
    final boolean value = RandomUtil.getRandomBoolean();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final BooleanHandler handler = new BooleanHandler();
      http.register("/{boolean}", handler);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + value);
      Assertions.assertTrue(handler.used);
      Assertions.assertEquals(value, handler.get);
    }
  }

  @Test
  public void testUnknownBooleanParameter() throws Exception {
    final boolean value = RandomUtil.getRandomBoolean();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final BooleanHandler handler0 = new BooleanHandler();
      final BooleanHandler handler1 = new BooleanHandler();
      http.register("/{test}", handler0);
      http.register("/{boolean}", handler1);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + value);
      Assertions.assertFalse(handler0.used);
      Assertions.assertTrue(handler1.used);
      Assertions.assertEquals(value, handler1.get);
    }
  }

  @Test
  public void testOverlappingBooleanParameter() throws Exception {
    final boolean value = RandomUtil.getRandomBoolean();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final BooleanHandler handler0 = new BooleanHandler();
      final BooleanHandler handler1 = new BooleanHandler();
      final BooleanHandler handler2 = new BooleanHandler();
      final BooleanHandler handler3 = new BooleanHandler();

      http.register("/a/{boolean}", handler0);
      http.register("/a/{boolean}", handler1);
      http.register("/b/{boolean_alt}", handler2);
      http.register("/b/{boolean}", handler3);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/a/" + value);
      Assertions.assertTrue(handler0.used);
      Assertions.assertFalse(handler1.used);
      Assertions.assertEquals(value, handler0.get);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/b/" + value);
      Assertions.assertFalse(handler2.used_alt);
      Assertions.assertTrue(handler3.used);
      Assertions.assertEquals(value, handler3.get);
    }
  }

  @Test
  public void testCharParameter() throws Exception {
    final char value = RandomUtil.getRandomCharacter();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final CharHandler handler = new CharHandler();
      http.register("/{char}", handler);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + value);
      Assertions.assertTrue(handler.used);
      Assertions.assertEquals(value, handler.get);
    }
  }

  @Test
  public void testUnknownCharParameter() throws Exception {
    final char value = RandomUtil.getRandomCharacter();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final CharHandler handler0 = new CharHandler();
      final CharHandler handler1 = new CharHandler();
      http.register("/{test}", handler0);
      http.register("/{char}", handler1);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + value);
      Assertions.assertFalse(handler0.used);
      Assertions.assertTrue(handler1.used);
      Assertions.assertEquals(value, handler1.get);
    }
  }

  @Test
  public void testOverlappingCharParameter() throws Exception {
    final char value = RandomUtil.getRandomCharacter();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final CharHandler handler0 = new CharHandler();
      final CharHandler handler1 = new CharHandler();
      final CharHandler handler2 = new CharHandler();
      final CharHandler handler3 = new CharHandler();

      http.register("/a/{char}", handler0);
      http.register("/a/{char}", handler1);
      http.register("/b/{char_alt}", handler2);
      http.register("/b/{char}", handler3);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/a/" + value);
      Assertions.assertTrue(handler0.used);
      Assertions.assertFalse(handler1.used);
      Assertions.assertEquals(value, handler0.get);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/b/" + value);
      Assertions.assertFalse(handler2.used_alt);
      Assertions.assertTrue(handler3.used);
      Assertions.assertEquals(value, handler3.get);
    }
  }

  @Test
  public void testStringArrayParameter() throws Exception {
    final String[] value = RandomUtil.getRandomStrings(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final StringArrayHandler handler = new StringArrayHandler();
      http.register("/{string}", handler);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + ArrayUtil.implode(value, ","));
      Assertions.assertTrue(handler.used);
      Assertions.assertArrayEquals(value, handler.get);
    }
  }

  @Test
  public void testUnknownStringArrayParameter() throws Exception {
    final String[] value = RandomUtil.getRandomStrings(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final StringArrayHandler handler0 = new StringArrayHandler();
      final StringArrayHandler handler1 = new StringArrayHandler();
      http.register("/{test}", handler0);
      http.register("/{string}", handler1);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + ArrayUtil.implode(value, ","));
      Assertions.assertFalse(handler0.used);
      Assertions.assertTrue(handler1.used);
      Assertions.assertArrayEquals(value, handler1.get);
    }
  }

  @Test
  public void testOverlappingStringArrayParameter() throws Exception {
    final String[] value = RandomUtil.getRandomStrings(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final StringArrayHandler handler0 = new StringArrayHandler();
      final StringArrayHandler handler1 = new StringArrayHandler();
      final StringArrayHandler handler2 = new StringArrayHandler();
      final StringArrayHandler handler3 = new StringArrayHandler();

      http.register("/a/{string}", handler0);
      http.register("/a/{string}", handler1);
      http.register("/b/{string_alt}", handler2);
      http.register("/b/{string}", handler3);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/a/" + ArrayUtil.implode(value, ","));
      Assertions.assertTrue(handler0.used);
      Assertions.assertFalse(handler1.used);
      Assertions.assertArrayEquals(value, handler0.get);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/b/" + ArrayUtil.implode(value, ","));
      Assertions.assertTrue(handler2.used_alt);
      Assertions.assertFalse(handler3.used);
      Assertions.assertArrayEquals(value, handler2.get);
    }
  }

  @Test
  public void testByteArrayParameter() throws Exception {
    final byte[] value = RandomUtil.getRandomBytes(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final ByteArrayHandler handler = new ByteArrayHandler();
      http.register("/{byte}", handler);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + ArrayUtil.implode(value, ","));
      Assertions.assertTrue(handler.used);
      Assertions.assertArrayEquals(value, handler.get);
    }
  }

  @Test
  public void testUnknownByteArrayParameter() throws Exception {
    final byte[] value = RandomUtil.getRandomBytes(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final ByteArrayHandler handler0 = new ByteArrayHandler();
      final ByteArrayHandler handler1 = new ByteArrayHandler();
      http.register("/{test}", handler0);
      http.register("/{byte}", handler1);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + ArrayUtil.implode(value, ","));
      Assertions.assertFalse(handler0.used);
      Assertions.assertTrue(handler1.used);
      Assertions.assertArrayEquals(value, handler1.get);
    }
  }

  @Test
  public void testOverlappingByteArrayParameter() throws Exception {
    final byte[] value = RandomUtil.getRandomBytes(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final ByteArrayHandler handler0 = new ByteArrayHandler();
      final ByteArrayHandler handler1 = new ByteArrayHandler();
      final ByteArrayHandler handler2 = new ByteArrayHandler();
      final ByteArrayHandler handler3 = new ByteArrayHandler();

      http.register("/a/{byte}", handler0);
      http.register("/a/{byte}", handler1);
      http.register("/b/{byte_alt}", handler2);
      http.register("/b/{byte}", handler3);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/a/" + ArrayUtil.implode(value, ","));
      Assertions.assertTrue(handler0.used);
      Assertions.assertFalse(handler1.used);
      Assertions.assertArrayEquals(value, handler0.get);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/b/" + ArrayUtil.implode(value, ","));
      Assertions.assertFalse(handler2.used_alt);
      Assertions.assertTrue(handler3.used);
      Assertions.assertArrayEquals(value, handler3.get);
    }
  }

  @Test
  public void testShortArrayParameter() throws Exception {
    final short[] value = RandomUtil.getRandomShorts(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final ShortArrayHandler handler = new ShortArrayHandler();
      http.register("/{short}", handler);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + ArrayUtil.implode(value, ","));
      Assertions.assertTrue(handler.used);
      Assertions.assertArrayEquals(value, handler.get);
    }
  }

  @Test
  public void testUnknownShortArrayParameter() throws Exception {
    final short[] value = RandomUtil.getRandomShorts(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final ShortArrayHandler handler0 = new ShortArrayHandler();
      final ShortArrayHandler handler1 = new ShortArrayHandler();
      http.register("/{test}", handler0);
      http.register("/{short}", handler1);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + ArrayUtil.implode(value, ","));
      Assertions.assertFalse(handler0.used);
      Assertions.assertTrue(handler1.used);
      Assertions.assertArrayEquals(value, handler1.get);
    }
  }

  @Test
  public void testOverlappingShortArrayParameter() throws Exception {
    final short[] value = RandomUtil.getRandomShorts(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final ShortArrayHandler handler0 = new ShortArrayHandler();
      final ShortArrayHandler handler1 = new ShortArrayHandler();
      final ShortArrayHandler handler2 = new ShortArrayHandler();
      final ShortArrayHandler handler3 = new ShortArrayHandler();

      http.register("/a/{short}", handler0);
      http.register("/a/{short}", handler1);
      http.register("/b/{short_alt}", handler2);
      http.register("/b/{short}", handler3);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/a/" + ArrayUtil.implode(value, ","));
      Assertions.assertTrue(handler0.used);
      Assertions.assertFalse(handler1.used);
      Assertions.assertArrayEquals(value, handler0.get);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/b/" + ArrayUtil.implode(value, ","));
      Assertions.assertFalse(handler2.used_alt);
      Assertions.assertTrue(handler3.used);
      Assertions.assertArrayEquals(value, handler3.get);
    }
  }

  @Test
  public void testIntArrayParameter() throws Exception {
    final int[] value = RandomUtil.getRandomIntegers(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final IntArrayHandler handler = new IntArrayHandler();
      http.register("/{int}", handler);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + ArrayUtil.implode(value, ","));
      Assertions.assertTrue(handler.used);
      Assertions.assertArrayEquals(value, handler.get);
    }
  }

  @Test
  public void testUnknownIntArrayParameter() throws Exception {
    final int[] value = RandomUtil.getRandomIntegers(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final IntArrayHandler handler0 = new IntArrayHandler();
      final IntArrayHandler handler1 = new IntArrayHandler();
      http.register("/{test}", handler0);
      http.register("/{int}", handler1);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + ArrayUtil.implode(value, ","));
      Assertions.assertFalse(handler0.used);
      Assertions.assertTrue(handler1.used);
      Assertions.assertArrayEquals(value, handler1.get);
    }
  }

  @Test
  public void testOverlappingIntArrayParameter() throws Exception {
    final int[] value = RandomUtil.getRandomIntegers(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final IntArrayHandler handler0 = new IntArrayHandler();
      final IntArrayHandler handler1 = new IntArrayHandler();
      final IntArrayHandler handler2 = new IntArrayHandler();
      final IntArrayHandler handler3 = new IntArrayHandler();

      http.register("/a/{int}", handler0);
      http.register("/a/{int}", handler1);
      http.register("/b/{int_alt}", handler2);
      http.register("/b/{int}", handler3);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/a/" + ArrayUtil.implode(value, ","));
      Assertions.assertTrue(handler0.used);
      Assertions.assertFalse(handler1.used);
      Assertions.assertArrayEquals(value, handler0.get);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/b/" + ArrayUtil.implode(value, ","));
      Assertions.assertFalse(handler2.used_alt);
      Assertions.assertTrue(handler3.used);
      Assertions.assertArrayEquals(value, handler3.get);
    }
  }

  @Test
  public void testLongArrayParameter() throws Exception {
    final long[] value = RandomUtil.getRandomLongs(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final LongArrayHandler handler = new LongArrayHandler();
      http.register("/{long}", handler);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + ArrayUtil.implode(value, ","));
      Assertions.assertTrue(handler.used);
      Assertions.assertArrayEquals(value, handler.get);
    }
  }

  @Test
  public void testUnknownLongArrayParameter() throws Exception {
    final long[] value = RandomUtil.getRandomLongs(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final LongArrayHandler handler0 = new LongArrayHandler();
      final LongArrayHandler handler1 = new LongArrayHandler();
      http.register("/{test}", handler0);
      http.register("/{long}", handler1);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + ArrayUtil.implode(value, ","));
      Assertions.assertFalse(handler0.used);
      Assertions.assertTrue(handler1.used);
      Assertions.assertArrayEquals(value, handler1.get);
    }
  }

  @Test
  public void testOverlappingLongArrayParameter() throws Exception {
    final long[] value = RandomUtil.getRandomLongs(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final LongArrayHandler handler0 = new LongArrayHandler();
      final LongArrayHandler handler1 = new LongArrayHandler();
      final LongArrayHandler handler2 = new LongArrayHandler();
      final LongArrayHandler handler3 = new LongArrayHandler();

      http.register("/a/{long}", handler0);
      http.register("/a/{long}", handler1);
      http.register("/b/{long_alt}", handler2);
      http.register("/b/{long}", handler3);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/a/" + ArrayUtil.implode(value, ","));
      Assertions.assertTrue(handler0.used);
      Assertions.assertFalse(handler1.used);
      Assertions.assertArrayEquals(value, handler0.get);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/b/" + ArrayUtil.implode(value, ","));
      Assertions.assertFalse(handler2.used_alt);
      Assertions.assertTrue(handler3.used);
      Assertions.assertArrayEquals(value, handler3.get);
    }
  }

  @Test
  public void testFloatArrayParameter() throws Exception {
    final float[] value = RandomUtil.getRandomFloats(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final FloatArrayHandler handler = new FloatArrayHandler();
      http.register("/{float}", handler);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + ArrayUtil.implode(value, ","));
      Assertions.assertTrue(handler.used);
      Assertions.assertArrayEquals(value, handler.get);
    }
  }

  @Test
  public void testUnknownFloatArrayParameter() throws Exception {
    final float[] value = RandomUtil.getRandomFloats(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final FloatArrayHandler handler0 = new FloatArrayHandler();
      final FloatArrayHandler handler1 = new FloatArrayHandler();
      http.register("/{test}", handler0);
      http.register("/{float}", handler1);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + ArrayUtil.implode(value, ","));
      Assertions.assertFalse(handler0.used);
      Assertions.assertTrue(handler1.used);
      Assertions.assertArrayEquals(value, handler1.get);
    }
  }

  @Test
  public void testOverlappingFloatArrayParameter() throws Exception {
    final float[] value = RandomUtil.getRandomFloats(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final FloatArrayHandler handler0 = new FloatArrayHandler();
      final FloatArrayHandler handler1 = new FloatArrayHandler();
      final FloatArrayHandler handler2 = new FloatArrayHandler();
      final FloatArrayHandler handler3 = new FloatArrayHandler();

      http.register("/a/{float}", handler0);
      http.register("/a/{float}", handler1);
      http.register("/b/{float_alt}", handler2);
      http.register("/b/{float}", handler3);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/a/" + ArrayUtil.implode(value, ","));
      Assertions.assertTrue(handler0.used);
      Assertions.assertFalse(handler1.used);
      Assertions.assertArrayEquals(value, handler0.get);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/b/" + ArrayUtil.implode(value, ","));
      Assertions.assertFalse(handler2.used_alt);
      Assertions.assertTrue(handler3.used);
      Assertions.assertArrayEquals(value, handler3.get);
    }
  }

  @Test
  public void testDoubleArrayParameter() throws Exception {
    final double[] value = RandomUtil.getRandomDoubles(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final DoubleArrayHandler handler = new DoubleArrayHandler();
      http.register("/{double}", handler);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + ArrayUtil.implode(value, ","));
      Assertions.assertTrue(handler.used);
      Assertions.assertArrayEquals(value, handler.get);
    }
  }

  @Test
  public void testUnknownDoubleArrayParameter() throws Exception {
    final double[] value = RandomUtil.getRandomDoubles(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final DoubleArrayHandler handler0 = new DoubleArrayHandler();
      final DoubleArrayHandler handler1 = new DoubleArrayHandler();
      http.register("/{test}", handler0);
      http.register("/{double}", handler1);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + ArrayUtil.implode(value, ","));
      Assertions.assertFalse(handler0.used);
      Assertions.assertTrue(handler1.used);
      Assertions.assertArrayEquals(value, handler1.get);
    }
  }

  @Test
  public void testOverlappingDoubleArrayParameter() throws Exception {
    final double[] value = RandomUtil.getRandomDoubles(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final DoubleArrayHandler handler0 = new DoubleArrayHandler();
      final DoubleArrayHandler handler1 = new DoubleArrayHandler();
      final DoubleArrayHandler handler2 = new DoubleArrayHandler();
      final DoubleArrayHandler handler3 = new DoubleArrayHandler();

      http.register("/a/{double}", handler0);
      http.register("/a/{double}", handler1);
      http.register("/b/{double_alt}", handler2);
      http.register("/b/{double}", handler3);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/a/" + ArrayUtil.implode(value, ","));
      Assertions.assertTrue(handler0.used);
      Assertions.assertFalse(handler1.used);
      Assertions.assertArrayEquals(value, handler0.get);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/b/" + ArrayUtil.implode(value, ","));
      Assertions.assertFalse(handler2.used_alt);
      Assertions.assertTrue(handler3.used);
      Assertions.assertArrayEquals(value, handler3.get);
    }
  }

  @Test
  public void testBooleanArrayParameter() throws Exception {
    final boolean[] value = RandomUtil.getRandomBooleans(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final BooleanArrayHandler handler = new BooleanArrayHandler();
      http.register("/{boolean}", handler);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + ArrayUtil.implode(value, ","));
      Assertions.assertTrue(handler.used);
      Assertions.assertArrayEquals(value, handler.get);
    }
  }

  @Test
  public void testUnknownBooleanArrayParameter() throws Exception {
    final boolean[] value = RandomUtil.getRandomBooleans(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final BooleanArrayHandler handler0 = new BooleanArrayHandler();
      final BooleanArrayHandler handler1 = new BooleanArrayHandler();
      http.register("/{test}", handler0);
      http.register("/{boolean}", handler1);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + ArrayUtil.implode(value, ","));
      Assertions.assertFalse(handler0.used);
      Assertions.assertTrue(handler1.used);
      Assertions.assertArrayEquals(value, handler1.get);
    }
  }

  @Test
  public void testOverlappingBooleanArrayParameter() throws Exception {
    final boolean[] value = RandomUtil.getRandomBooleans(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final BooleanArrayHandler handler0 = new BooleanArrayHandler();
      final BooleanArrayHandler handler1 = new BooleanArrayHandler();
      final BooleanArrayHandler handler2 = new BooleanArrayHandler();
      final BooleanArrayHandler handler3 = new BooleanArrayHandler();

      http.register("/a/{boolean}", handler0);
      http.register("/a/{boolean}", handler1);
      http.register("/b/{boolean_alt}", handler2);
      http.register("/b/{boolean}", handler3);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/a/" + ArrayUtil.implode(value, ","));
      Assertions.assertTrue(handler0.used);
      Assertions.assertFalse(handler1.used);
      Assertions.assertArrayEquals(value, handler0.get);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/b/" + ArrayUtil.implode(value, ","));
      Assertions.assertFalse(handler2.used_alt);
      Assertions.assertTrue(handler3.used);
      Assertions.assertArrayEquals(value, handler3.get);
    }
  }

  @Test
  public void testCharArrayParameter() throws Exception {
    final char[] value = RandomUtil.getRandomCharacters(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final CharArrayHandler handler = new CharArrayHandler();
      http.register("/{char}", handler);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + ArrayUtil.implode(value, ","));
      Assertions.assertTrue(handler.used);
      Assertions.assertArrayEquals(value, handler.get);
    }
  }

  @Test
  public void testUnknownCharArrayParameter() throws Exception {
    final char[] value = RandomUtil.getRandomCharacters(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final CharArrayHandler handler0 = new CharArrayHandler();
      final CharArrayHandler handler1 = new CharArrayHandler();
      http.register("/{test}", handler0);
      http.register("/{char}", handler1);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + ArrayUtil.implode(value, ","));
      Assertions.assertFalse(handler0.used);
      Assertions.assertTrue(handler1.used);
      Assertions.assertArrayEquals(value, handler1.get);
    }
  }

  @Test
  public void testOverlappingCharArrayParameter() throws Exception {
    final char[] value = RandomUtil.getRandomCharacters(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final CharArrayHandler handler0 = new CharArrayHandler();
      final CharArrayHandler handler1 = new CharArrayHandler();
      final CharArrayHandler handler2 = new CharArrayHandler();
      final CharArrayHandler handler3 = new CharArrayHandler();

      http.register("/a/{char}", handler0);
      http.register("/a/{char}", handler1);
      http.register("/b/{char_alt}", handler2);
      http.register("/b/{char}", handler3);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/a/" + ArrayUtil.implode(value, ","));
      Assertions.assertTrue(handler0.used);
      Assertions.assertFalse(handler1.used);
      Assertions.assertArrayEquals(value, handler0.get);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/b/" + ArrayUtil.implode(value, ","));
      Assertions.assertFalse(handler2.used_alt);
      Assertions.assertTrue(handler3.used);
      Assertions.assertArrayEquals(value, handler3.get);
    }
  }


  @Test
  public void testOptionalParameters() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      OptionalStringHandler handler0 = new OptionalStringHandler();
      OptionalStringHandler handler1 = new OptionalStringHandler();

      http.register("/a/", handler0);
      http.register("/a/{string}", handler0);
      http.register("/b/", handler1);
      http.register("/b/{string}", handler1);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertTrue(handler0.used);
      Assertions.assertFalse(handler0.used_alt);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/b/asdfgh");
      Assertions.assertFalse(handler1.used);
      Assertions.assertTrue(handler1.used_alt);
      Assertions.assertEquals("asdfgh", handler1.get);
    }
  }

  @Test
  public void testConflictingParameters() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final StringHandler handler0 = new StringHandler();
      final IntHandler handler1 = new IntHandler();
      final StringHandler handler2 = new StringHandler();
      final IntHandler handler3 = new IntHandler();
      final IntHandler handler4 = new IntHandler();
      final StringHandler handler5 = new StringHandler();
      final IntHandler handler6 = new IntHandler();
      final StringHandler handler7 = new StringHandler();

      http.register("/a/{p}", handler0);
      http.register("/a/{p}", handler1);
      http.register("/b/{p}", handler2);
      http.register("/b/{p}", handler3);
      http.register("/c/{p}", handler4);
      http.register("/c/{p}", handler5);
      http.register("/d/{p}", handler6);
      http.register("/d/{p}", handler7);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/a/123456", new HashMap<>());
      Assertions.assertTrue(handler0.used);
      Assertions.assertFalse(handler1.used);
      Assertions.assertEquals("123456", handler0.get);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/b/asdfgh", new HashMap<>());
      Assertions.assertTrue(handler2.used);
      Assertions.assertFalse(handler3.used);
      Assertions.assertEquals("asdfgh", handler2.get);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/c/123456", new HashMap<>());
      Assertions.assertTrue(handler4.used);
      Assertions.assertFalse(handler5.used);
      Assertions.assertEquals(123456, handler4.get);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/d/asdfgh", new HashMap<>());
      Assertions.assertFalse(handler6.used);
      Assertions.assertTrue(handler7.used);
      Assertions.assertEquals("asdfgh", handler7.get);
    }
  }

  @Test
  public void testArrayOverlappingPrimitive() throws Exception {
    final int intValue = RandomUtil.getRandomInteger();
    final int[] intArrayValue = RandomUtil.getRandomIntegers(5);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      IntArrayHandler handler0 = new IntArrayHandler();
      IntHandler handler1 = new IntHandler();
      IntHandler handler2 = new IntHandler();
      IntArrayHandler handler3 = new IntArrayHandler();
      IntArrayHandler handler4 = new IntArrayHandler();
      IntHandler handler5 = new IntHandler();
      IntHandler handler6 = new IntHandler();
      IntArrayHandler handler7 = new IntArrayHandler();

      http.register("/a/{int}", handler0);
      http.register("/a/{int}", handler1);
      http.register("/b/{int}", handler2);
      http.register("/b/{int}", handler3);
      http.register("/c/{int}", handler4);
      http.register("/c/{int}", handler5);
      http.register("/d/{int}", handler6);
      http.register("/d/{int}", handler7);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/a/" + intValue);
      Assertions.assertTrue(handler0.used);
      Assertions.assertFalse(handler1.used);
      Assertions.assertArrayEquals(new int[] {intValue}, handler0.get);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/b/" + intValue);
      Assertions.assertTrue(handler2.used);
      Assertions.assertFalse(handler3.used);
      Assertions.assertEquals(intValue, handler2.get);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/c/" + ArrayUtil.implode(intArrayValue, ","));
      Assertions.assertTrue(handler4.used);
      Assertions.assertFalse(handler5.used);
      Assertions.assertArrayEquals(intArrayValue, handler4.get);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/d/" + ArrayUtil.implode(intArrayValue, ","));
      Assertions.assertFalse(handler6.used);
      Assertions.assertTrue(handler7.used);
      Assertions.assertArrayEquals(intArrayValue, handler7.get);
    }
  }


  public static class StringHandler {
    public boolean used = false;
    public boolean used_alt = false;
    public String get;

    @Get
    public void get(@Param("string") String param) {
      this.used = true;
      this.get = param;
    }

    @Get
    public void get_alt(@Param("string_alt") String param) {
      this.used_alt = true;
      this.get = param;
    }

    @Post
    public void post(@Param("p") String p) {
      this.used = true;
      this.get = p;
    }
  }

  public static class ByteHandler {
    public boolean used = false;
    public boolean used_alt = false;
    public byte get;

    @Get
    public void get(@Param("byte") byte param) {
      this.used = true;
      this.get = param;
    }

    @Get
    public void get_alt(@Param("byte_alt") Byte param) {
      this.used_alt = true;
      this.get = param;
    }
  }

  public static class ShortHandler {
    public boolean used = false;
    public boolean used_alt = false;
    public short get;

    @Get
    public void get(@Param("short") short param) {
      this.used = true;
      this.get = param;
    }

    @Get
    public void get_alt(@Param("short_alt") Short param) {
      this.used_alt = true;
      this.get = param;
    }
  }

  public static class IntHandler {
    public boolean used = false;
    public boolean used_alt = false;
    public int get;

    @Get
    public void get(@Param("int") int param) {
      this.used = true;
      this.get = param;
    }

    @Get
    public void get_alt(@Param("int_alt") Integer param) {
      this.used_alt = true;
      this.get = param;
    }

    @Post
    public void post(@Param("p") int p) {
      this.used = true;
      this.get = p;
    }
  }

  public static class LongHandler {
    public boolean used = false;
    public boolean used_alt = false;
    public long get;

    @Get
    public void get(@Param("long") long param) {
      this.used = true;
      this.get = param;
    }

    @Get
    public void get_alt(@Param("long_alt") Long param) {
      this.used_alt = true;
      this.get = param;
    }
  }

  public static class FloatHandler {
    public boolean used = false;
    public boolean used_alt = false;
    public float get;

    @Get
    public void get(@Param("float") float param) {
      this.used = true;
      this.get = param;
    }

    @Get
    public void get_alt(@Param("float_alt") Float param) {
      this.used_alt = true;
      this.get = param;
    }
  }

  public static class DoubleHandler {
    public boolean used = false;
    public boolean used_alt = false;
    public double get;

    @Get
    public void get(@Param("double") double param) {
      this.used = true;
      this.get = param;
    }

    @Get
    public void get_alt(@Param("double_alt") Double param) {
      this.used_alt = true;
      this.get = param;
    }
  }

  public static class BooleanHandler {
    public boolean used = false;
    public boolean used_alt = false;
    public boolean get;

    @Get
    public void get(@Param("boolean") boolean param) {
      this.used = true;
      this.get = param;
    }

    @Get
    public void get_alt(@Param("boolean_alt") Boolean param) {
      this.used_alt = true;
      this.get = param;
    }
  }

  public static class CharHandler {
    public boolean used = false;
    public boolean used_alt = false;
    public char get;

    @Get
    public void get(@Param("char") char param) {
      this.used = true;
      this.get = param;
    }

    @Get
    public void get_alt(@Param("char_alt") Character param) {
      this.used_alt = true;
      this.get = param;
    }
  }

  public static class StringArrayHandler {
    public boolean used = false;
    public boolean used_alt = false;
    public String[] get;

    @Get
    public void get(@Param("string") String[] param) {
      this.used = true;
      this.get = param;
    }

    @Get
    public void get_alt(@Param("string_alt") String[] param) {
      this.used_alt = true;
      this.get = param;
    }

    @Post
    public void post(@Param("p") String[] p) {
      this.used = true;
      this.get = p;
    }
  }

  public static class ByteArrayHandler {
    public boolean used = false;
    public boolean used_alt = false;
    public byte[] get;
    public Byte[] get_alt;

    @Get
    public void get(@Param("byte") byte[] param) {
      this.used = true;
      this.get = param;
    }

    @Get
    public void get_alt(@Param("byte_alt") Byte[] param) {
      this.used_alt = true;
      this.get_alt = param;
    }
  }

  public static class ShortArrayHandler {
    public boolean used = false;
    public boolean used_alt = false;
    public short[] get;
    public Short[] get_alt;

    @Get
    public void get(@Param("short") short[] param) {
      this.used = true;
      this.get = param;
    }

    @Get
    public void get_alt(@Param("short_alt") Short[] param) {
      this.used_alt = true;
      this.get_alt = param;
    }
  }

  public static class IntArrayHandler {
    public boolean used = false;
    public boolean used_alt = false;
    public int[] get;
    public Integer[] get_alt;

    @Get
    public void get(@Param("int") int[] param) {
      this.used = true;
      this.get = param;
    }

    @Get
    public void get_alt(@Param("int_alt") Integer[] param) {
      this.used_alt = true;
      this.get_alt = param;
    }

    @Post
    public void post(@Param("p") int[] p) {
      this.used = true;
      this.get = p;
    }
  }

  public static class LongArrayHandler {
    public boolean used = false;
    public boolean used_alt = false;
    public long[] get;
    public Long[] get_alt;

    @Get
    public void get(@Param("long") long[] param) {
      this.used = true;
      this.get = param;
    }

    @Get
    public void get_alt(@Param("long_alt") Long[] param) {
      this.used_alt = true;
      this.get_alt = param;
    }
  }

  public static class FloatArrayHandler {
    public boolean used = false;
    public boolean used_alt = false;
    public float[] get;
    public Float[] get_alt;

    @Get
    public void get(@Param("float") float[] param) {
      this.used = true;
      this.get = param;
    }

    @Get
    public void get_alt(@Param("float_alt") Float[] param) {
      this.used_alt = true;
      this.get_alt = param;
    }
  }

  public static class DoubleArrayHandler {
    public boolean used = false;
    public boolean used_alt = false;
    public double[] get;
    public Double[] get_alt;

    @Get
    public void get(@Param("double") double[] param) {
      this.used = true;
      this.get = param;
    }

    @Get
    public void get_alt(@Param("double_alt") Double[] param) {
      this.used_alt = true;
      this.get_alt = param;
    }
  }

  public static class BooleanArrayHandler {
    public boolean used = false;
    public boolean used_alt = false;
    public boolean[] get;
    public Boolean[] get_alt;

    @Get
    public void get(@Param("boolean") boolean[] param) {
      this.used = true;
      this.get = param;
    }

    @Get
    public void get_alt(@Param("boolean_alt") Boolean[] param) {
      this.used_alt = true;
      this.get_alt = param;
    }
  }

  public static class CharArrayHandler {
    public boolean used = false;
    public boolean used_alt = false;
    public char[] get;
    public Character[] get_alt;

    @Get
    public void get(@Param("char") char[] param) {
      this.used = true;
      this.get = param;
    }

    @Get
    public void get_alt(@Param("char_alt") Character[] param) {
      this.used_alt = true;
      this.get_alt = param;
    }
  }

  public static class OptionalStringHandler {
    public boolean used = false;
    public boolean used_alt = false;
    public String get;

    @Get(priority = 1)
    public void get() {
      this.used = true;
    }

    @Get(priority = 2)
    public void alt(@Param("string") String param) {
      this.used_alt = true;
      this.get = param;
    }
  }
}
