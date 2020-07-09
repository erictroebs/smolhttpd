package de.troebs.smol;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class SmolHTTPd implements AutoCloseable {
  public static final String BUILD = "indev";

  private final HttpServer server;
  private final Handler handler;

  public SmolHTTPd(int port, String name, boolean indent) throws IOException {
    // create objects
    this.server = HttpServer.create(new InetSocketAddress(port), 0);
    this.handler = new Handler(name, indent);

    // start server
    this.server.createContext("/", this.handler);
    this.server.setExecutor(null);
    this.server.start();
  }

  public SmolHTTPd(int port, String name) throws IOException {
    this(port, name, false);
  }

  public SmolHTTPd(int port, boolean indent) throws IOException {
    this(port, null, indent);
  }

  public SmolHTTPd(int port) throws IOException {
    this(port, false);
  }

  public void register(String path, Object handler) {
    this.handler.register(path, handler);
  }

  public void register(Object handler, String... paths) {
    for (String path : paths)
      this.register(path, handler);
  }

  @Override
  public void close() {
    this.server.stop(0);
  }
}
