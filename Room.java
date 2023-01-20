import java.util.*;
import java.awt.*;

public class Room {
    protected Color color;
    protected Color obstacleColor;
    protected Color penetrableObstacleColor;
    protected int width;
    protected int height;
    protected Obstacle[] obstacles;
    protected Door[] doors;

    public void setColor(Color color) {
        this.color = color;
    }
    public void setObstacleColor(Color obstacleColor) {
        this.obstacleColor = obstacleColor;
    }
    public void setPenetrableObstacleColor(Color penetrableObstacleColor) {
        this.penetrableObstacleColor = penetrableObstacleColor;
    }
    public int getWidth() {
        return this.width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return this.height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setObstacleCount(int obstacleCount) {
        this.obstacles = new Obstacle[obstacleCount];
    }
    public void setDoorCount(int doorCount) {
        this.doors = new Door[doorCount];
    }
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
    }
    public void addObstacle(Obstacle obstacle) {
        for (int i = 0; i < this.obstacles.length; i++) {
            if (this.obstacles[i] == null) {
                this.obstacles[i] = obstacle;
                break;
            }
        }
    }
    public void addDoor(Door door) {
        for (int i = 0; i < this.doors.length; i++) {
            if (this.doors[i] == null) {
                this.doors[i] = door;
                break;
            }
        }
    }
}
