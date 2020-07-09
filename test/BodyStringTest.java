import de.troebs.smol.BodyString;
import de.troebs.smol.Post;
import de.troebs.smol.SmolHTTPd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.HttpUtil;
import util.PortUtil;
import util.RandomUtil;

import java.io.IOException;

public class BodyStringTest {
  @Test
  public void testStringParameter() throws Exception {
    final String value = RandomUtil.getRandomString();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final StringHandler handler = new StringHandler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/", value.getBytes());
      Assertions.assertTrue(handler.used);
      Assertions.assertEquals(value, handler.get);
    }
  }

  public static class StringHandler {
    public boolean used = false;
    public String get;

    @Post
    public void get(@BodyString String param) throws IOException {
      this.used = true;
      this.get = param;
    }
  }
}
