import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game extends Observable {

    private int width = 600;
    private int height = 600;

    private List<Bullet> bullets;
    private Thread mainLoop;
    private boolean alive;

    public Game() {
        alive = true;
        bullets = new CopyOnWriteArrayList<Bullet>();
//        bullets = new ArrayList<Bullet>();
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
            System.out.println("Move " + bullet);
            bullet.move();
//            setChanged();
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
        List<Bullet> toRelease = new ArrayList<Bullet>();
        for(Bullet bullet : toRemove) {
            System.out.println("remove " + bullet);
//            if (bullet != null) {
//                bulletPool.releaseBullet(bullet);
//            }
            if (bullet != null) {
                toRelease.add(bullet);
            }
            bullets.remove(bullet);
        }

        bulletPool.releaseBullet(toRelease);
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
//        Bullet firstBullet = bulletPool.acquireBullet();
//        if (firstBullet != null) {
//            firstBullet.setX(x);
//            firstBullet.setY(y);
//            bullets.add(firstBullet);
//            for (int i = 0; i < 7; i++) {
//                Bullet bullet = bulletPool.acquireBullet();
//                bullet.setX(x);
//                bullet.setY(y);
//                bullets.add(bullet);
//            }
//            System.out.println("Add from pool " + bullets);
//        }
//        else {
//            bullets.add(new Bullet(x, y, 1, 0));
//            bullets.add(new Bullet(x, y, 0, 1));
//            bullets.add(new Bullet(x, y, -1, 0));
//            bullets.add(new Bullet(x, y, 0, -1));
//            bullets.add(new Bullet(x, y, 1, 1));
//            bullets.add(new Bullet(x, y, 1, -1));
//            bullets.add(new Bullet(x, y, -1, 1));
//            bullets.add(new Bullet(x, y, -1, -1));
//            System.out.println("Add from game " + bullets);
//        }

        List<Bullet> requestedBullets = bulletPool.acquireBullet();
//        System.out.println("requestedBullets: " + requestedBullets);
        if (requestedBullets.size() > 0) {
//            System.out.println("requestedBullets: " + requestedBullets);
            for (Bullet bullet: requestedBullets) {
//                Bullet bullet = bulletPool.acquireBullet();
                bullet.setX(x);
                bullet.setY(y);
                bullets.add(bullet);
            }
            System.out.println("Add from pool " + bullets);
//            bullets.addAll(requestedBullets);
        }
        else {
            bullets.add(new Bullet(x, y, 1, 0));
            bullets.add(new Bullet(x, y, 0, 1));
            bullets.add(new Bullet(x, y, -1, 0));
            bullets.add(new Bullet(x, y, 0, -1));
            bullets.add(new Bullet(x, y, 1, 1));
            bullets.add(new Bullet(x, y, 1, -1));
            bullets.add(new Bullet(x, y, -1, 1));
            bullets.add(new Bullet(x, y, -1, -1));
            System.out.println("Add from game " + bullets);
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
    }
}
