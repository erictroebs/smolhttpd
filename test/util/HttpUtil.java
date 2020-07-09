package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class HttpUtil {
  public HttpUtil() {
    throw new AssertionError();
  }

  private static CloseableHttpClient createClient() {
    return HttpClientBuilder
      .create()
      .disableAuthCaching()
      .disableAutomaticRetries()
      .disableConnectionState()
      .disableCookieManagement()
      .disableRedirectHandling()
      .build();
  }

  private static <T> T execute(HttpRequestBase hrb, Map<String, String> headers, Class<T> cl) throws IOException {
    for (String key : headers.keySet())
      hrb.addHeader(key, headers.get(key));

    try (
      CloseableHttpClient client = HttpUtil.createClient();
      CloseableHttpResponse response = client.execute(hrb)
    ) {
      if (cl == String.class) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
          StringBuilder result = new StringBuilder();
          String line;

          while ((line = reader.readLine()) != null)
            result.append(line);

          return (T) result.toString();
        }
      }
      else {
        return new ObjectMapper().readValue(response.getEntity().getContent(), cl);
      }
    }
  }


  public static <T> T get(String url, Map<String, String> headers, Class<T> cl) throws IOException {
    HttpGet get = new HttpGet(url);
    return HttpUtil.execute(get, headers, cl);
  }

  public static <T> T get(String url, Class<T> cl) throws IOException {
    return HttpUtil.get(url, new HashMap<>(), cl);
  }

  public static String get(String url, Map<String, String> headers) throws IOException {
    return HttpUtil.get(url, headers, String.class);
  }

  public static String get(String url) throws IOException {
    return HttpUtil.get(url, String.class);
  }

  public static <T> T getf(String url, Map<String, String> headers, Class<T> cl, Object... args) throws IOException {
    return HttpUtil.get(String.format(url, args), headers, cl);
  }

  public static <T> T getf(String url, Class<T> cl, Object... args) throws IOException {
    return HttpUtil.get(String.format(url, args), cl);
  }

  public static String getf(String url, Map<String, String> headers, Object... args) throws IOException {
    return HttpUtil.get(String.format(url, args), String.class);
  }

  public static String getf(String url, Object... args) throws IOException {
    return HttpUtil.getf(url, String.class, args);
  }


  public static <T> T post(String url, Map<String, String> data, Class<T> cl) throws IOException {
    HttpPost post = new HttpPost(url);

    List<NameValuePair> nvp = new LinkedList<>();
    for (String key : data.keySet())
      nvp.add(new BasicNameValuePair(key, data.get(key)));
    post.setEntity(new UrlEncodedFormEntity(nvp));

    return HttpUtil.execute(post, new HashMap<>(), cl);
  }

  public static String post(String url, Map<String, String> data) throws IOException {
    return HttpUtil.post(url, data, String.class);
  }

  public static <T> T postf(String url, Map<String, String> data, Class<T> cl, Object... args) throws IOException {
    return HttpUtil.post(String.format(url, args), data, cl);
  }

  public static String postf(String url, Map<String, String> data, Object... args) throws IOException {
    return HttpUtil.postf(url, data, String.class, args);
  }


  public static <T> T post(String url, byte[] data, Class<T> cl) throws IOException {
    HttpPost post = new HttpPost(url);
    post.setEntity(new ByteArrayEntity(data));

    return HttpUtil.execute(post, new HashMap<>(), cl);
  }

  public static String post(String url, byte[] data) throws IOException {
    return HttpUtil.post(url, data, String.class);
  }

  public static <T> T postf(String url, byte[] data, Class<T> cl, Object... args) throws IOException {
    return HttpUtil.post(String.format(url, args), data, cl);
  }

  public static String postf(String url, byte[] data, Object... args) throws IOException {
    return HttpUtil.postf(url, data, String.class, args);
  }


  public static <T> T put(String url, Class<T> cl) throws IOException {
    HttpPut put = new HttpPut(url);
    return HttpUtil.execute(put, new HashMap<>(), cl);
  }

  public static String put(String url) throws IOException {
    return HttpUtil.put(url, String.class);
  }

  public static <T> T putf(String url, Class<T> cl, Object... args) throws IOException {
    return HttpUtil.put(String.format(url, args), cl);
  }

  public static String putf(String url, Object... args) throws IOException {
    return HttpUtil.putf(url, String.class, args);
  }


  public static <T> T patch(String url, Class<T> cl) throws IOException {
    HttpPatch patch = new HttpPatch(url);
    return HttpUtil.execute(patch, new HashMap<>(), cl);
  }

  public static String patch(String url) throws IOException {
    return HttpUtil.patch(url, String.class);
  }

  public static <T> T patchf(String url, Class<T> cl, Object... args) throws IOException {
    return HttpUtil.patch(String.format(url, args), cl);
  }

  public static String patchf(String url, Object... args) throws IOException {
    return HttpUtil.patchf(url, String.class, args);
  }


  public static <T> T delete(String url, Class<T> cl) throws IOException {
    HttpDelete put = new HttpDelete(url);
    return HttpUtil.execute(put, new HashMap<>(), cl);
  }

  public static String delete(String url) throws IOException {
    return HttpUtil.delete(url, String.class);
  }

  public static <T> T deletef(String url, Class<T> cl, Object... args) throws IOException {
    return HttpUtil.delete(String.format(url, args), cl);
  }

  public static String deletef(String url, Object... args) throws IOException {
    return HttpUtil.deletef(url, String.class, args);
  }


  public static void head(String url) throws IOException {
    HttpHead head = new HttpHead(url);
    try (
      CloseableHttpClient client = HttpUtil.createClient();
      CloseableHttpResponse response = client.execute(head)
    ) {
    }
  }


  public static class X {
    public final int code;
    public final Header[] header;

    X(HttpResponse response) {
      this.code = response.getStatusLine().getStatusCode();
      this.header = response.getAllHeaders();
    }

    public String getHeader(String name) {
      for (Header header : this.header)
        if (header.getName().equals(name))
          return header.getValue();

      return null;
    }
  }

  public static X getX(String url) throws IOException {
    HttpGet get = new HttpGet(url);

    try (
      CloseableHttpClient client = HttpUtil.createClient();
      CloseableHttpResponse response = client.execute(get)
    ) {
      return new X(response);
    }
  }
}
