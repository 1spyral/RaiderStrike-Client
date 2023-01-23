import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;

public class Player {
    protected String name;
    protected int team;
    protected Agent agent;
    protected boolean ready;
    
    protected boolean alive;
    protected Room room;
    protected GunModel gun;
    protected int x;
    protected int y;
    protected int angle;
    protected int health;

    protected int damageFrames;
    
    Player() {
        this.team = -1;
        this.ready = false;

        this.alive = true;
        this.gun = GunModel.Robin;
        this.x = -1;
        this.y = -1;
        this.health = 100;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getTeam() {
        return team;
    }
    public void setTeam(int team) {
        this.team = team;
    }
    public Agent getAgent() {
        return this.agent;
    }
    public void setAgent(Agent agent) {
        this.agent = agent;
    }
    public boolean isReady() {
        return this.ready;
    }
    public void lock() {
        this.ready = true;
    }
    public boolean isAlive() {
        return this.alive;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public Room getRoom() {
        return this.room;
    }
    public void setRoom(Room room) {
        this.room = room;
    }
    public GunModel getGun() {
        return this.gun;
    }
    public void setGun(GunModel gun) {
        this.gun = gun;
    }
    public int getX() {
        return this.x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return this.y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setAngle(int angle) {
        this.angle = angle;
    }
    public int getHealth() {
        return this.health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public boolean isDamaged() {
        return this.damageFrames > 0;
    }
    public void damaged() {
        this.damageFrames = 30;
    }
    public void update() {
        this.damageFrames--;
    }
    public void draw(Graphics g, int xCorner, int yCorner) {
        if (this.isDamaged()) {
            g.setColor(Color.RED);
        } else if (this.isAlive()) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(new Color(0, 0, 0, 100));
        }
        g.fillOval(xCorner + this.getX() - Const.PLAYER_RADIUS, yCorner + this.getY() - Const.PLAYER_RADIUS, Const.PLAYER_RADIUS * 2, Const.PLAYER_RADIUS * 2);
        if (this.isAlive() && this.getGun() != null) {
            BufferedImage image = this.getGun().getTopImage();
            AffineTransform transform = new AffineTransform();
            transform.translate(xCorner + this.getX() - Const.PLAYER_RADIUS, yCorner + this.getY() - Const.PLAYER_RADIUS);
            transform.rotate(-Math.toRadians(this.angle - 90), image.getWidth() / 2, image.getHeight() / 2);
            ((Graphics2D)g).drawImage(image, transform, null);
        }
    }
}
