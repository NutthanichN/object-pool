import java.util.ArrayList;
import java.util.List;

public class BulletPool {

    private static BulletPool instance = new BulletPool();
    private List<Bullet> bullets = new ArrayList<Bullet>();

    private BulletPool() {}

    public static BulletPool getInstance() {
        return instance;
    }

//    public List<Bullet> acquireBullet() {
//        System.out.println("---------------------------");
//        System.out.println("Acquire " + bullets);
//        System.out.println("---------------------------");
////        System.out.println(setOfBullets);
//        List<Bullet> returnBullets = new ArrayList<>();
//        if(bullets.size() > 0) {
//            System.out.println("Here");
//            for(int i = 0; i < 8; i++) {
//                returnBullets.add(bullets.get(i));
//            }
//            bullets.subList(0, 8).clear();
//        }
//        else {
//            System.out.println("bullet size: " + bullets.size());
//        }
//        return returnBullets;
////        return null;
//    }

    public Bullet acquireBullet() {
        System.out.println("---------------------------");
        System.out.println("Acquire " + bullets);
        System.out.println("---------------------------");
        if (bullets.size() > 0) {
            Bullet bullet = bullets.get(0);
            bullets.remove(0);
            return bullet;
        }
        return null;
    }

    public void releaseBullet(Bullet bullet) {
        System.out.println("---------------------------");
        bullets.add(bullet);
        System.out.println("Release: " + bullets);
        System.out.println("---------------------------");
    }
}
