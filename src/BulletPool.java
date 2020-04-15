import java.util.ArrayList;
import java.util.List;

public class BulletPool {

    private static BulletPool instance = new BulletPool();
        private List<Bullet> bullets = new ArrayList<Bullet>();

    private BulletPool() {
    }

    public static BulletPool getInstance() {
        return instance;
    }

    public Bullet acquireBullet() {
        if (bullets.size() > 0) {
            Bullet bullet = bullets.get(0);
            bullets.remove(0);
            return bullet;
        }
        return null;
    }

    public void releaseBullet(Bullet bullet) {
        bullets.add(bullet);
    }
}
