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

        for (int agentId = 0; agentId < Agent.values().length; agentId++) {
            Button agentButton = new AgentButton(this.mouse, Agent.values()[agentId]);
            agentButton.setBounds((int)(Const.WIDTH * (0.5 - Agent.values().length * 0.05 + agentId * 0.1)), (int)(Const.HEIGHT * 0.5), (int)(Const.WIDTH * 0.1), (int)(Const.WIDTH * 0.1));
            agentButton.setColor(Color.BLACK);
            agentButton.setHoverColor(Color.BLACK);
            agentButton.setTextColor(Color.BLACK);
            this.buttons.put("agent" + agentId, agentButton);
        }

        Button readyButton = new ReadyButton(this.mouse);
        readyButton.setBounds((int)(Const.WIDTH * 0.3), (int)(Const.HEIGHT * 0.7), (int)(Const.WIDTH * 0.4), (int)(Const.HEIGHT * 0.1));
        readyButton.setText("READY");
        readyButton.setColor(Color.GREEN);
        readyButton.setHoverColor(Color.CYAN);
        readyButton.setTextColor(Color.WHITE);
        this.buttons.put("ready", readyButton);
    }
    public void update() {
        super.update();
        this.caret.update();
    }
    public void message(String messageText) {
        String[] message = messageText.split(" ");
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
            case "PLAYER_TEAM":
                this.player_team(args);
                break;
            case "JOINED":
                this.joined(args);
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
    public void type(char key) {
        if (this.buttons.get("name").isActive()) {
            ((NameButton)this.buttons.get("name")).type(key);
        }
    }
    public void click(Mouse.Click click) {
        for (Button button: this.buttons.values()) {
            if (button.click(click)) {
                break;
            }
        }
    }
    public void draw(Graphics g) {
        super.draw(g);
        ((Graphics2D) g).setStroke(new BasicStroke(3));
        g.setColor(Color.BLACK);
        if (this.id != -1 && this.players[this.id] != null && this.players[this.id].getName() != null) {
            if (this.players[this.id].getAgent() != null) {
                Text.drawCentered(g, 30, "Selected agent: " + this.players[this.id].getAgent().name(), Const.WIDTH / 2, Const.HEIGHT * 0.3);
            }
        } 
        else {
            g.drawImage(this.title, (Const.WIDTH - this.title.getWidth()) / 2, (Const.HEIGHT - this.title.getHeight()) / 4, null);
            if (!this.buttons.get("name").isActive()) {
                Text.drawCentered(g, 80, "Waiting on server...", Const.WIDTH / 2, Const.HEIGHT * 0.6);
                if (this.caret.isActive()) {
                    g.fillRect((int) (Const.WIDTH * 0.45), (int) (Const.HEIGHT * 0.7), (int) (Const.WIDTH * 0.05), (int) (Const.HEIGHT * 0.01));
                } else {
                    g.fillRect((int) (Const.WIDTH * 0.5), (int) (Const.HEIGHT * 0.7), (int) (Const.WIDTH * 0.05), (int) (Const.HEIGHT * 0.01));
                }
            } 
        }
    }
    public void close() {
        super.close();
        this.setNextArgs(new Object[] {
            this.id,
            this.players,
        });
    }
    /* Server-Client commands */
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
        if (this.red >= 3) {
            this.buttons.get("red").setColor(Color.LIGHT_GRAY);
            this.buttons.get("red").setHoverColor(Color.LIGHT_GRAY);
        }
        if (this.blue >= 3) {
            this.buttons.get("blue").setColor(Color.LIGHT_GRAY);
            this.buttons.get("blue").setHoverColor(Color.LIGHT_GRAY);
        }
    }
    private void player_team(String[] args) {
        this.players[Integer.valueOf(args[0])].setTeam(Integer.valueOf(args[1]));
    }
    private void joined(String[] args) {
        this.buttons.get("red").setActive(false);
        this.buttons.get("blue").setActive(false);
        for (int agentId = 0; agentId < Agent.values().length; agentId++) {
            this.buttons.get("agent" + agentId).setActive(true);
        }
    }
    private void agent(String[] args) {
        this.players[Integer.valueOf(args[0])].setAgent(Agent.valueOf(args[1]));
    }
    private void name(String[] args) {
        String fullName = "";
        for (String word: Arrays.copyOfRange(args, 1, args.length)) {
            fullName = fullName + " " + word;
        }
        fullName = fullName.trim();
        this.players[Integer.valueOf(args[0])].setName(fullName);
        if (Integer.valueOf(args[0]) == this.id) {
            this.buttons.get("red").setActive(true);
            this.buttons.get("blue").setActive(true);
        }
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
        public void draw(Graphics g) {
            super.draw(g);
            if (this.active) {
                g.setColor(Color.BLACK);
                g.drawRect((int)(Const.WIDTH * 0.3), (int)(Const.HEIGHT * 0.5), (int)(Const.WIDTH * 0.4), (int)(Const.HEIGHT * 0.1));
                Text.draw(g, 40, name, Const.WIDTH * 0.31, Const.HEIGHT * 0.56);
                if (caret.isActive()) {
                    g.drawLine((int) (Const.WIDTH * 0.31 + g.getFontMetrics().stringWidth(name)), (int) (Const.HEIGHT * 0.52), (int) (Const.WIDTH * 0.31 + g.getFontMetrics().stringWidth(name)), (int) (Const.HEIGHT * 0.58));
                }    
            }
        }
        public boolean run() {
            if (name.length() == 0) {
                return false;
            }
            messenger.print("NAME " + name);
            this.setActive(false);
            return true;
        }
        public void type(char key) {
            if (key == '\b') {
                if (name.length() > 0) {
                    name = name.substring(0, name.length() - 1);
                }
            } else if (key == '\n' || key == '\r') {
                this.run();
            } else if (name.length() < Const.MAX_NAME_LENGTH) {
                name = name + key;
                if (name.equals(" ")) {
                    name = "";
                }
            }
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
            return true;
        }
    }
    private class AgentButton extends Button {
        private Agent agent;

        AgentButton(Mouse mouse, Agent agent) {
            super(mouse);
            this.agent = agent;
        }
        public void draw(Graphics g) {
            super.draw(g);
            if (!this.isActive()) {
                return;
            }
            g.drawImage(this.agent.getIcon(), this.x, this.y, this.width, this.height, null);
            if (players[id].isReady()) {
                g.setColor(new Color(80, 80, 80, 240));
                g.fillRect(this.x, this.y, this.width, this.height);
            }
        }
        public boolean run() {
            if (players[id].isReady()) {
                return false;
            }
            messenger.print("AGENT " + this.agent);
            buttons.get("ready").setActive(true);
            return true;
        }
    }
    private class ReadyButton extends Button {
        ReadyButton(Mouse mouse) {
            super(mouse);
        }
        public boolean run() {
            messenger.print("READY");
            this.setActive(false);
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
