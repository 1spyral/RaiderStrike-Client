import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
public class StateMachine {
    Keyboard keyboard;
    Mouse mouse;
    Messenger messenger;

    StateMachine(Keyboard keyboard, Mouse mouse, Messenger messenger) {
        this.keyboard = keyboard;
        this.mouse = mouse;
        this.messenger = messenger;
    }
    public void update() {
        
    }
    public void draw(Graphics g) {

    }
}
