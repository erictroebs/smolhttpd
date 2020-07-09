import de.troebs.smol.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.HttpUtil;
import util.PortUtil;

import java.io.IOException;

public class ResponseCodeTest {
  @Test
  public void test200OK() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler("OK"));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(200, x.code);
    }
  }

  @Test
  public void test201Created() throws Exception {
    ExampleResponse re = new ExampleResponse();

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new Created("/201")));
      http.register("/b", new ExceptionHandler(new Created("http://localhost:" + PortUtil.q() + "/201")));
      http.register("/c", new ReturnHandler(new Created(re, "/201")));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(201, x.code);
      Assertions.assertEquals("http://localhost:" + PortUtil.q() + "/201", x.getHeader("Location"));

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(201, y.code);
      Assertions.assertEquals("http://localhost:" + PortUtil.q() + "/201", y.getHeader("Location"));

      ExampleResponse er = HttpUtil.get("http://localhost:" + PortUtil.q() + "/c", ExampleResponse.class);
      Assertions.assertEquals(re.a, er.a);
    }
  }

  @Test
  public void test204NoContent() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new NoContent()));
      http.register("/b", new ExceptionHandler(new NoContent()));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(204, x.code);

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(204, y.code);
    }
  }

  @Test
  public void test300MultipleChoice() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new MultipleChoice("/300")));
      http.register("/b", new ExceptionHandler(new MultipleChoice("http://localhost:" + PortUtil.q() + "/300")));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(300, x.code);
      Assertions.assertEquals("http://localhost:" + PortUtil.q() + "/300", x.getHeader("Location"));

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(300, y.code);
      Assertions.assertEquals("http://localhost:" + PortUtil.q() + "/300", y.getHeader("Location"));
    }
  }

  @Test
  public void test301MovedPermanently() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new MovedPermanently("/301")));
      http.register("/b", new ExceptionHandler(new MovedPermanently("http://localhost:" + PortUtil.q() + "/301")));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(301, x.code);
      Assertions.assertEquals("http://localhost:" + PortUtil.q() + "/301", x.getHeader("Location"));

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(301, y.code);
      Assertions.assertEquals("http://localhost:" + PortUtil.q() + "/301", y.getHeader("Location"));
    }
  }

  @Test
  public void test302Found() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new Found("/302")));
      http.register("/b", new ExceptionHandler(new Found("http://localhost:" + PortUtil.q() + "/302")));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(302, x.code);
      Assertions.assertEquals("http://localhost:" + PortUtil.q() + "/302", x.getHeader("Location"));

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(302, y.code);
      Assertions.assertEquals("http://localhost:" + PortUtil.q() + "/302", y.getHeader("Location"));
    }
  }

  @Test
  public void test303SeeOther() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new SeeOther("/303")));
      http.register("/b", new ExceptionHandler(new SeeOther("http://localhost:" + PortUtil.q() + "/303")));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(303, x.code);
      Assertions.assertEquals("http://localhost:" + PortUtil.q() + "/303", x.getHeader("Location"));

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(303, y.code);
      Assertions.assertEquals("http://localhost:" + PortUtil.q() + "/303", y.getHeader("Location"));
    }
  }

  @Test
  public void test304NotModified() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new NotModified()));
      http.register("/b", new ExceptionHandler(new NotModified()));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(304, x.code);

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(304, y.code);
    }
  }

  @Test
  public void test307TemporaryRedirect() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new TemporaryRedirect("/307")));
      http.register("/b", new ExceptionHandler(new TemporaryRedirect("http://localhost:" + PortUtil.q() + "/307")));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(307, x.code);
      Assertions.assertEquals("http://localhost:" + PortUtil.q() + "/307", x.getHeader("Location"));

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(307, y.code);
      Assertions.assertEquals("http://localhost:" + PortUtil.q() + "/307", y.getHeader("Location"));
    }
  }

  @Test
  public void test308PermanentRedirect() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new PermanentRedirect("/308")));
      http.register("/b", new ExceptionHandler(new PermanentRedirect("http://localhost:" + PortUtil.q() + "/308")));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(308, x.code);
      Assertions.assertEquals("http://localhost:" + PortUtil.q() + "/308", x.getHeader("Location"));

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(308, y.code);
      Assertions.assertEquals("http://localhost:" + PortUtil.q() + "/308", y.getHeader("Location"));
    }
  }


  @Test
  public void test400BadRequest() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new BadRequest()));
      http.register("/b", new ExceptionHandler(new BadRequest()));
      http.register("/c/{int}", new IntHandler());
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(400, x.code);

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(400, y.code);

      HttpUtil.X z = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/c/abc");
      Assertions.assertEquals(400, z.code);
    }
  }

  @Test
  public void test401Unauthorized() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new Unauthorized("401")));
      http.register("/b", new ExceptionHandler(new Unauthorized("401")));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(401, x.code);
      Assertions.assertEquals("Basic realm=401", x.getHeader("Www-authenticate"));

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(401, y.code);
      Assertions.assertEquals("Basic realm=401", y.getHeader("Www-authenticate"));
    }
  }

  @Test
  public void test403Forbidden() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new Forbidden()));
      http.register("/b", new ExceptionHandler(new Forbidden()));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(403, x.code);

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(403, y.code);
    }
  }

  @Test
  public void test404NotFound() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new NotFound()));
      http.register("/b", new ExceptionHandler(new NotFound()));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(404, x.code);

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(404, y.code);

      HttpUtil.X z = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/c");
      Assertions.assertEquals(404, z.code);
    }
  }

  @Test
  public void test405MethodNotAllowed() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new MethodNotAllowed(MethodNotAllowed.POST)));
      http.register("/b", new ExceptionHandler(new MethodNotAllowed(MethodNotAllowed.POST)));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(405, x.code);
      Assertions.assertEquals("POST", x.getHeader("Allow"));

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(405, y.code);
      Assertions.assertEquals("POST", y.getHeader("Allow"));
    }
  }

  @Test
  public void test406NotAcceptable() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new NotAcceptable()));
      http.register("/b", new ExceptionHandler(new NotAcceptable()));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(406, x.code);

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(406, y.code);
    }
  }

  @Test
  public void test407ProxyAuthenticationRequired() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new ProxyAuthenticationRequired("407")));
      http.register("/b", new ExceptionHandler(new ProxyAuthenticationRequired("407")));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(407, x.code);
      Assertions.assertEquals("Basic realm=407", x.getHeader("Proxy-authenticate"));

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(407, y.code);
      Assertions.assertEquals("Basic realm=407", y.getHeader("Proxy-authenticate"));
    }
  }

  @Test
  public void test408RequestTimeout() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new RequestTimeout()));
      http.register("/b", new ExceptionHandler(new RequestTimeout()));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(408, x.code);

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(408, y.code);
    }
  }

  @Test
  public void test409Conflict() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new Conflict()));
      http.register("/b", new ExceptionHandler(new Conflict()));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(409, x.code);

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(409, y.code);
    }
  }

  @Test
  public void test410Gone() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new Gone()));
      http.register("/b", new ExceptionHandler(new Gone()));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(410, x.code);

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(410, y.code);
    }
  }

  @Test
  public void test411LengthRequired() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new LengthRequired()));
      http.register("/b", new ExceptionHandler(new LengthRequired()));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(411, x.code);

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(411, y.code);
    }
  }

  @Test
  public void test412PreconditionFailed() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new PreconditionFailed()));
      http.register("/b", new ExceptionHandler(new PreconditionFailed()));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(412, x.code);

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(412, y.code);
    }
  }

  @Test
  public void test413PayloadTooLarge() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new PayloadTooLarge(413)));
      http.register("/b", new ExceptionHandler(new PayloadTooLarge(413)));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(413, x.code);
      Assertions.assertEquals("413", x.getHeader("Retry-after"));

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(413, y.code);
      Assertions.assertEquals("413", y.getHeader("Retry-after"));
    }
  }

  @Test
  public void test414URITooLong() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new URITooLong()));
      http.register("/b", new ExceptionHandler(new URITooLong()));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(414, x.code);

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(414, y.code);
    }
  }

  @Test
  public void test415UnsupportedMediaType() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new UnsupportedMediaType()));
      http.register("/b", new ExceptionHandler(new UnsupportedMediaType()));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(415, x.code);

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(415, y.code);
    }
  }

  @Test
  public void test416RequestedRangeNotSatisfiable() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new RequestedRangeNotSatisfiable()));
      http.register("/b", new ExceptionHandler(new RequestedRangeNotSatisfiable()));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(416, x.code);

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(416, y.code);
    }
  }

  @Test
  public void test417ExpectationFailed() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new ExpectationFailed()));
      http.register("/b", new ExceptionHandler(new ExpectationFailed()));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(417, x.code);

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(417, y.code);
    }
  }

  @Test
  public void test421MisdirectedRequest() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new MisdirectedRequest()));
      http.register("/b", new ExceptionHandler(new MisdirectedRequest()));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(421, x.code);

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(421, y.code);
    }
  }

  @Test
  public void test422UnprocessableEntity() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new UnprocessableEntity()));
      http.register("/b", new ExceptionHandler(new UnprocessableEntity()));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(422, x.code);

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(422, y.code);
    }
  }

  @Test
  public void test423Locked() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new Locked()));
      http.register("/b", new ExceptionHandler(new Locked()));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(423, x.code);

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(423, y.code);
    }
  }

  @Test
  public void test424FailedDependency() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new FailedDependency()));
      http.register("/b", new ExceptionHandler(new FailedDependency()));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(424, x.code);

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(424, y.code);
    }
  }

  @Test
  public void test426UpgradeRequired() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new UpgradeRequired()));
      http.register("/b", new ExceptionHandler(new UpgradeRequired()));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(426, x.code);

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(426, y.code);
    }
  }

  @Test
  public void test428PreconditionRequired() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new PreconditionRequired()));
      http.register("/b", new ExceptionHandler(new PreconditionRequired()));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(428, x.code);

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(428, y.code);
    }
  }

  @Test
  public void test429TooManyRequests() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new TooManyRequests()));
      http.register("/b", new ExceptionHandler(new TooManyRequests()));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(429, x.code);

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(429, y.code);
    }
  }

  @Test
  public void test431RequestHeaderFieldsTooLarge() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new RequestHeaderFieldsTooLarge()));
      http.register("/b", new ExceptionHandler(new RequestHeaderFieldsTooLarge()));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(431, x.code);

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(431, y.code);
    }
  }

  @Test
  public void test451UnavailableForLegalReasons() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ReturnHandler(new UnavailableForLegalReasons()));
      http.register("/b", new ExceptionHandler(new UnavailableForLegalReasons()));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(451, x.code);

      HttpUtil.X y = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/b");
      Assertions.assertEquals(451, y.code);
    }
  }


  @Test
  public void test500InternalError() throws Exception {
    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      http.register("/a", new ExceptionHandler(new IOException()));
      Thread.sleep(10);

      HttpUtil.X x = HttpUtil.getX("http://localhost:" + PortUtil.q() + "/a");
      Assertions.assertEquals(500, x.code);
    }
  }


  public static class ReturnHandler {
    private final Object response;

    ReturnHandler(Object response) {
      this.response = response;
    }

    @Get
    public Object get() {
      return this.response;
    }
  }

  public static class ExceptionHandler {
    private final Exception response;

    ExceptionHandler(Object response) {
      this.response = (Exception) response;
    }

    @Get
    public Object get() throws Exception {
      throw this.response;
    }
  }

  public static class IntHandler {
    @Get
    public int get(@Param("int") int i) {
      return i;
    }
  }

  public static class ExampleResponse {
    public int a = 1;
  }
}
