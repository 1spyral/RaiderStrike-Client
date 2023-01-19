import java.util.*;
import java.awt.*;

public class LoadState extends State {
    int id;
    Player[] players;

    Map map;

    LoadState(Keyboard keyboard, Mouse mouse, Messenger messenger) {
        super(keyboard, mouse, messenger);
    }
    public void setup(Object[] args) {
        super.setup();
        this.id = (int)args[0];
        this.players = (Player[])args[1];
    }
    public void update() {

    }
    public void draw(Graphics g) {

    }
    public void close() {
        super.close();
    }
    private class Loader extends Thread {
        Queue<String> messages;

        Loader() {
            this.messages = new LinkedList<String>();
        }

        public void run() {
            while (!this.messages.isEmpty()) {};
            map.setName(messages.poll());
        }
    }
}
