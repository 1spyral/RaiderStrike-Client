import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

public class MenuState extends State {
    BufferedImage title;

    int id;
    Player[] players;
    int attack;
    int defense;
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
        this.attack = 0;
        this.defense = 0;
        this.name = "";
        this.nameSet = false;
    }
    public void update() {
        if (!this.nameSet) {

        }
    }
    public void draw(Graphics g) {
        if (this.nameSet) {
        }
        else {
            g.drawImage(this.title, (Const.WIDTH - this.title.getWidth()) / 2, (Const.HEIGHT - this.title.getHeight()) / 3, null);
            g.setFont(FontLoader.getFont(80));
            if (this.id == -1) {
                g.drawString("Waiting on server...", (Const.WIDTH - g.getFontMetrics().stringWidth("Waiting on server...")) / 2, (int)(Const.HEIGHT * 0.6));
            }
            else {
    
            }      
        }
    }
    public void close() {
        super.close();
    }
}
