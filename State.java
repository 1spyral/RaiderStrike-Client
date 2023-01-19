import java.util.*;
import java.awt.*;

public abstract class State {
    Keyboard keyboard;
    Mouse mouse;
    Messenger messenger;

    private boolean active;
    private Object[] nextArgs;

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
    public Object[] getNextArgs() {
        return this.nextArgs;
    }
    public void setNextArgs(Object[] nextArgs) {
        this.nextArgs = nextArgs;
    }
    public void setup() {
        this.active = true;
        this.nextArgs = null;
    }
    public void close() {
        this.buttons.clear();
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
