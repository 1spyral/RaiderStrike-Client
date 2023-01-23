import java.awt.*;

/**
 * Class that describes a door in a room
 */
public class Door {
    protected int width;
    protected int x;
    protected int y;
    protected int roomId;
    protected int doorId;
    // The direction that the player will exit from: 0 - up, 1 - right, 2 - down, 3 - left
    protected int direction;
    
    Door(int width, int x, int y, int roomId, int doorId, int direction) {
        this.width = width;
        this.x = x;
        this.y = y;
        this.roomId = roomId;
        this.doorId = doorId;
        this.direction = direction;
    }

    public void draw(Graphics g, int xCorner, int yCorner) {
        ((Graphics2D)g).setStroke(new BasicStroke(6));
        g.setColor(Color.GREEN);
        // Up/down
        if (this.direction % 2 != 0) {
            g.drawLine(xCorner + this.x, yCorner + this.y, xCorner + this.x, yCorner + this.y + this.width);
        }
        // Left/right
        else {
            g.drawLine(xCorner + this.x, yCorner + this.y, xCorner + this.x + this.width, yCorner + this.y);
        }
        ((Graphics2D)g).setStroke(new BasicStroke(3));
    }
}
