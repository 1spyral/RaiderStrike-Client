import java.util.*;
import java.awt.event.*;

/**
 * Keyboard class that receives key input for the client
 */
public class Keyboard implements KeyListener {
    private Queue<Character> keysTyped;
    private HashSet<Integer> keysPressed;
    
    Keyboard() {
        this.keysTyped = new LinkedList<Character>();
        this.keysPressed = new HashSet<Integer>();
    }
    public boolean hasNext() {
        return !this.keysTyped.isEmpty();
    }
    public char next() {
        return this.keysTyped.poll();
    }
    protected void keyTyped(char c) {
        this.keysTyped.add(c);
    }
    public boolean isHeld(int key) {
        return this.keysPressed.contains(key);
    }
    protected void keyPressed(int key) {
        this.keysPressed.add(key);
    }
    protected void keyReleased(int key) {
        this.keysPressed.remove(key);
    }
    // Inherited from KeyListener
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        this.keyPressed(key);
    }
    public void keyReleased(KeyEvent e) { 
        int key = e.getKeyCode();
        this.keyReleased(key);
    }   
    public void keyTyped(KeyEvent e) {
        char keyChar = e.getKeyChar();
        this.keyTyped(keyChar);
    }              
}
