import java.io.*;
import java.awt.*;
import javax.imageio.*;

public enum GunModel {
    /* TODO: implement gun models */;
    // Name of enum constant can be acquired using .toString() method
    private final Image topImage;
    private final Image sideImage;
    private final int maxAmmo;
    private final double reloadSpeed;
    private final double fireRate;

    private GunModel(String topPath, String sidePath, int maxAmmo, double reloadSpeed, double fireRate) throws Exception {
        this.topImage = ImageIO.read(new File(topPath));
        this.sideImage = ImageIO.read(new File(sidePath));
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
