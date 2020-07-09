package de.troebs.smol;

import java.util.LinkedList;
import java.util.List;

class StatusException extends Exception {
  final int code;
  final List<Header> headers;

  StatusException(int code) {
    this.code = code;
    this.headers = new LinkedList<>();
  }

  final void addHeader(String name, String value) {
    this.headers.add(new Header(name, value));
  }

  final class Header {
    final String name;
    final String value;

    private Header(String name, String value) {
      this.name = name;
      this.value = value;
    }
  }
}
