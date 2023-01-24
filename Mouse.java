import java.util.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Class that keeps track of mouse motion and input
 */
public class Mouse extends Point implements MouseListener, MouseMotionListener {
    private Queue<Click> clicks;
    private boolean[] pressed;
    
    Mouse() {
        super(-1, -1);
        this.clicks = new LinkedList<Click>();
        this.pressed = new boolean[17];
    }

    public boolean hasNext() {
        return !this.clicks.isEmpty();
    }
    public Click poll() {
        return this.clicks.poll();
    }
    public boolean isPressed(int button) {
        try {
            return this.pressed[button];
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public class Click extends Point {
        private int button;

        Click(int button, int x, int y) {
            super(x, y);
            this.button = button;
        }
        
        public int getButton() {
            return this.button;
        }
    }

    /* Inherited from MouseListener */
    public void mouseClicked(MouseEvent e) {
        this.clicks.add(new Click(e.getButton(), e.getX(), e.getY()));
    }
    public void mousePressed(MouseEvent e) {
        try {
            this.pressed[e.getButton()] = true;
        } catch (Exception error) {}
    }
    public void mouseReleased(MouseEvent e) {
        try {
            this.pressed[e.getButton()] = false;
        } catch (Exception error) {}
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    /* Inherited from MouseMotionListener */
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }
    public void mouseDragged(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }

}
