import java.awt.*;
public class Room {
    private Color color;
    private Color obstacleColor;
    private Color penetrableObstacleColor;
    private int width;
    private int height;
    private Obstacle[] obstacles;
    private Door[] doors;

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
    public void draw(Graphics g, Player[] players, GameObject[] gameObjects) {
        
    }
    public void addObstacle(Obstacle obstacle) {
        for (int i = 0; i < this.obstacles.length; i++) {
            if (this.obstacles[i] != null) {
                this.obstacles[i] = obstacle;
                break;
            }
        }
    }
    public void addDoor(Door door) {
        for (int i = 0; i < this.doors.length; i++) {
            if (this.doors[i] != null) {
                this.doors[i] = door;
                break;
            }
        }
    }
}
