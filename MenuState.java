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

        Button nameButton = new NameButton(this.mouse);
        nameButton.setBounds((int)(Const.WIDTH * 0.3), (int)(Const.HEIGHT * 0.65), (int)(Const.WIDTH * 0.4), (int)(Const.HEIGHT * 0.1));
        nameButton.setText("JOIN");
        nameButton.setColor(Color.BLACK);
        nameButton.setHoverColor(Color.GRAY);
        nameButton.setTextColor(Color.WHITE);
        nameButton.setFontSize(30);
        this.buttons.put("name", nameButton);

        Button redButton = new RedButton(this.mouse);
        redButton.setBounds((int)(Const.WIDTH * 0.15), (int)(Const.HEIGHT * 0.5 - Const.WIDTH * 0.15), (int)(Const.WIDTH * 0.3), (int)(Const.WIDTH * 0.3));
        redButton.setText("TEAM RED");
        redButton.setColor(Color.RED);
        redButton.setHoverColor(Color.PINK);
        redButton.setTextColor(Color.BLACK);
        redButton.setFontSize(20);
        this.buttons.put("red", redButton);

        Button blueButton = new BlueButton(this.mouse);
        blueButton.setBounds((int)(Const.WIDTH * 0.55), (int)(Const.HEIGHT * 0.5 - Const.WIDTH * 0.15), (int)(Const.WIDTH * 0.3), (int)(Const.WIDTH * 0.3));
        blueButton.setText("TEAM BLUE");
        blueButton.setColor(Color.BLUE);
        blueButton.setHoverColor(Color.CYAN);
        blueButton.setTextColor(Color.BLACK);
        blueButton.setFontSize(20);
        this.buttons.put("blue", blueButton);
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
                    if (this.name.equals(" ")) {
                        this.name = "";
                    }
                }
            }
        }
        while (this.mouse.hasNext()) {
            Mouse.Click click = this.mouse.poll();
            for (Button button: this.buttons.values()) {
                if (button.click(click)) {
                    break;
                }
            }
        }
        if (!this.nameSet) {
            this.caret.update();

        }
    }
    public void draw(Graphics g) {
        super.draw(g);
        ((Graphics2D) g).setStroke(new BasicStroke(3));
        g.setColor(Color.BLACK);
        if (this.nameSet) {

        } 
        else {
            g.drawImage(this.title, (Const.WIDTH - this.title.getWidth()) / 2,
                    (Const.HEIGHT - this.title.getHeight()) / 4, null);
            if (this.id == -1) {
                Text.drawCentered(g, 80, "Waiting on server...", Const.WIDTH / 2, Const.HEIGHT * 0.6);
                if (this.caret.isActive()) {
                    g.fillRect((int) (Const.WIDTH * 0.45), (int) (Const.HEIGHT * 0.7), (int) (Const.WIDTH * 0.05), (int) (Const.HEIGHT * 0.01));
                } else {
                    g.fillRect((int) (Const.WIDTH * 0.5), (int) (Const.HEIGHT * 0.7), (int) (Const.WIDTH * 0.05), (int) (Const.HEIGHT * 0.01));
                }
            } else {
                g.drawRect((int) (Const.WIDTH * 0.3), (int) (Const.HEIGHT * 0.5), (int) (Const.WIDTH * 0.4), (int) (Const.HEIGHT * 0.1));
                Text.draw(g, 40, this.name, Const.WIDTH * 0.31, Const.HEIGHT * 0.56);
                if (this.caret.isActive()) {
                    g.drawLine((int) (Const.WIDTH * 0.31 + g.getFontMetrics().stringWidth(this.name)), (int) (Const.HEIGHT * 0.52), (int) (Const.WIDTH * 0.31 + g.getFontMetrics().stringWidth(this.name)), (int) (Const.HEIGHT * 0.58));
                }
            }
        }
    }
    public void close() {
        super.close();
    }
    private void id(String[] args) {
        this.id = Integer.valueOf(args[0]);
        this.buttons.get("name").setActive(true);
    }
    private void player(String[] args) {
        this.players[Integer.valueOf(args[0])] = new Player();
    }
    private void team(String[] args) {
        this.red = Integer.valueOf(args[0]);
        this.blue = Integer.valueOf(args[1]);
        this.buttons.get("red").setText("TEAM RED: " + this.red + "/3");
        this.buttons.get("blue").setText("TEAM BLUE: " + this.blue + "/3");
    }
    private void agent(String[] args) {
        this.players[Integer.valueOf(args[0])].setAgent(Integer.valueOf(args[1]));
    }
    private void name(String[] args) {
        String fullName = "";
        for (String word: Arrays.copyOfRange(args, 1, args.length)) {
            fullName = fullName + " " + word;
        }
        fullName = fullName.trim();
        this.players[Integer.valueOf(args[0])].setName(fullName);
    }
    private void ready(String[] args) {
        this.players[Integer.valueOf(args[0])].lock();
    }
    private void start(String[] args) {
        this.close();
    }

    private class NameButton extends Button {
        NameButton(Mouse mouse) {
            super(mouse);
        }
        public boolean run() {
            if (name.length() == 0) {
                return false;
            }
            messenger.print("NAME " + name);
            nameSet = true;
            this.setActive(false);
            buttons.get("red").setActive(true);
            buttons.get("blue").setActive(true);
            return true;
        }
    }
    private class RedButton extends Button {
        RedButton(Mouse mouse) {
            super(mouse);
        }
        public void draw(Graphics g) {
            super.draw(g);
        }
        public boolean run() {
            if (red >= 3) {
                return false;
            }
            messenger.print("TEAM 0");
            this.setActive(false);
            buttons.get("blue").setActive(false);
            return true;
        }
    }
    private class BlueButton extends Button {
        BlueButton(Mouse mouse) {
            super(mouse);
        }
        public boolean run() {
            if (blue >= 3) {
                return false;
            }
            messenger.print("TEAM 1");
            this.setActive(false);
            buttons.get("red").setActive(false);
            return true;
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
