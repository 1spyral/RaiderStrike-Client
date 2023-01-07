import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
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
        this.states = new State[]{new MenuState(), new LoadState(), new GameState()};
    }
    public void update() {
        while (true) {
            current.update();
        }
    }
    public void draw(Graphics g) {
        
    }
    public void changeState() {

    }
}
