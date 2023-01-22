import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

public enum GunModel {
    // Sidearms
    Robin("/assets/Robin/Top.png", "/assets/Robin/Side.png", 0, 10, 2, 7),
    Duck("/assets/Duck/Top.png", "/assets/Duck/Side.png", 400, 2, 2, 3),
    Finch("/assets/Finch/Top.png", "/assets/Finch/Side.png", 400, 15, 2, 7),
    Hummingbird("/assets/Hummingbird/Top.png", "/assets/Hummingbird/Side.png", 600, 12, 2, 10),
    Raven("/assets/Raven/Top.png", "/assets/Raven/Side.png", 600, 8, 2.5, 2),
    // Primary
    // Light Machine Guns
    Pecker("/assets/Pecker/Top.png", "/assets/Pecker/Side.png", 1000, 20, 2.5, 15),
    Swift("/assets/Swift/Top.png", "/assets/Swift/Side.png", 1400, 25, 3, 13),
    // Rifles
    Crane("/assets/Crane/Top.png", "/assets/Crane/Side.png", 2000, 15, 3, 3),
    Eagle("/assets/Eagle/Top.png", "/assets/Eagle/Side.png", 2500, 25, 3, 8),
    // Shotguns
    Peacock("/assets/Peacock/Top.png", "/assets/Peacock/Side.png", 1200, 8, 3.5, 1),
    Turkey("/assets/Turkey/Top.png", "/assets/Turkey/Side.png", 1300, 8, 3.5, 1.5),
    // Snipers
    Vulture("/assets/Vulture/Top.png", "/assets/Vulture/Side.png", 1000, 8, 3, 1.5),
    Falcon("/assets/Falcon/Top.png", "/assets/Falcon/Side.png", 4000, 1, 2, 1),
    // Heavy Machine Guns
    Rhea("/assets/Rhea/Top.png", "/assets/Rhea/Side.png", 2300, 50, 4, 12);
    
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
