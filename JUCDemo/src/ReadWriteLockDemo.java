import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Rifu
 * @create 2018-05-14 23:15
 * <p>
 * <p>
 * 读写锁：ReadWriteLock    (读读之间不需要互斥，其他的都需要互斥)
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        ReadAndWrite readAndWrite = new ReadAndWrite();
        new Thread(new Runnable() {
            @Override
            public void run() {
                readAndWrite.write();
            }
        }, "writeThread").start();

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    readAndWrite.read();
                }
            }, "readThread"+i).start();

        }
    }
}

class ReadAndWrite {
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "  is begin" );
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + " is over ");
        } catch (Exception e) {

        } finally {
            lock.readLock().unlock();
        }
    }

    public void write() {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName());
        } catch (Exception e) {

        } finally {
            lock.writeLock().unlock();
        }
    }
}
