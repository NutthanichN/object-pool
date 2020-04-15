import java.util.ArrayList;
import java.util.List;

public class BulletPool {

    private static BulletPool instance = new BulletPool();
    private List<Bullet> bullets = new ArrayList<Bullet>();

    private BulletPool() {}

    public static BulletPool getInstance() {
        return instance;
    }

    public List<Bullet> acquireBullet() {
        System.out.println("---------------------------");
        System.out.println("Acquire " + bullets);
        System.out.println("---------------------------");
//        System.out.println(setOfBullets);
        List<Bullet> returnBullets = new ArrayList<>();
        if(bullets.size() > 0) {
            for(int i = 0; i < 8; i++) {
                returnBullets.add(bullets.get(i));
            }

//            System.out.println("Here");
//            System.out.println(setOfBullets);
//            List<Bullet> removed = setOfBullets.get(0);
////            List<Bullet> removed = setOfBullets.remove(0);
//            System.out.println(removed);
////            System.out.println("---------------------------");
////            return removed;
        }
//        else {
//            System.out.println("null");
//            System.out.println("---------------------------");
//        }
        return returnBullets;
//        return null;
    }

    public void releaseBullet(Bullet bullet) {
        System.out.println("---------------------------");
        bullets.add(bullet);
        System.out.println("Release: " + bullets);
        System.out.println("---------------------------");
    }
}
