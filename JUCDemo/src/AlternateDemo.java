import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Rifu
 * @create 2018-05-14 22:25
 * <p>
 * 线程按顺序交替打印
 */
public class AlternateDemo {
    public static void main(String [] args){
        Alternate alternate = new Alternate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 10; i++) {
                    alternate.loopA(i);
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 10; i++) {
                    alternate.loopB(i);
                }
            }
        },"B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 10; i++) {
                    alternate.loopC(i);
                }
            }
        },"C").start();
    }
}

class Alternate {

    private int currentIndex = 1;

    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void loopA(int totalLoop) {
        lock.lock();
        try {
            while (currentIndex != 1) {
                c1.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i + "\t" + totalLoop);
            }
            currentIndex = 2;
            c2.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }

    }

    public void loopB(int totalLoop) {
        lock.lock();
        try {
            while (currentIndex != 2) {
                c2.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i + "\t" + totalLoop);
            }
            currentIndex = 3;
            c3.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }

    }

    public void loopC(int totalLoop) {
        lock.lock();
        try {
            while (currentIndex != 3) {
                c3.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i + "\t" + totalLoop);
            }
            currentIndex = 1;
            c1.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }

    }

}


