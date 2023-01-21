import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GameState extends State {
    int id;
    Player[] players;
    Map map;

    LinkedList<GameObject> objects;

    int creds;
    Sidearm sidearm;
    Primary primary;

    boolean playing;
    int direction;
    int angle;
    int held; // 1 - primary, 2 - sidearm

    MinimapPanel minimapPanel;
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

        this.objects = new LinkedList<GameObject>();

        this.minimapPanel = new MinimapPanel();
        this.playerPanel = new PlayersPanel(this.players);
        this.timePanel = new TimePanel();
        this.weaponsPanel = new WeaponsPanel(this.players[this.id]);
    }
    public void update() {
        super.update();

        if (this.playing) {
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
                    this.held = 1;
                }
            } else if (key == '2') {
                this.held = 2;
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
        this.weaponsPanel.draw(g);
        if (!this.playing) {

        } else {
            this.players[this.id].getRoom().draw(g, this.players, this.objects);
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
        int principleAngle = (int)(Math.atan( (double)(this.mouse.getY() - (double)Const.HEIGHT/2) / (this.mouse.getX() - (double)Const.WIDTH/2)) * (180 / Math.PI));
        int raa = Math.abs(principleAngle); // related acute angle
        if (this.mouse.getX() >= (double)Const.WIDTH/2 && this.mouse.getY() >= (double)Const.HEIGHT/2) return raa;
        else if (this.mouse.getX() < (double)Const.WIDTH/2 && this.mouse.getY() >= (double)Const.HEIGHT/2) return 180 - raa;
        else if (this.mouse.getX() < (double)(Const.WIDTH)/2 && this.mouse.getY() < (double)Const.HEIGHT/2) return 180 + raa;
        else return 360 - raa;
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
    public void object(String[] args) {
        // TODO
    }
    public void object_room(String[] args) {
        // TODO
    }
    public void object_location(String[] args) {
        // TODO
    }
    public void object_remove(String[] args) {
        // TODO
    }
    public void bullet(String[] args) {
        // TODO
    }
    public void gun(String[] args) {
        // TODO
    }
    public void pickup(String[] args) {
        // TODO
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
    private class PlayersPanel {
        Player[] players;

        PlayersPanel(Player[] players) {
            this.players = players;
        }
        public void draw(Graphics g) {
            
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
            g.drawRect((int)(Const.WIDTH * 0.75), (int)(Const.HEIGHT * 0.6), (int)(Const.WIDTH * 0.25 - Const.HEIGHT * 0.05), (int)(Const.HEIGHT * 0.35));
        }
    }
}
