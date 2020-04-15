import java.util.ArrayList;
import java.util.List;

public class BulletPool {

    private static BulletPool instance = new BulletPool();
    private List<List<Bullet>> setOfBullets = new ArrayList<List<Bullet>>();

    private BulletPool() {}

    public static BulletPool getInstance() {
        return instance;
    }

    public List<Bullet> acquireBullet() {
        if(setOfBullets.size() > 0) {
            System.out.println(setOfBullets.remove(0));
           return setOfBullets.remove(0);
        }
        System.out.println("null");
        return null;
    }

    public void releaseBullets(List<Bullet> bullets) {
        setOfBullets.add(bullets);
    }
}
