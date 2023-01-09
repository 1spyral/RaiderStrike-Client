import java.awt.*;
public class Room {
    Color color;
    Color obstacleColor;
    Color penetrableObstacleColor;
    int width;
    int height;
    Obstacle[] obstacles;
    Door[] doors;

    Room(Color color, Color obstacleColor, Color penetrableColor, int width, int height, int obstacleCount, int doorCount) {
        this.color = color;
        this.obstacleColor = obstacleColor;
        this.penetrableObstacleColor = penetrableObstacleColor;
        this.width = width;
        this.height = height;
        this.obstacles = new Obstacle[obstacleCount];
        this.doors = new Door[doorCount];
    }
}
