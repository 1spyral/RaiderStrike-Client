import java.awt.*;
public class Room {
    private Color color;
    private Color obstacleColor;
    private Color penetrableObstacleColor;
    private int width;
    private int height;
    private Obstacle[] obstacles;
    private Door[] doors;

    Room(Color color, Color obstacleColor, Color penetrableObstacleColor, int width, int height, int obstacleCount, int doorCount) {
        this.color = color;
        this.obstacleColor = obstacleColor;
        this.penetrableObstacleColor = penetrableObstacleColor;
        this.width = width;
        this.height = height;
        this.obstacles = new Obstacle[obstacleCount];
        this.doors = new Door[doorCount];
    }
    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }
    public void draw(Graphics g) {
        
    }
}
