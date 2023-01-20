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
    public void update() {
        while (!this.messenger.isEmpty() && this.isActive()) {
            this.message(this.messenger.poll());
        }
        while (this.keyboard.hasNext() && this.isActive()) {
            this.type(this.keyboard.next());
        }
        while (this.mouse.hasNext() && this.isActive()) {
            this.click(this.mouse.poll());
        }
    }
    public abstract void message(String messageText);
    public abstract void type(char key);
    public abstract void click(Mouse.Click click);
    public void draw(Graphics g) {
        for (Button button: this.buttons.values()) {
            button.draw(g);
        }
    }
}
