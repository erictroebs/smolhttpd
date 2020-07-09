import de.troebs.smol.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.HttpUtil;
import util.PortUtil;

import java.util.HashMap;

public class MethodTest {
  @Test
  public void testGet() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final Handler handler = new Handler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/");
      Assertions.assertEquals(Handler.GET, handler.method);
    }
  }

  @Test
  public void testPost() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final Handler handler = new Handler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/", new HashMap<>());
      Assertions.assertEquals(Handler.POST, handler.method);
    }
  }

  @Test
  public void testPut() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final Handler handler = new Handler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.put("http://localhost:" + PortUtil.q() + "/");
      Assertions.assertEquals(Handler.PUT, handler.method);
    }
  }

  @Test
  public void testPatch() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final Handler handler = new Handler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.patch("http://localhost:" + PortUtil.q() + "/");
      Assertions.assertEquals(Handler.PATCH, handler.method);
    }
  }

  @Test
  public void testDelete() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final Handler handler = new Handler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.delete("http://localhost:" + PortUtil.q() + "/");
      Assertions.assertEquals(Handler.DELETE, handler.method);
    }
  }

  @Test
  public void testUnsupported() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final Handler handler = new Handler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.head("http://localhost:" + PortUtil.q() + "/");
      Assertions.assertEquals(0, handler.method);
    }
  }

  public static class Handler {
    public static final int GET = 1;
    public static final int POST = 2;
    public static final int PUT = 3;
    public static final int PATCH = 4;
    public static final int DELETE = 5;

    public int method = 0;

    @Get
    public void get() {
      this.method = GET;
    }

    @Post
    public void post() {
      this.method = POST;
    }

    @Put
    public void put() {
      this.method = PUT;
    }

    @Patch
    public void patch() {
      this.method = PATCH;
    }

    @Delete
    public void delete() {
      this.method = DELETE;
    }
  }
}
