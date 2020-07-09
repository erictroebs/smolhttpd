import de.troebs.smol.Get;
import de.troebs.smol.SmolHTTPd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.HttpUtil;
import util.PortUtil;

public class PriorityTest {
  @Test
  public void testPriority() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final Handler1 handler1 = new Handler1();
      final Handler2 handler2 = new Handler2();

      http.register("/a", handler1);
      http.register("/b", handler2);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(4, handler1.priority);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(4, handler2.priority);
    }
  }

  public static class Handler1 {
    public int priority = 0;

    @Get(priority = 4)
    public void a() {
      this.priority = 4;
    }

    @Get(priority = 2)
    public void b() {
      this.priority = 2;
    }
  }

  public static class Handler2 {
    public int priority = 0;

    @Get(priority = 2)
    public void a() {
      this.priority = 2;
    }

    @Get(priority = 4)
    public void b() {
      this.priority = 4;
    }
  }
}
