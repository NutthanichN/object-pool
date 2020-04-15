import java.util.ArrayList;
import java.util.List;

public class BulletPool {

    private static BulletPool instance = new BulletPool();
    //    private List<Bullet> bullets = new ArrayList<Bullet>();
    private List<List<Bullet>> bullets = new ArrayList<List<Bullet>>();

    private BulletPool() {
    }

    public static BulletPool getInstance() {
        return instance;
    }

    public List<Bullet> acquireBullet() {
        System.out.println("---------------------------");
        System.out.println("Acquire " + bullets);
        System.out.println("---------------------------");
//        System.out.println(setOfBullets);
//        List<Bullet> returnBullets = new ArrayList<Bullet>();
        if (bullets.size() > 0) {
            List<Bullet> first = bullets.get(0);
            bullets.remove(first);
            return first;
        }
        return null;
    }

//    public Bullet acquireBullet() {
//        System.out.println("---------------------------");
//        System.out.println("Acquire " + bullets);
//        System.out.println("---------------------------");
//        if (bullets.size() > 0) {
//            Bullet bullet = bullets.get(0);
//            bullets.remove(0);
//            return bullet;
//        }
//        return null;
//    }

    public void releaseBullet(List<Bullet> bulletList) {
        System.out.println("---------------------------");
        bullets.add(bulletList);
        System.out.println("Release: " + bulletList);
        System.out.println("---------------------------");
    }
}
