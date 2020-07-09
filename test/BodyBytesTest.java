import de.troebs.smol.BodyBytes;
import de.troebs.smol.Post;
import de.troebs.smol.SmolHTTPd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.HttpUtil;
import util.PortUtil;
import util.RandomUtil;

import java.io.IOException;

public class BodyBytesTest {
  @Test
  public void testBytesParameter() throws Exception {
    final byte[] value = RandomUtil.getRandomBytes(10);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final BytesHandler handler = new BytesHandler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/", value);
      Assertions.assertTrue(handler.used);
      Assertions.assertArrayEquals(value, handler.get);
    }
  }

  public static class BytesHandler {
    public boolean used = false;
    public byte[] get;

    @Post
    public void get(@BodyBytes byte[] param) throws IOException {
      this.used = true;
      this.get = param;
    }
  }
}
