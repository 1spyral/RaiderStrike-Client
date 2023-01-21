import java.awt.*;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected Room room;

    GameObject() {
        this.x = -1;
        this.y = -1;
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
    public void setRoom(Room room) {
        this.room = room;
    }
    public Room getRoom() {
        return this.room;
    }

    public abstract void draw(Graphics g, int xCorner, int yCorner);
}
