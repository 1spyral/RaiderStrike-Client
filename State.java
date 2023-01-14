import java.util.*;
import java.awt.*;

public abstract class State {
    Keyboard keyboard;
    Mouse mouse;
    Messenger messenger;

    private boolean active;
    protected Object[] nextArgs;

    protected HashMap<String, Button> buttons;

    State(Keyboard keyboard, Mouse mouse, Messenger messenger) {
        this.keyboard = keyboard;
        this.mouse = mouse;
        this.messenger = messenger;

        this.buttons = new HashMap<String, Button>();

        this.active = false;
    }
    public boolean isActive() {
        return this.active;
    }
    public Object[] next() {
        return this.nextArgs;
    }
    public void setup() {
        this.active = true;
    }
    public void close() {
        this.active = false;
    }
    public abstract void setup(Object[] args);
    public abstract void update();
    public void draw(Graphics g) {
        for (Button button: this.buttons.values()) {
            button.draw(g);
        }
    }
}
