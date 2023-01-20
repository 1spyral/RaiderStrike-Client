import java.awt.*;

public class Door {
    protected int width;
    protected int x;
    protected int y;
    protected int roomId;
    protected int doorId;
    protected int direction;
    
    Door(int width, int x, int y, int roomId, int doorId, int direction) {
        this.width = width;
        this.x = x;
        this.y = y;
        this.roomId = roomId;
        this.doorId = doorId;
        this.direction = direction;
    }

    public void draw(Graphics g) {
        // TODO: draw door
    }
}
