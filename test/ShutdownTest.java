import de.troebs.smol.Get;
import de.troebs.smol.SmolHTTPd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.HttpUtil;
import util.PortUtil;
import util.RandomUtil;

public class ShutdownTest {
  @Test
  public void testShutdown() throws Exception {
    final boolean error = RandomUtil.getRandomBoolean();
    final String message = RandomUtil.getRandomString();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/", new Handler(error, message));

      Thread.sleep(10);

      Handler handler = HttpUtil.get("http://localhost:" + PortUtil.q() + "/", Handler.class);
      Assertions.assertEquals(error, handler.error);
      Assertions.assertEquals(message, handler.message);
    }
  }

  public static class Handler {
    public final boolean error;
    public final String message;

    public Handler(boolean error, String message) {
      this.error = error;
      this.message = message;
    }

    public Handler() {
      this(false, null);
    }

    @Get
    public Object get() {
      return this;
    }
  }
}
