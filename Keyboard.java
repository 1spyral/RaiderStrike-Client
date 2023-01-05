import java.util.*;
public class Keyboard {
    private Queue<Character> keysTyped;
    private HashSet<Integer> keysPressed;
    
    Keyboard() {
        this.keysTyped = new LinkedList<Character>();
        this.keysPressed = new HashSet<Integer>();
    }
    public boolean hasNext() {
        return this.keysTyped.size() > 0;
    }
    public char next() {
        return this.keysTyped.poll();
    }
    public void keyTyped(char c) {
        this.keysTyped.add(c);
    }
    public boolean isHeld(int key) {
        return this.keysPressed.contains(key);
    }
    public void keyPressed(int key) {
        this.keysPressed.add(key);
    }
    public void keyReleased(int key) {
        this.keysPressed.remove(key);
    }
}
