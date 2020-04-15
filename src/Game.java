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
        for(Bullet bullet : toRemove) {
            if (bullet != null) {
                bullet.setAllAttributes(0, 0, 0, 0);
                bulletPool.releaseBullet(bullet);
            }
            bullets.remove(bullet);
        }
    }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public List<Bullet> getBullets() { return bullets; }

    public void burstBullets(int x, int y) {
        int[] dxList = {1, 0, -1, 0, 1, 1, -1, -1};
        int[] dyList = {0, 1, 0, -1, 1, -1, 1, -1};
        BulletPool bulletPool = BulletPool.getInstance();

        for (int i = 0; i < 8; i++) {
            Bullet bullet = bulletPool.acquireBullet();
            if (bullet != null) {
                bullet.setAllAttributes(x, y, dxList[i], dyList[i]);
                bullets.add(bullet);
            }
            else {
                bullets.add(new Bullet(x, y, dxList[i], dyList[i]));
            }
        }
    }
    
}
