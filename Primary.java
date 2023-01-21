import java.util.*;

public class Primary extends Gun {
    private final static HashSet<GunModel> primaries = new HashSet<GunModel>() {
        {
            add(GunModel.Pecker);
            add(GunModel.Swift);
            add(GunModel.Crane);
            add(GunModel.Eagle);
            add(GunModel.Peacock);
            add(GunModel.Turkey);
            add(GunModel.Vulture);
            add(GunModel.Falcon);
            add(GunModel.Rhea);
        }
    };
    
    Primary(String type, int ammo) {
        super(type, ammo);
    }

    public static boolean isPrimary(GunModel gunModel) {
        return primaries.contains(gunModel);
    }
}
