import java.util.*;

public class Sidearm extends Gun {
    private final static HashSet<GunModel> sidearms = new HashSet<GunModel>() {
        {
            add(GunModel.Robin);
            add(GunModel.Duck);
            add(GunModel.Finch);
            add(GunModel.Hummingbird);
            add(GunModel.Raven);
        }
    };

    Sidearm(String type, int ammo) {
        super(type, ammo);
    }

    public static boolean isSidearm(GunModel gunModel) {
        return sidearms.contains(gunModel);
    }
}
