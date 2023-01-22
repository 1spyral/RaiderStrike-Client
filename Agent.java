import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

/**
 * Enumerator that keeps track of unique agents
 */
public enum Agent {
    ZERO(0, "assets/Agents/zero.png"),
    MUSTANG(1, "assets/Agents/mustang.png"),
    HELLCAT(2, "assets/Agents/hellcat.png"),
    WILDCAT(3, "assets/Agents/wildcat.png"),
    MIRAGE(4, "assets/Agents/mirage.png");

//-------------------------------------------------------------------------------------
    private final int id;
    private BufferedImage icon;

    Agent(int id, String iconPath) {
        this.id = id;
        try {
            this.icon = ImageIO.read(new File(iconPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getId(){
        return this.id;
    }
    public Image getIcon() {
        return this.icon;
    }
}