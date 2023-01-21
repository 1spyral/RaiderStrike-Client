import java.util.*;
import java.awt.*;

public class BombRoom extends Room {
    public void draw(Graphics g, Player[] players, LinkedList<GameObject> objects) {
        int xCorner = (Const.WIDTH - this.width) / 2;
        int yCorner = (Const.HEIGHT - this.height) / 2;
        g.setColor(this.color);
        g.fillRect(xCorner, yCorner, this.width, this.height);
        for (Player player: players) {
            if (player != null && player.getRoom().equals(this)) {
                player.draw(g, xCorner, yCorner);
            }
        }
        g.setColor(Color.YELLOW);
        g.drawRect(xCorner, yCorner, this.width, this.height);
    }
}
