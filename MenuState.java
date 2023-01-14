import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

public class MenuState extends State {
    BufferedImage title;

    int id;
    Player[] players;
    int red;
    int blue;
    Caret caret;
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
        this.caret = new Caret();
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
                    break;
                case "PLAYER":
                    this.player(args);
                    break;
                case "TEAM":
                    this.team(args);
                    break;
                case "AGENT":
                    this.agent(args);
                    break;
                case "NAME":
                    this.name(args);
                    break;
                case "READY":
                    this.ready(args);
                    break;
                case "START":
                    this.start(args);
                    break;
            }
        }
        while (this.keyboard.hasNext()) {
            if (!this.nameSet) {
                char key = this.keyboard.next();
                if (key == '\b') {
                    if (this.name.length() > 0) {
                        this.name = this.name.substring(0, this.name.length() - 1);
                    }
                } else if (this.name.length() < Const.MAX_NAME_LENGTH && key != '\n' && key != '\r') {
                    this.name = this.name + key;
                }
            }
        }
        while (this.mouse.hasNext()) {
            Mouse.Click click = this.mouse.poll();
            for (Button button: this.buttons) {
                button.click(click);
            }
        }
        if (!this.nameSet) {
            this.caret.update();

        }
    }

    public void draw(Graphics g) {
        super.draw(g);
        ((Graphics2D) g).setStroke(new BasicStroke(3));
        if (this.nameSet) {

        } 
        else {
            g.drawImage(this.title, (Const.WIDTH - this.title.getWidth()) / 2,
                    (Const.HEIGHT - this.title.getHeight()) / 4, null);
            if (this.id == -1) {
                Text.drawCentered(g, 80, "Waiting on server...", Const.WIDTH / 2, Const.HEIGHT * 0.6);
                if (this.caret.isActive()) {
                    g.fillRect((int) (Const.WIDTH * 0.45), (int) (Const.HEIGHT * 0.7), (int) (Const.WIDTH * 0.05),
                            (int) (Const.HEIGHT * 0.01));
                } else {
                    g.fillRect((int) (Const.WIDTH * 0.5), (int) (Const.HEIGHT * 0.7), (int) (Const.WIDTH * 0.05),
                            (int) (Const.HEIGHT * 0.01));
                }
            } else {
                g.drawRect((int) (Const.WIDTH * 0.3), (int) (Const.HEIGHT * 0.6), (int) (Const.WIDTH * 0.4),
                        (int) (Const.HEIGHT * 0.1));
                Text.draw(g, 40, this.name, Const.WIDTH * 0.31, Const.HEIGHT * 0.66);
                if (this.caret.isActive()) {
                    g.drawLine((int) (Const.WIDTH * 0.31 + g.getFontMetrics().stringWidth(this.name)),
                            (int) (Const.HEIGHT * 0.62),
                            (int) (Const.WIDTH * 0.31 + g.getFontMetrics().stringWidth(this.name)),
                            (int) (Const.HEIGHT * 0.68));
                }
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

    private class NameButton extends Button {
        NameButton(Mouse mouse, int x, int y, int width, int height, String text, Color color, Color hoverColor, int fontSize) {
            super(mouse, x, y, width, height, text, color, hoverColor, fontSize);
        }
        
        public void run() {
            
        }
    }
    private class Caret {
        private int ticks;

        public void update() {
            ticks++;
            ticks %= (int) (1000 / Const.FRAME_PERIOD);
        }

        public boolean isActive() {
            return this.ticks < (int) (500 / Const.FRAME_PERIOD);
        }
    }
}
