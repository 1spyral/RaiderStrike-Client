import java.awt.*;

public class Obstacle extends Rectangle {
    private boolean penetrable;
    Obstacle(int x, int y, int width, int height, boolean penetrable) {
        super(x, y, width, height);
        this.penetrable = penetrable;
    }
    public void draw(Graphics g) {
        // TODO: draw obstacle
    }
}
