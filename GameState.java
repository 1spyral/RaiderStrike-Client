import java.util.*;
import java.awt.*;

public class GameState extends State {
    int id;
    Player[] players;
    Map map;

    GameObject[] objects;

    int creds;
    Sidearm sidearm;
    Primary primary;
    Gun currentGun;

    boolean playing;

    GameState(Keyboard keyboard, Mouse mouse, Messenger messenger) {
        super(keyboard, mouse, messenger);
    }
    public void setup(Object[] args) {
        super.setup();
        this.id = (int)args[0];
        this.players = (Player[])args[1];
        this.map = (Map)args[2];
    }
    public void update() {
        super.update();
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
        }
    }
    public void type(char key) {

    }
    public void click(Mouse.Click click) {

    }
    public void draw(Graphics g) {
        
        if (!this.playing) {

        } else {
            this.map.getRooms()[this.players[this.id].getRoom()].draw(g, this.players, this.objects);
        }
    }
    public void close() {
        super.close();
    }
    /* Server-Client commands */
    public void round_start(String[] args) {
        
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
        this.players[Integer.valueOf(args[0])].setRoom(Integer.valueOf(args[1]));
    }
    public void player_location(String[] args) {
        this.players[Integer.valueOf(args[0])].setX(Integer.valueOf(args[1]));
        this.players[Integer.valueOf(args[0])].setY(Integer.valueOf(args[2]));
    }
    public void player_gun(String[] args) {
        this.players[Integer.valueOf(args[0])].setGun(GunModel.valueOf(args[1]));
    }
    public void object(String[] args) {
        
    }
    public void object_room(String[] args) {
        
    }
    public void object_location(String[] args) {
        
    }
    public void object_remove(String[] args) {
        
    }
    public void bullet(String[] args) {
        
    }
    public void gun(String[] args) {
        
    }
    public void pickup(String[] args) {
        
    }
    public void ammo(String[] args) {
        currentGun.setAmmo(Integer.valueOf(args[0]));
    }
    public void health(String[] args) {
        this.players[Integer.valueOf(args[0])].setHealth(Integer.valueOf(args[1]));
    }
    public void damage(String[] args) {
        
    }
    public void audio(String[] args) {
        
    }
    public void plant(String[] args) {
        
    }
}
