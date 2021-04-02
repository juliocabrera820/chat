package Chat;

import java.util.Vector;

public class ConnectionListener extends Thread {
  private Vector<Connection> connections;

  public ConnectionListener(Vector<Connection> connections) {
    this.connections = connections;
  }

  public void run() {
    while (true) {
      for (int i = 0; i < connections.size(); i++) {
        Connection ith = connections.get(i);
        if (!ith.isAlive())
          connections.remove(i);
        String message = ith.getMessage();
        if (message != null)
          for (Connection jth : connections)
            jth.println(message);
      }
      try {
        Thread.sleep(100);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}