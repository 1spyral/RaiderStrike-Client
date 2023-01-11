import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

public enum GunModel {
    // Sidearms
    Robin("", "", 0, 10, 2, 7),
    Duck("", "", 400, 2, 2, 3),
    Finch("", "", 400, 15, 2, 7),
    Hummingbird("", "", 600, 12, 2, 10),
    Raven("", "", 600, 8, 2.5, 2),
    // Primary
    // Light Machine Guns
    Pecker("", "", 1000, 20, 2.5, 15),
    Swift("", "", 1400, 25, 3, 13),
    // Rifles
    Crane("", "", 2000, 15, 3, 3),
    Eagle("", "", 2500, 25, 3, 8),
    // Shotguns
    Peacock("", "", 1200, 8, 3.5, 1),
    Turkey("", "", 1300, 8, 3.5, 1.5),
    // Snipers
    Vulture("", "", 1000, 8, 3, 1.5),
    Falcon("", "", 4000, 1, 2, 1),
    // Heavy Machine Guns
    Rhea("", "", 2300, 50, 4, 12);
    
    // Name of enum constant can be acquired using .toString() method
    private BufferedImage topImage;
    private BufferedImage sideImage;
    private final int price;
    private final int maxAmmo;
    private final double reloadSpeed;
    private final double fireRate;

    private GunModel(String topPath, String sidePath, int price, int maxAmmo, double reloadSpeed, double fireRate) {
        try {
            this.topImage = ImageIO.read(new File(topPath));
            this.sideImage = ImageIO.read(new File(sidePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.price = price;
        this.maxAmmo = maxAmmo;
        this.reloadSpeed = reloadSpeed;
        this.fireRate = fireRate;
    }
    public Image getTopImage() {
        return this.topImage;
    }
    public Image getSideImage() {
        return this.sideImage;
    }
    public int getPrice() {
        return this.price;
    }
    public int getMaxAmmo() {
        return this.maxAmmo;
    }
    public double getReloadSpeed() {
        return this.reloadSpeed;
    }
    public double getFireRate() {
        return this.fireRate;
    }
}
