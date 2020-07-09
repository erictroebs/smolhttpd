import com.fasterxml.jackson.databind.ObjectMapper;
import de.troebs.smol.BodyJSON;
import de.troebs.smol.Post;
import de.troebs.smol.SmolHTTPd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.HttpUtil;
import util.PortUtil;
import util.RandomUtil;

import java.io.IOException;

public class BodyJSONTest {
  @Test
  public void testJSONParameter() throws Exception {
    final Request value = new Request(
      RandomUtil.getRandomInteger(),
      RandomUtil.getRandomString()
    );

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final JSONHandler handler = new JSONHandler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/", new ObjectMapper().writeValueAsBytes(value));
      Assertions.assertTrue(handler.used);
      Assertions.assertEquals(value.id, handler.get.id);
      Assertions.assertEquals(value.message, handler.get.message);
    }
  }

  public static class JSONHandler {
    public boolean used = false;
    public Request get;

    @Post
    public void get(@BodyJSON Request param) throws IOException {
      this.used = true;
      this.get = param;
    }
  }

  public static class Request {
    public int id;
    public String message;

    public Request(int id, String message) {
      this.id = id;
      this.message = message;
    }

    public Request() {
    }
  }
}
