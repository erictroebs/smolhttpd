import de.troebs.smol.BasicAuthCredentials;
import de.troebs.smol.Get;
import de.troebs.smol.Header;
import de.troebs.smol.SmolHTTPd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.HttpUtil;
import util.PortUtil;
import util.RandomUtil;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class AuthorizationTest {
  @Test
  public void testAuthorization() throws Exception {
    final String username = RandomUtil.getRandomString();
    final String password = RandomUtil.getRandomString();
    final String auth = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

    final Map<String, String> headers = new HashMap<>();
    headers.put("Authorization", auth);

    try (SmolHTTPd http = new SmolHTTPd(PortUtil.p())) {
      final StringHandler handler0 = new StringHandler();
      final AuthHandler handler1 = new AuthHandler();

      http.register("/a", handler0);
      http.register("/b", handler1);

      Thread.sleep(10);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/a", headers);
      Assertions.assertEquals(auth, handler0.auth);

      HttpUtil.get("http://localhost:" + PortUtil.q() + "/b", headers);
      Assertions.assertEquals(username, handler1.username);
      Assertions.assertEquals(password, handler1.password);
    }
  }

  public static class StringHandler {
    public String auth = null;

    @Get
    public void get(@Header("Authorization") String auth) {
      this.auth = auth;
    }
  }

  public static class AuthHandler {
    public String username;
    public String password;

    @Get
    public void get(@Header("Authorization") BasicAuthCredentials auth) {
      this.username = auth.username;
      this.password = auth.password;
    }
  }
}
