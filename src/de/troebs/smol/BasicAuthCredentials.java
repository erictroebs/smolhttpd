package de.troebs.smol;

import java.util.Base64;

public final class BasicAuthCredentials {
  public final String username;
  public final String password;

  BasicAuthCredentials(String username, String password) {
    this.username = username;
    this.password = password;
  }

  BasicAuthCredentials(Base64.Decoder decoder, String header) {
    if (header != null) {
      final String[] k = header.split(" ", 2);

      if (k.length == 2 && k[0].equals("Basic")) {
        final String[] data = new String(decoder.decode(k[1])).split(":");

        if (data.length == 2) {
          this.username = data[0];
          this.password = data[1];

          return;
        }
      }
    }

    this.username = null;
    this.password = null;
  }
}
