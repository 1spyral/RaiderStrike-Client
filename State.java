import java.awt.*;
public abstract class State {
    Keyboard keyboard;
    Mouse mouse;
    Messenger messenger;

    State(Keyboard keyboard, Mouse mouse, Messenger messenger) {
        this.keyboard = keyboard;
        this.mouse = mouse;
        this.messenger = messenger;
    }
    public abstract void setup(Object[] args);
    public abstract void update();
    public abstract void draw(Graphics g);
    public abstract void close();
}
