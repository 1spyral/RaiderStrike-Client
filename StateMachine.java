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
        while (true) {
            this.states[this.current].update();
        }
    }
    public void draw(Graphics g) {
        
    }
    public void changeState(int state, Object[] args) {
        this.current = state;
        this.states[current].setup(args);
    }
}
