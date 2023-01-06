public class Mouse {
    private int x;
    private int y;
    private boolean rightMouseClicked;
    private boolean leftMouseClicked;
    private boolean rightMouseDown;
    private boolean leftMouseDown;
    
    Mouse() {

    }
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
    public void rightMouseClick() {
        this.rightMouseClicked = true;
    }
    public void rightMouseUnclick() {
        this.rightMouseClicked = false;
    }
    public boolean leftClicked() {
        return this.leftMouseClicked;
    }
    public void leftMouseClick() {
        this.leftMouseClicked = true;
    }
    public void leftMouseUnclick() {
        this.leftMouseClicked = false;
    }
    public boolean rightDown() {
        return this.rightMouseDown;
    }
    public void rightMousePressed() {
        this.rightMouseDown = true;
    }
    public void rightMouseReleased() {
        this.rightMouseDown = false;
    }
    public boolean leftDown() {
        return this.leftMouseDown;
    }
    public void leftMousePressed() {
        this.leftMouseDown = true;
    }
    public void leftMouseReleased() {
        this.leftMouseDown = false;
    }
}
