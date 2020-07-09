package de.troebs.smol;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Base64;

class Handler implements HttpHandler {
  private final String name;
  private final TreeNode head;
  private final Base64.Decoder base64;
  private final ObjectMapper json;


  Handler(String name, boolean indent) {
    this.name = name;
    this.head = new TreeNode("/");
    this.base64 = Base64.getDecoder();
    this.json = new ObjectMapper();

    if (indent)
      this.json.enable(SerializationFeature.INDENT_OUTPUT);
  }


  private void register(String[] uri, int a, TreeNode current, Object handler) {
    if (a == uri.length)
      return;

    // find existing named node
    for (TreeNode n : current.named) {
      if (uri[a].equals(n.name)) {
        if (a + 1 == uri.length)
          n.handlers.add(handler);
        else
          this.register(uri, a + 1, n, handler);

        return;
      }
    }

    // find existing typed node
    for (TreeNode n : current.typed) {
      if (uri[a].equals("{" + n.name + "}")) {
        if (a + 1 == uri.length)
          n.handlers.add(handler);
        else
          this.register(uri, a + 1, n, handler);

        return;
      }
    }

    // insert new node
    TreeNode target;

    if (uri[a].startsWith("{") && uri[a].endsWith("}")) {
      target = new TreeNode(uri[a].substring(1, uri[a].length() - 1), a == uri.length - 1 ? handler : null);
      current.typed.add(target);
    }
    else {
      target = new TreeNode(uri[a], a == uri.length - 1 ? handler : null);
      current.named.add(target);
    }

    this.register(uri, a + 1, target, handler);
  }

  public void register(String path, Object handler) {
    final String[] uri = path.substring(1).split("/");
    this.register(uri, 0, this.head, handler);
  }


  private void handle(String[] uri, int a, TreeNode current, HandlerList result) {
    if (a < uri.length) {
      // find named node
      for (TreeNode n : current.named) {
        if (n.name.equals(uri[a])) {
          if (a == uri.length - 1) {
            result.addHandler(n);
          }
          else {
            this.handle(uri, a + 1, n, result);
          }
        }
      }

      // save value
      for (TreeNode n : current.typed) {
        result.pushValue(n.name, uri[a]);

        if (a == uri.length - 1) {
          result.addHandler(n);
        }
        else {
          this.handle(uri, a + 1, n, result);
        }

        result.popValue();
      }
    }
  }

  @Override
  public void handle(HttpExchange he) throws IOException {
    // prepare request details
    final String[] uri = he.getRequestURI().getPath().substring(1).split("/");

    final MethodFilter filter = new MethodFilter(he.getRequestMethod());
    final ParameterMap headers = new ParameterMap(he.getRequestHeaders());
    ParameterMap body = null;

    // find handler
    final HandlerList result = new HandlerList();
    this.handle(uri, 0, this.head, result);

    // invoke method
    boolean invoked = false;
    Object response = null;

    loop:
    for (HandlerList.Wrapper w : result) {
      for (Method method : filter.getEligibleMethods(w.handler)) {
        try {
          final Parameter[] parameters = method.getParameters();
          final Object[] params = new Object[parameters.length];

          for (int i = 0; i < parameters.length; i++) {
            final Parameter parameter = parameters[i];

            if (parameter.isAnnotationPresent(Param.class)) {
              w.values.addTo(
                params,
                i,
                parameter.getAnnotationsByType(Param.class)[0].value(),
                parameter.getType()
              );
            }
            else if (parameter.isAnnotationPresent(Header.class)) {
              final String name = parameter.getAnnotationsByType(Header.class)[0].value();

              if (name.equals("Authorization") && parameter.getType() == BasicAuthCredentials.class) {
                params[i] = new BasicAuthCredentials(this.base64, headers.toString("Authorization", false));
              }
              else {
                headers.addTo(
                  params,
                  i,
                  name,
                  parameter.getType()
                );
              }
            }
            else if (parameter.isAnnotationPresent(BodyParam.class)) {
              if (body == null)
                body = new ParameterMap(he.getRequestBody());

              body.addTo(
                params,
                i,
                parameter.getAnnotationsByType(BodyParam.class)[0].value(),
                parameter.getType()
              );
            }
            else if (parameter.isAnnotationPresent(BodyStream.class)) {
              params[i] = he.getRequestBody();
            }
            else if (parameter.isAnnotationPresent(BodyBytes.class)) {
              InputStream stream = he.getRequestBody();
              byte[] data = new byte[stream.available()];
              stream.read(data);
              params[i] = data;
            }
            else if (parameter.isAnnotationPresent(BodyString.class)) {
              InputStream stream = he.getRequestBody();
              byte[] data = new byte[stream.available()];
              stream.read(data);
              params[i] = new String(data);
            }
            else if (parameter.isAnnotationPresent(BodyJSON.class)) {
              params[i] = this.json.readValue(he.getRequestBody(), parameter.getType());
            }
          }

          response = method.invoke(w.handler, params);
          invoked = true;
          break loop;
        }
        catch (Exception e) {
          if (e instanceof ParameterMap.ParseException) {
            response = new BadRequest();
            invoked = true;
          }
          else if (e.getCause() instanceof StatusException) {
            response = e.getCause();
            invoked = true;
            break loop;
          }
          else {
            response = e;
            break loop;
          }
        }
      }
    }

    // send response
    if (invoked) {
      if (response == null) {
        Handler.send(he, 400, new byte[0]);
      }
      else {
        if (response instanceof StatusException) {
          for (StatusException.Header header : ((StatusException) response).headers)
            if (header.name.equalsIgnoreCase("location") && !header.value.matches("https?://.*")) {
              he.getResponseHeaders().add(
                header.name,
                "http://" + this.getHost(he) + (header.value.startsWith("/") ? "" : "/") + header.value
              );
            }
            else {
              he.getResponseHeaders().add(header.name, header.value);
            }

          if (response instanceof Created)
            Handler.send(he, ((StatusException) response).code, this.json.writeValueAsBytes(((Created) response).body));
          else
            Handler.send(he, ((StatusException) response).code, new byte[0]);
        }
        else if (response instanceof Number) {
          response = new Wrapper<>((Number) response);
        }
        else if (response instanceof Boolean) {
          response = new Wrapper<>((Boolean) response);
        }
        else if (response instanceof Character) {
          response = new Wrapper<>((Character) response);
        }
        else if (response instanceof String) {
          response = new Wrapper<>((String) response);
        }

        Handler.send(he, 200, this.json.writeValueAsBytes(response));
      }
    }
    else {
      if (response == null)
        Handler.send(he, 404, new byte[0]);
      else
        Handler.send(he, 500, new byte[0]);
    }
  }


  private static void send(HttpExchange he, int code, byte[] response) throws IOException {
    // send headers
    he.getResponseHeaders().add("Server", "SmolHTTPd/" + SmolHTTPd.BUILD);
    he.getResponseHeaders().add("Content-Type", "application/json");
    he.sendResponseHeaders(code, response.length > 0 ? response.length : -1);

    // send body
    try (OutputStream os = he.getResponseBody()) {
      os.write(response);
    }
  }

  private String getHost(HttpExchange he) {
    if (he.getRequestHeaders().containsKey("Host"))
      return he.getRequestHeaders().getFirst("Host");
    else if (this.name != null)
      return this.name;
    else
      return he.getLocalAddress().getHostString();
  }
}
