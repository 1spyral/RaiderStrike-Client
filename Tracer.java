import java.awt.*;

/**
 * Represents the path of a bullet
 */
public class Tracer {
    Room room;
    int x1;
    int y1;
    int x2;
    int y2;

    long startTime;
    
    Tracer(Room room, int x1, int y1, int x2, int y2) {
        this.room = room;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        this.startTime = System.currentTimeMillis();
    }

    public Room getRoom() {
        return this.room;
    }
    // Whether the tracer has expired or not
    public boolean isActive() {
        return System.currentTimeMillis() - this.startTime <= 500;
    }
    public void draw(Graphics g, int xCorner, int yCorner) {
        if (this.isActive()) {
            g.setColor(Color.YELLOW);
            g.drawLine(this.x1 + xCorner, this.y1 + yCorner, this.x2 + xCorner, this.y2 + yCorner);
        }
    }
}
