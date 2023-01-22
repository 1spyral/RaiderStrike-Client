import java.awt.*;

/**
 * A room where players spawn
 */
public class SpawnRoom extends Room {
    protected int[] spawnX;
    protected int[] spawnY;

    public void setSpawnX(int[] spawnX) {
        this.spawnX = spawnX;
    }
    public void setSpawnY(int[] spawnY) {
        this.spawnY = spawnY;
    }
    public void draw(Graphics g, int xCorner, int yCorner) {
        g.setColor(this.color);
        // Fill the dimensions of the room
        g.fillRect(xCorner, yCorner, this.width, this.height);
        g.setColor(Color.RED);
        // Draw the borders of the room
        g.drawRect(xCorner, yCorner, this.width, this.height);
        // Draw doors
        for (Door door: this.doors) {
            door.draw(g, xCorner, yCorner);
        }
        // Draw spawn locations
        for (int i = 0; i < 3; i++) {
            g.setColor(Color.YELLOW);
            g.drawOval(xCorner + this.spawnX[i] - Const.PLAYER_RADIUS, yCorner + this.spawnY[i] - Const.PLAYER_RADIUS, Const.PLAYER_RADIUS * 2, Const.PLAYER_RADIUS * 2);
        }
    }
}
