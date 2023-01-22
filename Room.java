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
    public void draw(Graphics g, int xCorner, int yCorner) {
        g.setColor(this.color);
        g.fillRect(xCorner, yCorner, this.width, this.height);
        g.setColor(Color.WHITE);
        g.drawRect(xCorner, yCorner, this.width, this.height);
        for (Door door: this.doors) {
            door.draw(g, xCorner, yCorner);
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