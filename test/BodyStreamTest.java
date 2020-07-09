import de.troebs.smol.BodyStream;
import de.troebs.smol.Post;
import de.troebs.smol.SmolHTTPd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.HttpUtil;
import util.PortUtil;
import util.RandomUtil;

import java.io.IOException;
import java.io.InputStream;

public class BodyStreamTest {
  @Test
  public void testStreamParameter() throws Exception {
    final byte[] value = RandomUtil.getRandomBytes(10);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final StreamHandler handler = new StreamHandler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/", value);
      Assertions.assertTrue(handler.used);
      Assertions.assertArrayEquals(value, handler.get);
    }
  }

  public static class StreamHandler {
    public boolean used = false;
    public byte[] get;

    @Post
    public void get(@BodyStream InputStream stream) throws IOException {
      this.used = true;

      this.get = new byte[stream.available()];
      stream.read(this.get);
    }
  }
}
