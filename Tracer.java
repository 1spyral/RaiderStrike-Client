import java.awt.*;

public class Tracer {
    Room room;
    int x;
    int y;
    int angle;

    long startTime;
    
    Tracer(Room room, Player player, int angle) {
        this.room = room;
        this.x = player.getX();
        this.y = player.getY();
        this.angle = angle;

        this.startTime = System.currentTimeMillis();

    }

    public Room getRoom() {
        return this.room;
    }
    public boolean isActive() {
        return System.currentTimeMillis() - this.startTime <= 500;
    }
    public void draw(Graphics g, int xCorner, int yCorner) {
        if (this.isActive()) {
            g.setColor(Color.YELLOW);
            g.drawLine(this.x + xCorner, this.y + yCorner, 1, 1);
        }
    }
}
