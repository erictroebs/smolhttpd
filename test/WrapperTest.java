import de.troebs.smol.Get;
import de.troebs.smol.Post;
import de.troebs.smol.SmolHTTPd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.HttpUtil;
import util.PortUtil;
import util.RandomUtil;

import java.util.HashMap;

public class WrapperTest {
  @Test
  public void testByteWrapper() throws Exception {
    final byte rnd = RandomUtil.getRandomByte();
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/", new ByteHandler(rnd));

      Thread.sleep(10);

      Assertions.assertEquals(
        "{\"value\":" + rnd + "}",
        HttpUtil.get("http://localhost:" + PortUtil.q() + "/")
      );
      Assertions.assertEquals(
        "{\"value\":" + rnd + "}",
        HttpUtil.post("http://localhost:" + PortUtil.q() + "/", new HashMap<>())
      );
    }
  }

  @Test
  public void testShortWrapper() throws Exception {
    final short rnd = RandomUtil.getRandomShort();
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/", new ShortHandler(rnd));

      Thread.sleep(10);

      Assertions.assertEquals(
        "{\"value\":" + rnd + "}",
        HttpUtil.get("http://localhost:" + PortUtil.q() + "/")
      );
      Assertions.assertEquals(
        "{\"value\":" + rnd + "}",
        HttpUtil.post("http://localhost:" + PortUtil.q() + "/", new HashMap<>())
      );
    }
  }

  @Test
  public void testIntWrapper() throws Exception {
    final int rnd = RandomUtil.getRandomInteger();
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/", new IntHandler(rnd));

      Thread.sleep(10);

      Assertions.assertEquals(
        "{\"value\":" + rnd + "}",
        HttpUtil.get("http://localhost:" + PortUtil.q() + "/")
      );
      Assertions.assertEquals(
        "{\"value\":" + rnd + "}",
        HttpUtil.post("http://localhost:" + PortUtil.q() + "/", new HashMap<>())
      );
    }
  }

  @Test
  public void testLongWrapper() throws Exception {
    final long rnd = RandomUtil.getRandomLong();
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/", new LongHandler(rnd));

      Thread.sleep(10);

      Assertions.assertEquals(
        "{\"value\":" + rnd + "}",
        HttpUtil.get("http://localhost:" + PortUtil.q() + "/")
      );
      Assertions.assertEquals(
        "{\"value\":" + rnd + "}",
        HttpUtil.post("http://localhost:" + PortUtil.q() + "/", new HashMap<>())
      );
    }
  }

  @Test
  public void testFloatWrapper() throws Exception {
    final float rnd = RandomUtil.getRandomFloat();
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/", new FloatHandler(rnd));

      Thread.sleep(10);

      Assertions.assertEquals(
        "{\"value\":" + rnd + "}",
        HttpUtil.get("http://localhost:" + PortUtil.q() + "/")
      );
      Assertions.assertEquals(
        "{\"value\":" + rnd + "}",
        HttpUtil.post("http://localhost:" + PortUtil.q() + "/", new HashMap<>())
      );
    }
  }

  @Test
  public void testDoubleWrapper() throws Exception {
    final double rnd = RandomUtil.getRandomDouble();
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/", new DoubleHandler(rnd));

      Thread.sleep(10);

      Assertions.assertEquals(
        "{\"value\":" + rnd + "}",
        HttpUtil.get("http://localhost:" + PortUtil.q() + "/")
      );
      Assertions.assertEquals(
        "{\"value\":" + rnd + "}",
        HttpUtil.post("http://localhost:" + PortUtil.q() + "/", new HashMap<>())
      );
    }
  }

  @Test
  public void testBooleanWrapper() throws Exception {
    final boolean rnd = RandomUtil.getRandomBoolean();
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/", new BooleanHandler(rnd));

      Thread.sleep(10);

      Assertions.assertEquals(
        "{\"value\":" + rnd + "}",
        HttpUtil.get("http://localhost:" + PortUtil.q() + "/")
      );
      Assertions.assertEquals(
        "{\"value\":" + rnd + "}",
        HttpUtil.post("http://localhost:" + PortUtil.q() + "/", new HashMap<>())
      );
    }
  }

  @Test
  public void testCharWrapper() throws Exception {
    final char rnd = RandomUtil.getRandomCharacter();
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/", new CharHandler(rnd));

      Thread.sleep(10);

      Assertions.assertEquals(
        "{\"value\":\"" + rnd + "\"}",
        HttpUtil.get("http://localhost:" + PortUtil.q() + "/")
      );
      Assertions.assertEquals(
        "{\"value\":\"" + rnd + "\"}",
        HttpUtil.post("http://localhost:" + PortUtil.q() + "/", new HashMap<>())
      );
    }
  }

  @Test
  public void testStringWrapper() throws Exception {
    final String rnd = RandomUtil.getRandomString();
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/", new StringHandler(rnd));

      Thread.sleep(10);

      Assertions.assertEquals(
        "{\"value\":\"" + rnd + "\"}",
        HttpUtil.get("http://localhost:" + PortUtil.q() + "/")
      );
    }
  }


  public static class ByteHandler {
    private final byte value;

    public ByteHandler(byte value) {
      this.value = value;
    }

    @Get
    public byte get() {
      return this.value;
    }

    @Post
    public Byte post() {
      return this.value;
    }
  }

  public static class ShortHandler {
    private final short value;

    public ShortHandler(short value) {
      this.value = value;
    }

    @Get
    public short get() {
      return this.value;
    }

    @Post
    public Short post() {
      return this.value;
    }
  }

  public static class IntHandler {
    private final int value;

    public IntHandler(int value) {
      this.value = value;
    }

    @Get
    public int get() {
      return this.value;
    }

    @Post
    public int post() {
      return this.value;
    }
  }

  public static class LongHandler {
    private final long value;

    public LongHandler(long value) {
      this.value = value;
    }

    @Get
    public long get() {
      return this.value;
    }

    @Post
    public Long post() {
      return this.value;
    }
  }

  public static class FloatHandler {
    private final float value;

    public FloatHandler(float value) {
      this.value = value;
    }

    @Get
    public float get() {
      return this.value;
    }

    @Post
    public Float post() {
      return this.value;
    }
  }

  public static class DoubleHandler {
    private final double value;

    public DoubleHandler(double value) {
      this.value = value;
    }

    @Get
    public double get() {
      return this.value;
    }

    @Post
    public Double post() {
      return this.value;
    }
  }

  public static class BooleanHandler {
    private final boolean value;

    public BooleanHandler(boolean value) {
      this.value = value;
    }

    @Get
    public boolean get() {
      return this.value;
    }

    @Post
    public Boolean post() {
      return this.value;
    }
  }

  public static class CharHandler {
    private final char value;

    public CharHandler(char value) {
      this.value = value;
    }

    @Get
    public char get() {
      return this.value;
    }

    @Post
    public Character post() {
      return this.value;
    }
  }

  public static class StringHandler {
    private final String value;

    public StringHandler(String value) {
      this.value = value;
    }

    @Get
    public String get() {
      return this.value;
    }
  }
}
