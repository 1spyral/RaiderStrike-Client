import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GameState extends State {
    int id;
    Player[] players;
    Map map;

    int creds;
    Sidearm sidearm;
    Primary primary;
    
    boolean playing;
    boolean shooting;
    int direction;
    int angle;
    int heldGun; // 1 - primary, 2 - sidearm
    
    LinkedList<GameObject> objects;
    LinkedList<Tracer> tracers;

    MinimapPanel minimapPanel;
    HealthAmmoPanel healthAmmoPanel;
    PlayersPanel playerPanel;
    TimePanel timePanel;
    WeaponsPanel weaponsPanel;


    GameState(Keyboard keyboard, Mouse mouse, Messenger messenger) {
        super(keyboard, mouse, messenger);
    }
    public void setup(Object[] args) {
        super.setup();
        this.id = (int)args[0];
        this.players = (Player[])args[1];
        this.map = (Map)args[2];

        this.sidearm = new Sidearm("Robin", 12);

        this.heldGun = 2;

        this.objects = new LinkedList<GameObject>();
        this.tracers = new LinkedList<Tracer>();

        this.minimapPanel = new MinimapPanel();
        this.healthAmmoPanel = new HealthAmmoPanel();
        this.playerPanel = new PlayersPanel(this.players);
        this.timePanel = new TimePanel();
        this.weaponsPanel = new WeaponsPanel(this.players[this.id]);
    }
    public void update() {
        super.update();
        for (Player player: this.players) {
            if (player != null) {
                player.update();
            }
        }
        if (this.playing) {
            // Determine if the player is shooting
            this.setShooting(this.mouse.isPressed(MouseEvent.BUTTON1));
            // Calculate what direction the player is moving
            if (this.keyboard.isHeld(KeyEvent.VK_W) && this.keyboard.isHeld(KeyEvent.VK_D)) {
                this.setDirection(5);
            } else if (this.keyboard.isHeld(KeyEvent.VK_W) && this.keyboard.isHeld(KeyEvent.VK_A)) {
                this.setDirection(6);
            } else if (this.keyboard.isHeld(KeyEvent.VK_S) && this.keyboard.isHeld(KeyEvent.VK_A)) {
                this.setDirection(7);
            } else if (this.keyboard.isHeld(KeyEvent.VK_S) && this.keyboard.isHeld(KeyEvent.VK_D)) {
                this.setDirection(8);
            } else if (this.keyboard.isHeld(KeyEvent.VK_W)) {
                this.setDirection(1);
            } else if (this.keyboard.isHeld(KeyEvent.VK_S)) {
                this.setDirection(2);
            } else if (this.keyboard.isHeld(KeyEvent.VK_A)) {
                this.setDirection(3);
            } else if (this.keyboard.isHeld(KeyEvent.VK_D)) {
                this.setDirection(4);
            } else {
                this.setDirection(0);
            }
            // Calculate what angle the player is aiming
            this.setAngle(this.calculateAngle());
            // Remove expired bullet tracers
            while (!this.tracers.isEmpty() && !this.tracers.peek().isActive()) {
                this.tracers.pop();
            }
        }
    }
    public void message(String messageText) {
        String[] message = messageText.split(" ");
        String command = message[0];
        String[] args = Arrays.copyOfRange(message, 1, message.length);
        switch (command) {
            case "ROUND_START":
                this.round_start(args);
                break;
            case "CREDS":
                this.creds(args);
                break;
            case "BUY_END":
                this.buy_end(args);
                break;
            case "KILL":
                this.kill(args);
                break;
            case "DEATH":
                this.death(args);
                break;
            case "PLAYER_ROOM":
                this.player_room(args);
                break;
            case "PLAYER_LOCATION":
                this.player_location(args);
                break;
            case "PLAYER_GUN":
                this.player_gun(args);
                break;
            case "PLAYER_TURN":
                this.player_turn(args);
                break;
            case "OBJECT":
                this.object(args);
                break;
            case "OBJECT_ROOM":
                this.object_room(args);
                break;
            case "OBJECT_LOCATION":
                this.object_location(args);
                break;
            case "OBJECT_REMOVE":
                this.object_remove(args);
                break;
            case "BULLET":
                this.bullet(args);
                break;
            case "GUN":
                this.gun(args);
                break;
            case "PICKUP":
                this.pickup(args);
                break;
            case "AMMO":
                this.ammo(args);
                break;
            case "HEALTH":
                this.health(args);
                break;
            case "DAMAGE":
                this.damage(args);
                break;
            case "AUDIO":
                this.audio(args);
                break;
            case "PLANT":
                this.plant(args);
                break;
            case "WIN":
                this.win(args);
                break;
        }
    }
    public void type(char key) {
        if (this.playing) {
            if (key == '1') {
                if (this.primary != null) {
                    this.heldGun = 1;
                }
            } else if (key == '2') {
                this.heldGun = 2;
            }
            if (key == 'r' || key == 'R') {
                this.messenger.print("RELOAD");
            }
        }
    }
    public void click(Mouse.Click click) {
        for (Button button: this.buttons.values()) {
            if (button.click(click)) {
                break;
            }
        }
        if (this.playing) {
            if (click.getButton() == MouseEvent.BUTTON1) {
                
            }
        }
    }
    public void draw(Graphics g) {
        this.healthAmmoPanel.draw(g);
        this.playerPanel.draw(g);
        this.weaponsPanel.draw(g);
        if (!this.playing) {

        } else {
            Room room = this.players[this.id].getRoom();
            int xCorner = (Const.WIDTH - room.getWidth()) / 2;
            int yCorner = (Const.HEIGHT - room.getHeight()) / 2;
            room.draw(g, xCorner, yCorner);
            for (Player player: this.players) {
                if (player != null && player.getRoom().equals(room)) {
                    player.draw(g, xCorner, yCorner);
                }
            }
            for (GameObject object: this.objects) {
                if (object.getRoom() != null && object.getRoom().equals(room)) {
                    object.draw(g, xCorner, yCorner);
                }
            }
            for (Tracer tracer: this.tracers) {
                if (tracer.getRoom().equals(room)) {
                    tracer.draw(g, xCorner, yCorner);
                }
            }
            
        }
    }
    public void close() {
        super.close();
    }
    private Gun getGun(int slot) {
        if (slot == 1) {
            return this.primary;
        } else {
            return this.sidearm;
        }
    }
    private void setShooting(boolean shooting) {
        if (this.shooting ^ shooting) {
            this.shooting = shooting;
            this.messenger.print("FIRE " + (this.shooting ? 1 : 0));
        }
    }
    private void setDirection(int direction) {
        if (this.direction != direction) {
            this.direction = direction;
            this.messenger.print("MOVE " + this.direction);
        }
    }
    private void setAngle(int angle) {
        if (this.angle != angle) {
            this.angle = angle;
            this.messenger.print("AIM " + this.angle);
        }
    }
    private int calculateAngle() {
        Player player = this.players[this.id];
        Room room = player.getRoom();
        int xCorner = (Const.WIDTH - room.getWidth()) / 2;
        int yCorner = (Const.HEIGHT - room.getHeight()) / 2;
        return (int)(Math.toDegrees(Math.atan2(yCorner + player.getY() - mouse.getY(), mouse.getX() - xCorner - player.getX())) + 360) % 360;
    }
    private void nextRound() {
        this.playing = false;
        this.objects = new LinkedList<GameObject>();
    }
    /* Server-Client commands */
    public void round_start(String[] args) {
        this.playing = true;
    }
    public void creds(String[] args) {
        this.creds = Integer.valueOf(args[0]);
    }
    public void buy_end(String[] args) {
        
    }
    public void kill(String[] args) {
        // TODO: kill feed
    }
    public void death(String[] args) {
        this.players[Integer.valueOf(args[0])].setAlive(false);
        // TODO: kill feed
    }
    public void player_room(String[] args) {
        this.players[Integer.valueOf(args[0])].setRoom(this.map.getRooms()[Integer.valueOf(args[1])]);
    }
    public void player_location(String[] args) {
        this.players[Integer.valueOf(args[0])].setX(Integer.valueOf(args[1]));
        this.players[Integer.valueOf(args[0])].setY(Integer.valueOf(args[2]));
    }
    public void player_gun(String[] args) {
        this.players[Integer.valueOf(args[0])].setGun(GunModel.valueOf(args[1]));
    }
    public void player_turn(String[] args) {
        this.players[Integer.valueOf(args[0])].setAngle(Integer.valueOf(args[1]));
    }
    public void object(String[] args) {
        // TODO
    }
    public void object_room(String[] args) {
        this.objects.get(Integer.valueOf(args[0])).setRoom(this.map.getRooms()[Integer.valueOf(args[1])]);
    }
    public void object_location(String[] args) {
        GameObject object = this.objects.get(Integer.valueOf(args[0]));
        object.setX(Integer.valueOf(args[1]));
        object.setY(Integer.valueOf(args[2]));
    }
    public void object_remove(String[] args) {
        this.objects.remove(Integer.parseInt(args[0]));
    }
    public void bullet(String[] args) {
        this.tracers.add(new Tracer(this.map.getRooms()[Integer.valueOf(args[0])], Integer.valueOf(args[1]), Integer.valueOf(args[2]), Integer.valueOf(args[3]), Integer.valueOf(args[4])));
    }
    public void gun(String[] args) {
        // TODO
    }
    public void pickup(String[] args) {
        GunModel gunModel = GunModel.valueOf(args[0]);
        if (Sidearm.isSidearm(gunModel)) {
            this.sidearm = new Sidearm(args[0], gunModel.getMaxAmmo());
        } else {
            this.primary = new Primary(args[0], gunModel.getMaxAmmo());
        }
    }
    public void ammo(String[] args) {
        this.getGun(Integer.valueOf(args[0])).setAmmo(Integer.valueOf(args[1]));
    }
    public void health(String[] args) {
        this.players[Integer.valueOf(args[0])].setHealth(Integer.valueOf(args[1]));
    }
    public void damage(String[] args) {
        // TODO
    }
    public void audio(String[] args) {
        // TODO
    }
    public void plant(String[] args) {
        // TODO
    }
    public void win(String[] args) {
        this.nextRound();
    }

    private class MinimapPanel {

    }
    private class HealthAmmoPanel {
        public void draw(Graphics g) {
            g.setColor(Color.RED);
            Text.drawCentered(g, 20, String.valueOf(Math.min(100, players[id].getHealth())), (int)(Const.HEIGHT * 0.05), (int)(Const.HEIGHT * 0.9));
            g.setColor(Color.CYAN);
            Text.drawCentered(g, 20, String.valueOf(Math.max(0, players[id].getHealth() - 100)), (int)(Const.HEIGHT * 0.05), (int)(Const.HEIGHT * 0.85));
            g.setColor(Color.YELLOW);
            Text.drawCentered(g, 20, String.valueOf(heldGun == 1 ? primary.getAmmo() : sidearm.getAmmo()), (int)(Const.HEIGHT * 0.1), (int)(Const.HEIGHT * 0.875));
        }
    }
    private class PlayersPanel {
        Player[] players;

        PlayersPanel(Player[] players) {
            this.players = players;
        }
        public void draw(Graphics g) {
            g.setColor(Color.RED);
            g.fillRect((int)(Const.HEIGHT * 0.05), (int)(Const.HEIGHT * 0.5), (int)(Const.WIDTH * 0.2), (int)(Const.HEIGHT * 0.125));
            g.setColor(Color.CYAN);
            g.fillRect((int)(Const.HEIGHT * 0.05), (int)(Const.HEIGHT * 0.625), (int)(Const.WIDTH * 0.2), (int)(Const.HEIGHT * 0.125));
            g.setColor(Color.BLACK);
            g.drawRect((int)(Const.HEIGHT * 0.05), (int)(Const.HEIGHT * 0.5), (int)(Const.WIDTH * 0.2), (int)(Const.HEIGHT * 0.25));
            int red = 0;
            int blue = 0;
            for (Player player: this.players) {
                if (player == null) {
                    break;
                }
                if (player.isDamaged()) {
                    g.setColor(Color.PINK);
                } else {
                    g.setColor(Color.YELLOW);
                }
                if (player.getTeam() == 0) {
                    g.fillOval((int)(Const.HEIGHT * 0.05 + Const.WIDTH * 0.01 + Const.WIDTH * 0.06 * red), (int)(Const.HEIGHT * 0.525), (int)(Const.WIDTH * 0.05), (int)(Const.WIDTH * 0.05));
                    red++;
                } else {
                    g.fillOval((int)(Const.HEIGHT * 0.05 + Const.WIDTH * 0.01 + Const.WIDTH * 0.06 * blue), (int)(Const.HEIGHT * 0.65), (int)(Const.WIDTH * 0.05), (int)(Const.WIDTH * 0.05));
                    blue++;
                }
            }
        }
    }
    private class TimePanel {
        
    }
    private class WeaponsPanel {
        Player player;

        WeaponsPanel(Player player) {
            this.player = player;
        }
        public void draw(Graphics g) {
            g.setColor(Color.BLACK);
            g.drawRect((int)(Const.WIDTH * 0.75), (int)(Const.HEIGHT * 0.55), (int)(Const.WIDTH * 0.25 - Const.HEIGHT * 0.05), (int)(Const.HEIGHT * 0.4));
            if (primary != null) {
                Text.draw(g, 10, primary.getModel().name(), (int)(Const.WIDTH * 0.8), (int)(Const.HEIGHT * 0.8));
                g.drawImage(primary.getModel().getSideImage(), (int)(Const.WIDTH * 0.75), (int)(Const.HEIGHT * 0.8), null);
            } if (sidearm != null) {
                Text.draw(g, 10, sidearm.getModel().name(), (int)(Const.WIDTH * 0.8), (int)(Const.HEIGHT * 0.7));
                g.drawImage(sidearm.getModel().getSideImage(), (int)(Const.WIDTH * 0.75), (int)(Const.HEIGHT * 0.7), null);
            }
        }
    }
}
