import de.troebs.smol.Get;
import de.troebs.smol.Param;
import de.troebs.smol.Post;
import de.troebs.smol.SmolHTTPd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.HttpUtil;
import util.PortUtil;
import util.RandomUtil;

import java.util.HashMap;

public class HandlerTest {
  @Test
  public void testSingleNamedHandler() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final GetHandler handler = new GetHandler();
      http.register("/", handler);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/");
      Assertions.assertTrue(handler.used);
    }
  }

  @Test
  public void testMultipleNamedHandlers() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final GetHandler handler0 = new GetHandler();
      final GetHandler handler1 = new GetHandler();

      http.register("/a", handler0);
      http.register("/b", handler1);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertTrue(handler0.used);
      Assertions.assertFalse(handler1.used);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertTrue(handler0.used);
      Assertions.assertTrue(handler1.used);
    }
  }

  @Test
  public void testLongPathNamedHandler() throws Exception {
    String path = "";
    for (int i = 0; i < 5; i++)
      path += "/" + RandomUtil.getRandomString();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final GetHandler handler = new GetHandler();
      http.register(path, handler);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + path);
      Assertions.assertTrue(handler.used);
    }
  }

  @Test
  public void testOverlappingNamedHandlers() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final GetHandler get0 = new GetHandler();
      final GetHandler get1 = new GetHandler();
      final PostHandler post = new PostHandler();

      http.register("/", get0);
      http.register("/", get1);
      http.register("/", post);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/");
      Assertions.assertTrue(get0.used);
      Assertions.assertFalse(get1.used);
      Assertions.assertFalse(post.used);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/", new HashMap<>());
      Assertions.assertTrue(get0.used);
      Assertions.assertFalse(get1.used);
      Assertions.assertTrue(post.used);
    }
  }


  @Test
  public void testSingleTypedHandler() throws Exception {
    final int id = RandomUtil.getRandomInteger();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final GetIdHandler handler = new GetIdHandler();
      http.register("/{id}", handler);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + id);
      Assertions.assertTrue(handler.used);
      Assertions.assertEquals(id, handler.id);
    }
  }

  @Test
  public void testMultipleTypedHandlers() throws Exception {
    final int id0 = RandomUtil.getRandomInteger();
    final int id1 = RandomUtil.getRandomInteger();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final GetIdHandler handler0 = new GetIdHandler();
      final GetIdHandler handler1 = new GetIdHandler();

      http.register("/a/{id}", handler0);
      http.register("/b/{id}", handler1);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/a/" + id0);
      Assertions.assertTrue(handler0.used);
      Assertions.assertEquals(id0, handler0.id);
      Assertions.assertFalse(handler1.used);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/b/" + id1);
      Assertions.assertTrue(handler0.used);
      Assertions.assertEquals(id0, handler0.id);
      Assertions.assertTrue(handler1.used);
      Assertions.assertEquals(id1, handler1.id);
    }
  }

  @Test
  public void testLongPathTypedHandler() throws Exception {
    final int id = RandomUtil.getRandomInteger();

    String path = "";
    for (int i = 0; i < 5; i++)
      path += "/" + (i == 2 ? "{id}" : RandomUtil.getRandomString());

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final GetIdHandler handler = new GetIdHandler();
      http.register(path, handler);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + path.replaceAll("\\{id\\}", String.valueOf(id)));
      Assertions.assertTrue(handler.used);
      Assertions.assertEquals(id, handler.id);
    }
  }

  @Test
  public void testOverlappingTypedHandlers() throws Exception {
    final int id0 = RandomUtil.getRandomInteger();
    final int id1 = RandomUtil.getRandomInteger();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final GetIdHandler get0 = new GetIdHandler();
      final GetIdHandler get1 = new GetIdHandler();
      final PostIdHandler post = new PostIdHandler();

      http.register("/{id}", get0);
      http.register("/{id}", get1);
      http.register("/{id}", post);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + id0);
      Assertions.assertTrue(get0.used);
      Assertions.assertFalse(get1.used);
      Assertions.assertFalse(post.used);
      Assertions.assertEquals(id0, get0.id);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/" + id1, new HashMap<>());
      Assertions.assertTrue(get0.used);
      Assertions.assertFalse(get1.used);
      Assertions.assertTrue(post.used);
      Assertions.assertEquals(id0, get0.id);
      Assertions.assertEquals(id1, post.id);
    }
  }


  @Test
  public void testOverlappingHandlers1() throws Exception {
    final int id = RandomUtil.getRandomInteger();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final GetHandler get = new GetHandler();
      final PostIdHandler post = new PostIdHandler();

      http.register("/" + id, get);
      http.register("/{id}", post);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + id);
      Assertions.assertTrue(get.used);
      Assertions.assertFalse(post.used);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/" + id, new HashMap<>());
      Assertions.assertTrue(get.used);
      Assertions.assertTrue(post.used);
      Assertions.assertEquals(id, post.id);
    }
  }

  @Test
  public void testOverlappingHandlers2() throws Exception {
    final int id = RandomUtil.getRandomInteger();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final GetIdHandler get = new GetIdHandler();
      final PostHandler post = new PostHandler();

      http.register("/{id}", get);
      http.register("/" + id, post);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/" + id);
      Assertions.assertTrue(get.used);
      Assertions.assertFalse(post.used);
      Assertions.assertEquals(id, get.id);

      Thread.sleep(10);

      HttpUtil.post("http://localhost:" + PortUtil.q() + "/" + id, new HashMap<>());
      Assertions.assertTrue(get.used);
      Assertions.assertTrue(post.used);
    }
  }

  @Test
  public void testMultipleRegisteredHandlers() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final GetHandler handler = new GetHandler();

      http.register(handler, "/a", "/b");

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertTrue(handler.used);

      handler.used = false;
      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertTrue(handler.used);
    }
  }


  public static class GetHandler {
    public boolean used = false;

    @Get
    public void get() {
      this.used = true;
    }
  }

  public static class PostHandler {
    public boolean used = false;

    @Post
    public void post() {
      this.used = true;
    }
  }

  public static class GetIdHandler {
    public boolean used = false;
    public int id;

    @Get
    public void get(@Param("id") int id) {
      this.used = true;
      this.id = id;
    }
  }

  public static class PostIdHandler {
    public boolean used = false;
    public int id;

    @Post
    public void post(@Param("id") int id) {
      this.used = true;
      this.id = id;
    }
  }
}
