import java.util.*;
import java.awt.*;

/**
 * Abstract class for a state of the program 
 */ 
public abstract class State {
    Keyboard keyboard;
    Mouse mouse;
    Messenger messenger;

    private boolean active;
    // The arguments that will be passed onto the next state
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
    // Process inputs
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
    // On message receive
    public abstract void message(String messageText);
    // On key type
    public abstract void type(char key);
    // On mouse click
    public abstract void click(Mouse.Click click);
    public void draw(Graphics g) {
        for (Button button: this.buttons.values()) {
            button.draw(g);
        }
    }
}
