import java.awt.event.*;

public class Mouse implements MouseListener, MouseMotionListener {
    private int x;
    private int y;
    private boolean rightMouseClicked;
    private boolean leftMouseClicked;
    private boolean rightMouseDown;
    private boolean leftMouseDown;
    
    public int getX() {
        return this.x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return this.y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public boolean rightClicked() {
        return this.rightMouseClicked;
    }
    protected void rightMouseClick() {
        this.rightMouseClicked = true;
    }
    public void rightMouseUnclick() {
        this.rightMouseClicked = false;
    }
    public boolean leftClicked() {
        return this.leftMouseClicked;
    }
    protected void leftMouseClick() {
        this.leftMouseClicked = true;
    }
    public void leftMouseUnclick() {
        this.leftMouseClicked = false;
    }
    public boolean rightDown() {
        return this.rightMouseDown;
    }
    protected void rightMousePressed() {
        this.rightMouseDown = true;
    }
    protected void rightMouseReleased() {
        this.rightMouseDown = false;
    }
    public boolean leftDown() {
        return this.leftMouseDown;
    }
    protected void leftMousePressed() {
        this.leftMouseDown = true;
    }
    protected void leftMouseReleased() {
        this.leftMouseDown = false;
    }
    /* Inherited from MouseListener */
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            this.rightMouseClick();
        } else if (e.getButton() == 2) {
            this.leftMouseClick();
        }
    }
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            this.rightMousePressed();
        } else if (e.getButton() == 2) {
            this.leftMousePressed();
        }
    }
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == 1) {
            this.rightMouseReleased();
        } else if (e.getButton() == 2) {
            this.leftMouseReleased();
        }
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    /* Inherited from MouseMotionListener */
    public void mouseMoved(MouseEvent e) {
        this.setX(e.getX());
        this.setY(e.getY());
    }
    public void mouseDragged(MouseEvent e) {
        this.setX(e.getX());
        this.setY(e.getY());
    }         
}
