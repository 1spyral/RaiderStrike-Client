import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

public enum Agent {
    ZERO(0, "assets/zero.png"),
    MUSTANG(1, "assets/mustang.png"),
    HELLCAT(2, ""),
    WILDCAT(3, ""),
    MIRAGE(4, "");

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