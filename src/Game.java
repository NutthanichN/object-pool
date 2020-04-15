import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Game extends Observable {

    private int width = 600;
    private int height = 600;

    private List<Bullet> bullets;
    private Thread mainLoop;
    private boolean alive;

    public Game() {
        alive = true;
        bullets = new ArrayList<Bullet>();
        mainLoop = new Thread() {
            @Override
            public void run() {
                while(alive) {
                    tick();
                    setChanged();
                    notifyObservers();
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        mainLoop.start();
    }

    public void tick() {
        moveBullets();
        cleanupBullets();
    }

    private void moveBullets() {
        System.out.println("Current bullets: " + bullets);
        for(Bullet bullet : bullets) {
            bullet.move();
        }
    }

    private void cleanupBullets() {
        List<Bullet> toRemove = new ArrayList<Bullet>();
        BulletPool bulletPool = BulletPool.getInstance();
        for(Bullet bullet : bullets) {
            if(bullet.getX() <= 0 ||
                    bullet.getX() >= width ||
                    bullet.getY() <= 0 ||
                    bullet.getY() >= height) {
                toRemove.add(bullet);
            }
        }
//        if (toRemove.size() > 0) {
//            bulletPool.releaseBullets(toRemove);
//        }
        for(Bullet bullet : toRemove) {
            bullets.remove(bullet);
            bulletPool.releaseBullet(bullet);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void burstBullets(int x, int y) {
        BulletPool bulletPool = BulletPool.getInstance();
//        List<Bullet> setOfBullets = bulletPool.acquireBullet();
        List<Bullet> requestedBullets = bulletPool.acquireBullet();
        if (requestedBullets.size() == 8) {
            System.out.println("requestedBullets: " + requestedBullets);
            bullets.addAll(requestedBullets);
        }
        else {
////            bulletPool.releaseBullet(new Bullet(x, y, 1, 0));
////            bulletPool.releaseBullet(new Bullet(x, y, 0, 1));
////            bulletPool.releaseBullet(new Bullet(x, y, -1, 0));
////            bulletPool.releaseBullet(new Bullet(x, y, 0, -1));
////            bulletPool.releaseBullet(new Bullet(x, y, 1, 1));
////            bulletPool.releaseBullet(new Bullet(x, y, 1, -1));
////            bulletPool.releaseBullet(new Bullet(x, y, -1, 1));
////            bulletPool.releaseBullet(new Bullet(x, y, -1, -1));
            bullets.add(new Bullet(x, y, 1, 0));
            bullets.add(new Bullet(x, y, 0, 1));
            bullets.add(new Bullet(x, y, -1, 0));
            bullets.add(new Bullet(x, y, 0, -1));
            bullets.add(new Bullet(x, y, 1, 1));
            bullets.add(new Bullet(x, y, 1, -1));
            bullets.add(new Bullet(x, y, -1, 1));
            bullets.add(new Bullet(x, y, -1, -1));
            System.out.println("Add from game " + bullets);
//            bulletPool.releaseBullets(bullets);
        }
//        bullets.add(new Bullet(x, y, 1, 0));
//        bullets.add(new Bullet(x, y, 0, 1));
//        bullets.add(new Bullet(x, y, -1, 0));
//        bullets.add(new Bullet(x, y, 0, -1));
//        bullets.add(new Bullet(x, y, 1, 1));
//        bullets.add(new Bullet(x, y, 1, -1));
//        bullets.add(new Bullet(x, y, -1, 1));
//        bullets.add(new Bullet(x, y, -1, -1));
//        System.out.println("Add from game " + bullets);
//        bulletPool.releaseBullets(bullets);
    }
}
