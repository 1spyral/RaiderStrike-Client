import java.io.*;
import java.util.Arrays;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

public class MenuState extends State {
    BufferedImage title;

    int id;
    Player[] players;
    int red;
    int blue;
    boolean caret;
    String name;
    boolean nameSet;

    MenuState(Keyboard keyboard, Mouse mouse, Messenger messenger) {
        super(keyboard, mouse, messenger);
        try {
            this.title = ImageIO.read(new File("assets/Title.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setup(Object[] args) {
        super.setup();

        this.id = -1;
        this.players = new Player[6];
        this.red = 0;
        this.blue = 0;
        this.caret = false;
        this.name = "";
        this.nameSet = false;
    }
    public void update() {
        while (!this.messenger.isEmpty()) {
            String[] message = this.messenger.poll().split(" ");
            String command = message[0];
            String[] args = Arrays.copyOfRange(message, 1, message.length);
            switch (command) {
                case "ID":
                this.id(args);
                case "PLAYER":
                this.player(args);
                case "TEAM":
                this.team(args);
                case "AGENT":
                this.agent(args);
                case "NAME":
                this.name(args);
                case "READY":
                this.ready(args);
                case "START":
                this.start(args);
            }
        }
        if (!this.nameSet) {
            this.caret = !this.caret;

        }
    }
    public void draw(Graphics g) {
        if (this.nameSet) {
        }
        else {
            g.drawImage(this.title, (Const.WIDTH - this.title.getWidth()) / 2, (Const.HEIGHT - this.title.getHeight()) / 4, null);
            g.setFont(FontLoader.getFont(80));
            if (this.id == -1) {
                g.drawString("Waiting on server...", (Const.WIDTH - g.getFontMetrics().stringWidth("Waiting on server...")) / 2, (int)(Const.HEIGHT * 0.6));
            }
            else {
                g.drawRect((int)(Const.WIDTH * 0.3), (int)(Const.HEIGHT * 0.8), (int)(Const.WIDTH * 0.4), (int)(Const.HEIGHT * 0.1));
            }      
        }
    }
    public void close() {
        super.close();
    }

    private void id(String[] args) {
        this.id = Integer.valueOf(args[0]);
    }
    private void player(String[] args) {
        this.players[Integer.valueOf(args[0])] = new Player();
    }
    private void team(String[] args) {
        this.red = Integer.valueOf(args[0]);
        this.blue = Integer.valueOf(args[1]);
    }
    private void agent(String[] args) {

    }
    private void name(String[] args) {

    }
    private void ready(String[] args) {
        
    }
    private void start(String[] args) {
        this.close();
    }
}
