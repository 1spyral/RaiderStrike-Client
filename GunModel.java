import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

public enum GunModel {
    // Sidearms
    Robin("assets/Guns/Robin/Top.png", "assets/Guns/Robin/Side.png", 0, 10, 2, 7),
    Duck("assets/Guns/Duck/Top.png", "assets/Guns/Duck/Side.png", 400, 2, 2, 3),
    Finch("assets/Guns/Finch/Top.png", "assets/Guns/Finch/Side.png", 400, 15, 2, 7),
    Hummingbird("assets/Guns/Hummingbird/Top.png", "assets/Guns/Hummingbird/Side.png", 600, 12, 2, 10),
    Raven("assets/Guns/Raven/Top.png", "assets/Guns/Raven/Side.png", 600, 8, 2.5, 2),
    // Primary
    // Light Machine Guns
    Pecker("assets/Guns/Pecker/Top.png", "assets/Guns/Pecker/Side.png", 1000, 20, 2.5, 15),
    Swift("assets/Guns/Swift/Top.png", "assets/Guns/Swift/Side.png", 1400, 25, 3, 13),
    // Rifles
    Crane("assets/Guns/Crane/Top.png", "assets/Guns/Crane/Side.png", 2000, 15, 3, 3),
    Eagle("assets/Guns/Eagle/Top.png", "assets/Guns/Eagle/Side.png", 2500, 25, 3, 8),
    // Shotguns
    Peacock("assets/Guns/Peacock/Top.png", "assets/Guns/Peacock/Side.png", 1200, 8, 3.5, 1),
    Turkey("assets/Guns/Turkey/Top.png", "assets/Guns/Turkey/Side.png", 1300, 8, 3.5, 1.5),
    // Snipers
    Vulture("assets/Guns/Vulture/Top.png", "assets/Guns/Vulture/Side.png", 1000, 8, 3, 1.5),
    Falcon("assets/Guns/Falcon/Top.png", "assets/Guns/Falcon/Side.png", 4000, 1, 2, 1),
    // Heavy Machine Guns
    Rhea("assets/Guns/Rhea/Top.png", "assets/Guns/Rhea/Side.png", 2300, 50, 4, 12);
    
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
    public BufferedImage getTopImage() {
        return this.topImage;
    }
    public BufferedImage getSideImage() {
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
