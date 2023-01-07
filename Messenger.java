import java.util.*;

/**
 * Class that communicates between a Server and StateMachine.
 * This is made to prevent either class to have too much power over the other.
 */
public class Messenger extends LinkedList<String> {
    Server server;

    public void setServer(Server server) {
        this.server = server;
    }
    public void print(String x) {
        this.server.print(x);
    }
}
