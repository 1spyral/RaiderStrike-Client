import java.util.*;
import java.awt.*;

public class BombRoom extends Room {
    public void draw(Graphics g, Player[] players, LinkedList<GameObject> objects) {
        int xCorner = (Const.WIDTH - this.width) / 2;
        int yCorner = (Const.HEIGHT - this.height) / 2;
        g.setColor(this.color);
        g.fillRect(xCorner, yCorner, this.width, this.height);
        g.setColor(Color.YELLOW);
        for (Player player: players) {
            if (player != null && player.getRoom().equals(this)) {
                g.fillOval(xCorner + player.getX() - Const.PLAYER_RADIUS, yCorner + player.getY() - Const.PLAYER_RADIUS, Const.PLAYER_RADIUS, Const.PLAYER_RADIUS);
            }
        }
        g.setColor(Color.YELLOW);
        g.drawRect(xCorner, yCorner, this.width, this.height);
    }
}
