import java.util.*;
import java.awt.event.*;

public class Mouse implements MouseListener, MouseMotionListener {
    private int x;
    private int y;
    private Queue<Click> clicks;
    private boolean[] pressed;
    
    Mouse() {
        this.x = -1;
        this.y = -1;
        this.clicks = new LinkedList<Click>();
        this.pressed = new boolean[17];
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
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

    public class Click {
        private int button;
        private int x;
        private int y;

        Click(int button, int x, int y) {
            this.button = button;
            this.x = x;
            this.y = y;
        }
        
        public int getButton() {
            return this.button;
        }
        public int getX() {
            return this.x;
        }
        public int getY() {
            return this.y;
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
