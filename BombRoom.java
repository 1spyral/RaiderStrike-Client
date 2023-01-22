import java.awt.*;

/**
 * A room where the bomb can be planted
 */
public class BombRoom extends Room {
    public void draw(Graphics g, int xCorner, int yCorner) {
        g.setColor(this.color);
        // Fill the dimensions of the room
        g.fillRect(xCorner, yCorner, this.width, this.height);
        g.setColor(Color.YELLOW);
        // Draw the borders of the room
        g.drawRect(xCorner, yCorner, this.width, this.height);
        // Draw doors
        for (Door door: this.doors) {
            door.draw(g, xCorner, yCorner);
        }
    }
}
