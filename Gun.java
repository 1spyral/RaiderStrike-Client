public class Gun {
    public GunModel model;
    private int ammo;
    private boolean active;

    Gun(String type, int ammo) {
        this.model = GunModel.valueOf(type);
        this.ammo = ammo;
        this.active = false;
    }
    Gun(String type) {
        this(type, GunModel.valueOf(type).getMaxAmmo());
    }
    public GunModel getModel() {
        return this.model;
    }
    public int getAmmo() {
        return this.ammo; 
    }
    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }
    public boolean isActive() {
        return this.active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
}
