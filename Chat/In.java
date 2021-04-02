package Chat;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public final class In {
  private static final String CHARSET_NAME = "UTF-8";
  private static final Locale LOCALE = Locale.US;

  private Scanner scanner;

  public In(Socket socket) {
    if (socket == null)
      throw new IllegalArgumentException(" socket es nulo ");
    try {
      InputStream is = socket.getInputStream();
      scanner = new Scanner(new BufferedInputStream(is), CHARSET_NAME);
      scanner.useLocale(LOCALE);
    } catch (IOException ioe) {
      throw new IllegalArgumentException("No abre el socket " + socket, ioe);
    }
  }

  public String readLine() {
    String line;
    try {
      line = scanner.nextLine();
    } catch (NoSuchElementException e) {
      line = null;
    }
    return line;
  }

  public void close() {
    scanner.close();
  }
}