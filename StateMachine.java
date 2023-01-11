import java.awt.*;
public class StateMachine {
    Keyboard keyboard;
    Mouse mouse;
    Messenger messenger;

    int current;
    State[] states;

    StateMachine(Keyboard keyboard, Mouse mouse, Messenger messenger) {
        this.keyboard = keyboard;
        this.mouse = mouse;
        this.messenger = messenger;

        this.current = 0;
        this.states = new State[]{
            new MenuState(this.keyboard, this.mouse, this.messenger), 
            new LoadState(this.keyboard, this.mouse, this.messenger), 
            new GameState(this.keyboard, this.mouse, this.messenger)
        };

        this.states[this.current].setup(new Object[]{});
    }
    public void update() {
        // Update the current state
        this.states[this.current].update();
        // Switch to the next state if the current one has closed
        if (!this.states[current].isActive()) {
            this.nextState();
        }
    }
    public void draw(Graphics g) {
        // Render the current state
        this.states[this.current].draw(g);
    }
    public void nextState() {
        // Arguments passed from previous state to next state
        Object[] args = this.states[current].next();
        this.current++;
        this.states[current].setup(args);
    }
}
